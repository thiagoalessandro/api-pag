package br.com.intelector.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "TB_SEG_SUBMENU")
public class SegSubMenu extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_menu")
	private SegMenu menu;
	
	@OneToOne
	@JoinColumn(name="id_func")
	private SegFuncionalidade funcionalidade;
	
	@Column(name="icone", length=150)
	private String icone;
			
	@Column(name="ordem")
	private Integer ordem;

	public SegSubMenu(SegMenu menu, SegFuncionalidade funcionalidade, String icone, Integer ordem) {
		super();
		this.menu = menu;
		this.funcionalidade = funcionalidade;
		this.icone = icone;
		this.ordem = ordem;
	}
	
}
