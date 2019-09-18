package br.com.intelector.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SegOperacaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sigla;
    private String descricao;
    private String icone;
    private Boolean enable = false;
    private Boolean immediateAction = false;
    private Boolean dataId = false;
    private Boolean singleSelect = false;

}
