package br.com.intelector.api.service;

import br.com.intelector.api.controller.response.UserAuthDTO;
import br.com.intelector.api.security.dto.UserDetailsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public UserAuthDTO userAuthenticated() {
		UserAuthDTO userAuthDTO = new UserAuthDTO();
		UserDetailsDTO userDetailsDTO = userDetailsDTO();
		userAuthDTO.setNome(userDetailsDTO.getNome());
		userAuthDTO.setPerfil(userDetailsDTO.getPerfil());
		userAuthDTO.setEmail(userDetailsDTO.getEmail());
		userAuthDTO.setUsuario(userDetailsDTO.getUsername());
		userAuthDTO.setListMenu(userDetailsDTO.getListMenu());
		return userAuthDTO;
	}

	public UserDetailsDTO userDetailsDTO(){
		Authentication authentication = getAuthentication();
		return (UserDetailsDTO) authentication.getPrincipal();
	}

	public Authentication getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
