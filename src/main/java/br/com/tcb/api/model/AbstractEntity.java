package br.com.intelector.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.*;

import br.com.intelector.api.model.enums.SituacaoRegistroEnum;
import br.com.intelector.api.security.dto.UserDetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "id_sit", length=1)
	private SituacaoRegistroEnum situacao;
	
	@Column(name = "cd_usu_atu", length=50)
	private String cdUsuAtu;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_atu")
	private Date dhAtu;

	@PrePersist
	public void prePersist() {
		dhAtu = new Date();
		situacao = SituacaoRegistroEnum.Ativo;
		cdUsuAtu = getUserName();
	}
	
	@PreUpdate
	public void preUpdate() {
		dhAtu = new Date();
		cdUsuAtu = getUserName();
	}

	@PreRemove
	public void preRemove() {
		dhAtu = new Date();
		cdUsuAtu = getUserName();
	}

	@JsonIgnore
	private String getUserName(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null)
			return ((UserDetailsDTO) authentication.getPrincipal()).getUsername();
		return null;
	}

}
