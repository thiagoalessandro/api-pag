package br.com.intelector.api.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "TB_SEG_PERFIL")
public class SegPerfil extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="nome", length=100)
	private String nome;
	
	@Column(name="descricao", length=150)
	private String descricao;

	@OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SegPermissao> listPermissao;

	public SegPerfil(String nome, String descricao, List<SegPermissao> listPermissao) {
		this.nome = nome;
		this.descricao = descricao;
		this.listPermissao = listPermissao;
	}
}
