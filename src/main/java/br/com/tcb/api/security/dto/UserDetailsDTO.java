package br.com.tcb.api.security.dto;

import br.com.tcb.api.model.SegMenu;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsDTO implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String nome;
    private String perfil;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private List<SegMenu> listMenu;

    public UserDetailsDTO() {}

    public UserDetailsDTO(String id, String username, String email, String password, String nome, String perfil, List<SegMenu> listMenu, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.email = email;
        this.nome = nome;
        this.perfil = perfil;
        this.listMenu = listMenu;
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
