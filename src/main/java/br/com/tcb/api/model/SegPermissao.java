package br.com.tcb.api.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SEG_PERMISSAO")
public class SegPermissao extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_perf")
	private SegPerfil perfil;

	@ManyToOne
	@JoinColumn(name = "id_func_oper")
	private SegFuncionalidadeOperacao funcionalidadeOperacao;

}
