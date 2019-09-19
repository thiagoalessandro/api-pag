package br.com.intelector.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "TB_SEG_FUNCIONALIDADE")
public class SegFuncionalidade extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 100)
	@Column(name="nome", length=100)
	private String nome;

	@NotNull
	@Size(min = 4, max = 4)
	@Column(name="sigla", length=4)
	private String sigla;

	@Column(name="descricao", length=150)
	private String descricao;

	@OneToMany(mappedBy = "funcionalidade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("ordem")
	private List<SegFuncionalidadeOperacao> listFuncionalidadeOperacao;

	public SegFuncionalidade(String nome, String sigla, String descricao,
			List<SegFuncionalidadeOperacao> listFuncionalidadeOperacao) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.descricao = descricao;
		this.listFuncionalidadeOperacao = listFuncionalidadeOperacao;
	}
	
}
