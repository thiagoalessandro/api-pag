package br.com.tcb.api.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SEG_USUARIO")
public class SegUsuario extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="nome", length=100)
	private String nome;
	
	@Column(name="login", length=20)
	private String login;
	
	@Column(name="email", length=150)
	private String email;

	@Column(name="password", length=255)
	private String password;

	@ManyToOne
	@JoinColumn(name = "id_perf")
	private SegPerfil perfil;


	public SegUsuario(String nome, String login, String email, String password, SegPerfil perfil) {
		super();
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.password = password;
		this.perfil = perfil;
	}
	
}
