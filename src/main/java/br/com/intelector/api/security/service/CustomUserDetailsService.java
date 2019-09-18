package br.com.intelector.api.security.service;

import br.com.intelector.api.model.SegMenu;
import br.com.intelector.api.model.SegUsuario;
import br.com.intelector.api.security.UserDetailsFactory;
import br.com.intelector.api.service.SegMenuService;
import br.com.intelector.api.service.SegUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SegUsuarioService usuarioService;

    @Autowired
    private SegMenuService segMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SegUsuario> usuario = usuarioService.findByLogin(username);
        List<SegMenu> listMenuAutorized;
        if(!usuario.isPresent()) {
            throw new UsernameNotFoundException(String.format("No user found with email %s", username));
        }else {
            listMenuAutorized = segMenuService.findByUserAutenticated(usuario.get());
            return UserDetailsFactory.create(usuario.get(), listMenuAutorized);
        }
    }

}
