package br.com.intelector.api.controller.response;

import br.com.intelector.api.model.SegMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAuthDTO {
    private String nome;
    private String email;
    private String usuario;
    private String perfil;
    private List<SegMenu> listMenu;
}
