# tcb-web

# Debezium

## O que é o Debezium?
O Debezium é uma plataforma distribuída que transforma seus bancos de dados existentes em fluxos de eventos, para que os aplicativos possam reagir rapidamente a cada alteração no nível de linha nos bancos de dados. O Debezium é construído sobre o Kafka e fornece conectores compatíveis com o Kafka Connect que monitoram sistemas específicos de gerenciamento de banco de dados. O Debezium registra o histórico de alterações de dados nos logs Kafka, para que seu aplicativo possa ser parado e reiniciado a qualquer momento e consuma facilmente todos os eventos que ele perdeu enquanto não estava em execução, garantindo que todos os eventos sejam processados ​​corretamente e completamente.

## PostgreSQL no Amazon RDS

É possível monitorar o banco de dados PostgreSQL em execução no Amazon RDS . Para executá-lo, você deve atender às seguintes condições

O parâmetro da instância rds.logical_replicationestá definido como 1.

Verifique se o wal_level parâmetro está definido logical executando a consulta SHOW wal_level como usuário mestre do DB; isso pode não ser o caso nas configurações de replicação de várias zonas. Você não pode definir esta opção manualmente, ela é ( alterada automaticamente ) quando rds.logical_replication está definida como 1. Se wal_level não for logical após a alteração acima, provavelmente é porque a instância precisa ser reiniciada devido à alteração do grupo de parâmetros, isso ocorrerá de acordo com a janela de manutenção ou poderá ser feito manualmente.

Defina o plugin.name parâmetro Debezium como wal2json. Você pode pular isso no PostgreSQL 10+ se desejar usar o suporte ao fluxo de replicação lógica pgoutput.

Use a conta principal do banco de dados para replicação, pois o RDS atualmente não suporta a configuração de REPLICATION privilégios para outra conta.

A partir de janeiro de 2019, as seguintes versões do Postgres no RDS vêm com uma versão atualizada do wal2json e, portanto, devem ser usadas:

Postgres 9.6: 9.6.10 e mais recente

Postgres 10: 10.5 e mais recente

Postgres 11: qualquer versão

## Restrições

- O conector pode ser executado apenas no servidor master ativo e suporta apenas banco de dados com codificação de caracteres UTF-8

- O plug-in pgoutput não emite eventos para tabelas sem chaves primárias de ponto flutuante

- O plug-in wal2json não suporta valores especiais (NaN ou infinity) para tipos de ponto flutuante

## Instalação Local

### PostgreSQL
postgresql.conf

Informar ao servidor que ele deve carregar na inicialização o decoderbufs e wal2json (plugins de decodificação lógicas)
```
# MODULES
shared_preload_libraries = 'decoderbufs,wal2json' 
```
A seguir, configurar o slot de replicação, independentemente do decodificador usado:
```
# REPLICATION
wal_level = logical             
max_wal_senders = 1             
max_replication_slots = 1 
wal_keep_segments = 0
```

Conceder permissões de replicação a um usuário, defina uma função do PostgreSQL que possua pelo menos as permissões REPLICATIONe LOGIN. 

Por exemplo:

```sql
CREATE ROLE name REPLICATION LOGIN;
```

**OBS**: Os super usuários têm, por padrão, as duas permissões acima.

Por fim, configure o servidor PostgreSQL para permitir a replicação entre a máquina servidor e o host no qual o conector Debezium PostgreSQL está sendo executado:

```
local   replication     <youruser>                          trust   
host    replication     <youruser>  127.0.0.1/32            trust   
host    replication     <youruser>  ::1/128                 trust   
```

- diz ao servidor para permitir a replicação *youruser* localmente (ou seja, na máquina do servidor)
- diz ao servidor para permitir *youruser* a localhost receber alterações de replicação usando IPV4
- diz ao servidor para permitir *youruser* a localhost receber alterações de replicação usando IPV6

Por exemplo, considere uma instalação PostgreSQL com um postgresbanco de dados e um inventory esquema que contém quatro tabelas: products, products_on_hand, customers, e orders. Se o conector que monitora esse banco de dados receber um nome de servidor lógico fulfillment, o conector produzirá eventos nesses quatro tópicos Kafka:

- fulfillment.inventory.products

- fulfillment.inventory.products_on_hand

- fulfillment.inventory.customers

- fulfillment.inventory.orders

Se, por outro lado, as tabelas não public fizessem parte de um esquema específico, mas fossem criadas no esquema PostgreSQL padrão, o nome dos tópicos do Kafka seria:

- fulfillment.public.products

- fulfillment.public.products_on_hand

- fulfillment.public.customers

- fulfillment.public.orders

### Meta Dados

Cada *record* produzido pelo conector PostgreSQL possui, além do evento do banco de dados, algumas meta-informações sobre onde o evento ocorreu no servidor, o nome da partição de origem e o nome do tópico Kafka e partição em que o evento deve ser colocado:

```json
"sourcePartition": {
     "server": "fulfillment"
 },
 "sourceOffset": {
     "lsn": "24023128",
     "txId": "555",
     "ts_usec": "1482918357011699"
 },
 "kafkaPartition": null
```
 
**OBS:** O conector do PostgreSQL usa apenas 1 partição Kafka Connect e coloca os eventos gerados em 1 partição Kafka. Portanto, o nome do *sourcePartition* sempre será o padrão da database.server.name propriedade de configuração, enquanto *kafkaPartition* o valor tem o *null* que significa que o conector não usa uma partição Kafka específica.

### Eventos

Todos os eventos de alteração de dados produzidos pelo conector PostgreSQL possuem uma chave e um valor, embora a estrutura da chave e do valor dependa da tabela da qual os eventos de alteração foram originados

### Instalando o conector debezium

Se você já instalou o Zookeeper , o Kafka e o Kafka Connect , é fácil usar o conector PostgreSQL do Debezium. Basta baixar o arquivo de plug-in do conector , extrair os JARs para o ambiente do Kafka Connect e adicionar o diretório com os JARs ao caminho de classe do Kafka Connect . Reinicie seu processo Kafka Connect para pegar os novos JARs.

## REST API - Gerenciando conectores

- GET /connectors - return a list of active connectors
- POST /connectors - create a new connector; the request body should be a JSON object containing a string name field and a object config field with the connector configuration parameters
- GET /connectors/{name} - get information about a specific connector
- GET /connectors/{name}/config - get the configuration parameters for a specific connector
- PUT /connectors/{name}/config - update the configuration parameters for a specific connector
- GET /connectors/{name}/status - get current status of the connector, including if it is running, failed, paused, etc., which worker it is assigned to, error information if it has failed, and the state of all its tasks
- GET /connectors/{name}/tasks - get a list of tasks currently running for a connector
- GET /connectors/{name}/tasks/{taskid}/status - get current status of the task, including if it is running, failed, paused, etc., which worker it is assigned to, and error information if it has failed
- PUT /connectors/{name}/pause - pause the connector and its tasks, which stops message processing until the connector is resumed
- PUT /connectors/{name}/resume - resume a paused connector (or do nothing if the connector is not paused)
- POST /connectors/{name}/restart - restart a connector (typically because it has failed)
- POST /connectors/{name}/tasks/{taskId}/restart - restart an individual task (typically because it has failed)
- DELETE /connectors/{name} - delete a connector, halting all tasks and deleting its configuration

## Criando um Conector

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:8083/connectors/ -d @docker/configuration/register-postgres-connector.json

register-postgres-connector.json
```json
{
  "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "database.hostname": "database",
      "database.port": "5432",
      "database.user": "postgres",
      "database.password": "postgres",
      "database.dbname": "db_tcb_main",
      "database.server.name": "database",
      "database.history.kafka.topic": "schema-changes.db_tcb_main",
      "plugin.name": "wal2json",
      "database.whitelist": "db_tcb_main",
      "include.schema.changes": "true",
      "database.history.kafka.bootstrap.servers": "localhost:9092"
  }
}
```

## KAFKA

Criar tópicos
```
$ kafka-topics --zookeeper 127.0.0.1:2181 --create --topic first_topic --partition 3 --replication-factor 1
```

Listar tópicos

```
kafka-topics --zookeeper zookeeper:2181 --list
```

Remover tópicos

```
kafka-topics --zookeeper zookeeper:2181 --topic database.public.remessa --delete
```

Detalhando tópicos

```
kafka-topics --zookeeper zookeeper:2181 --topic database.public.remessa --describe
```

Consumindo mensagens com Consumers

```
kafka-console-consumer --bootstrap-server kafka:9092 --topic database.public.remessa2 --from-beginning
```

Verificar slot de replicação
```
select pg_drop_replication_slot('connector_tcb_main_remessa');
```

## Referências

https://medium.com/@singaretti/postgresql-debezium-kafka-s3-5efd9b8eced

https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html#ce-docker-quickstart

https://medium.com/@singaretti/streaming-de-dados-do-postgresql-utilizando-kafka-debezium-v2-d49f46d70b37

https://debezium.io/documentation/reference/0.9/connectors/postgresql.html

https://github.com/debezium/docker-images/blob/master/postgres/10/Dockerfile

https://archive.cloudera.com/kafka/kafka/2/kafka-0.10.0-kafka2.1.0/connect.html

https://medium.com/data-hackers/mensageria-de-alta-performance-com-apache-kafka-2-ac6e5c0538e1

https://gist.github.com/jpsoroulas/30e9537138ca62a79fe261cff7ceb716