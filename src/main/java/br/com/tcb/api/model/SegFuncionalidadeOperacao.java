package br.com.tcb.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SEG_FUNCIONALIDADE_OPERACAO")
public class SegFuncionalidadeOperacao extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_func")
	private SegFuncionalidade funcionalidade;
	
	@OneToOne
	@JoinColumn(name="id_oper")
	private SegOperacao operacao;
		
	@Column(name="menu_principal")
	private Boolean menuPrincipal;
	
	@Column(name="ordem")
	private Integer ordem;

	public SegFuncionalidadeOperacao(SegFuncionalidade funcionalidade, SegOperacao operacao, Boolean menuPrincipal, Integer ordem) {
		super();
		this.funcionalidade = funcionalidade;
		this.operacao = operacao;
		this.menuPrincipal = menuPrincipal;
		this.ordem = ordem;
	}
	
}
