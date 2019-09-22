package br.com.tcb.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "TB_SEG_OPERACAO")
public class SegOperacao extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O Campo nome é obrigatório")
    @Column(name = "nome", length = 150)
    private String nome;

    @NotEmpty(message = "O Campo sigla é obrigatório")
    @Size(min = 4, max = 4, message = "O Campo sigla deve ter o tamanho entre {min} e {max}")
    @Column(name = "sigla", length = 4)
    private String sigla;

    @NotEmpty(message = "O Campo descrição é obrigatório")
    @Column(name = "descricao", length = 150)
    private String descricao;

    @Column(name = "icone", length = 150)
    private String icone;

    @Column(name = "enable")
    private Boolean enable = false;

    @Column(name = "immediate_action")
    private Boolean immediateAction = false;

    @Column(name = "data_id")
    private Boolean dataId = false;

    @Column(name = "single_select")
    private Boolean singleSelect = false;

    public SegOperacao(String nome, String sigla, String descricao, String icone, Boolean enable, Boolean immediateAction, Boolean dataId, Boolean singleSelect) {
        super();
        this.nome = nome;
        this.sigla = sigla;
        this.descricao = descricao;
        this.icone = icone;
        this.enable = enable;
        this.immediateAction = immediateAction;
        this.dataId = dataId;
        this.singleSelect = singleSelect;
    }

}
