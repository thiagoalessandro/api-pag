package br.com.intelector.api.security;

import br.com.intelector.api.model.SegFuncionalidadeOperacao;
import br.com.intelector.api.model.SegMenu;
import br.com.intelector.api.model.SegPermissao;
import br.com.intelector.api.model.SegUsuario;
import br.com.intelector.api.security.dto.UserDetailsDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsFactory {

    private UserDetailsFactory() {
    }

    public static UserDetailsDTO create(SegUsuario usuario, List<SegMenu> listMenu) {
        return new UserDetailsDTO(usuario.getId().toString(), usuario.getLogin(), usuario.getEmail(), usuario.getPassword(), usuario.getNome(), usuario.getPerfil().getNome(), listMenu, mapToGrantedAuthorities(usuario.getPerfil().getListPermissao()));
    }

    private static Collection<? extends GrantedAuthority> mapToGrantedAuthorities(List<SegPermissao> listPermissao) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        listPermissao.forEach(permissao -> authorities.add(new SimpleGrantedAuthority(generateAuthority(permissao.getFuncionalidadeOperacao()))));
        return authorities;
    }

    private static String generateAuthority(SegFuncionalidadeOperacao segFuncionalidadeOperacao){
        return segFuncionalidadeOperacao.getFuncionalidade().getSigla().toUpperCase().concat("_").concat(segFuncionalidadeOperacao.getOperacao().getSigla().toUpperCase());
    }

}
