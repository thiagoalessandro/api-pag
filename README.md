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

## Referências
https://medium.com/@singaretti/postgresql-debezium-kafka-s3-5efd9b8eced

https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html#ce-docker-quickstart

https://medium.com/@singaretti/streaming-de-dados-do-postgresql-utilizando-kafka-debezium-v2-d49f46d70b37

https://debezium.io/documentation/reference/0.9/connectors/postgresql.html

https://github.com/debezium/docker-images/blob/master/postgres/10/Dockerfile

##
