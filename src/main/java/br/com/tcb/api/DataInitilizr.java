package br.com.intelector.api;

import br.com.intelector.api.model.*;
import br.com.intelector.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Component
public class DataInitilizr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    SegUsuarioService usuarioService;

    @Autowired
    SegOperacaoService operacaoService;

    @Autowired
    SegFuncionalidadeService funcionalidadeService;

    @Autowired
    SegMenuService menuService;

    @Autowired
    SegPerfilService segPerfilService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Operações

        List<SegOperacao> listOperacao = new ArrayList<SegOperacao>();

        SegOperacao cadastrar = new SegOperacao("Cadastrar", "CADA", "Cadastrar Registro", "add", true, false, false, false);
        listOperacao.add(cadastrar);

        SegOperacao consultar = new SegOperacao("Consultar", "CONS", "Consultar Registros", "list", true, false, false, false);
        listOperacao.add(consultar);

        SegOperacao editar = new SegOperacao("Editar", "EDIT", "Editar Registro", "edit", false, false, true, true);
        listOperacao.add(editar);

        SegOperacao visualizar = new SegOperacao("Visualizar", "VISU", "Visualizar Registro", "zoom_in", false, false, true, true);
        listOperacao.add(visualizar);

        SegOperacao excluir = new SegOperacao("Excluir", "EXCL", "Excluir Registro", "delete_outline", false, true, true, false);
        listOperacao.add(excluir);

        SegOperacao gerarRelatorioXls = new SegOperacao("Relatório XLS", "RELX", "Relatório XLS", "", true, true, true, true);
        listOperacao.add(gerarRelatorioXls);

        SegOperacao gerarRelatorioPdf = new SegOperacao("Relatório PDF", "RELP", "Relatório PDF", "", true, true, true, true);
        listOperacao.add(gerarRelatorioPdf);

        operacaoService.saveAll(listOperacao);

        //Funcionalidades x Operações

        List<SegFuncionalidade> listFuncionalidade = new ArrayList<SegFuncionalidade>();

        List<SegFuncionalidadeOperacao> listFuncionalidadeOperacaoOperacao = new ArrayList<SegFuncionalidadeOperacao>();

        SegFuncionalidade funcionalidadeOperacao = new SegFuncionalidade("Operação", "OPER", "Gerenciar Operações", listFuncionalidadeOperacaoOperacao);
        SegFuncionalidadeOperacao operacaoCadastrar = new SegFuncionalidadeOperacao(funcionalidadeOperacao, cadastrar, true, 1);
        SegFuncionalidadeOperacao operacaoVisualizar = new SegFuncionalidadeOperacao(funcionalidadeOperacao, visualizar, true, 2);
        SegFuncionalidadeOperacao operacaoEditar = new SegFuncionalidadeOperacao(funcionalidadeOperacao, editar, true, 3);
        SegFuncionalidadeOperacao operacaoExcluir = new SegFuncionalidadeOperacao(funcionalidadeOperacao, excluir, true, 4);
        SegFuncionalidadeOperacao operacaoConsultar = new SegFuncionalidadeOperacao(funcionalidadeOperacao, consultar, false, 5);

        listFuncionalidadeOperacaoOperacao.add(operacaoCadastrar);
        listFuncionalidadeOperacaoOperacao.add(operacaoVisualizar);
        listFuncionalidadeOperacaoOperacao.add(operacaoEditar);
        listFuncionalidadeOperacaoOperacao.add(operacaoExcluir);
        listFuncionalidadeOperacaoOperacao.add(operacaoConsultar);

        listFuncionalidade.add(funcionalidadeOperacao);

        List<SegFuncionalidadeOperacao> listFuncionalidadeOperacaoFuncionalidade = new ArrayList<SegFuncionalidadeOperacao>();

        SegFuncionalidade funcionalidadeFuncionalidade = new SegFuncionalidade("Funcionalidade", "FUNC", "Gerenciar Funcionalidade", listFuncionalidadeOperacaoFuncionalidade);
        SegFuncionalidadeOperacao funcionalidadeCadastrar = new SegFuncionalidadeOperacao(funcionalidadeFuncionalidade, cadastrar, true, 2);
        SegFuncionalidadeOperacao funcionalidadeEditar = new SegFuncionalidadeOperacao(funcionalidadeFuncionalidade, editar, false, 3);
        SegFuncionalidadeOperacao funcionalidadeExcluir = new SegFuncionalidadeOperacao(funcionalidadeFuncionalidade, excluir, false, 4);
        listFuncionalidadeOperacaoOperacao.add(funcionalidadeCadastrar);
        listFuncionalidadeOperacaoOperacao.add(funcionalidadeEditar);
        listFuncionalidadeOperacaoOperacao.add(funcionalidadeExcluir);

        listFuncionalidade.add(funcionalidadeFuncionalidade);

        List<SegFuncionalidadeOperacao> listFuncionalidadeOperacaoMenu = new ArrayList<SegFuncionalidadeOperacao>();

        SegFuncionalidade funcionalidadeMenu = new SegFuncionalidade("Menu", "MENU", "Gerenciar Menu", listFuncionalidadeOperacaoMenu);
        SegFuncionalidadeOperacao menuCadastrar = new SegFuncionalidadeOperacao(funcionalidadeMenu, cadastrar, true, 2);
        SegFuncionalidadeOperacao menuEditar = new SegFuncionalidadeOperacao(funcionalidadeMenu, editar, false, 3);
        SegFuncionalidadeOperacao menuExcluir = new SegFuncionalidadeOperacao(funcionalidadeMenu, excluir, false, 4);
        listFuncionalidadeOperacaoMenu.add(menuCadastrar);
        listFuncionalidadeOperacaoMenu.add(menuEditar);
        listFuncionalidadeOperacaoMenu.add(menuExcluir);

        listFuncionalidade.add(funcionalidadeMenu);

        List<SegFuncionalidadeOperacao> listFuncionalidadeOperacaoSubMenu = new ArrayList<SegFuncionalidadeOperacao>();

        SegFuncionalidade funcionalidadeSubMenu = new SegFuncionalidade("SubMenu", "SUBM", "Gerenciar SubMenu", listFuncionalidadeOperacaoSubMenu);
        SegFuncionalidadeOperacao subMenuCadastrar = new SegFuncionalidadeOperacao(funcionalidadeSubMenu, cadastrar, true, 2);
        SegFuncionalidadeOperacao subMenuEditar = new SegFuncionalidadeOperacao(funcionalidadeSubMenu, editar, false, 3);
        SegFuncionalidadeOperacao subMenuExcluir = new SegFuncionalidadeOperacao(funcionalidadeSubMenu, excluir, false, 4);
        listFuncionalidadeOperacaoSubMenu.add(subMenuCadastrar);
        listFuncionalidadeOperacaoSubMenu.add(subMenuEditar);
        listFuncionalidadeOperacaoSubMenu.add(subMenuExcluir);

        listFuncionalidade.add(funcionalidadeSubMenu);

        List<SegFuncionalidadeOperacao> listFuncionalidadeOperacaoPerfil = new ArrayList<SegFuncionalidadeOperacao>();

        SegFuncionalidade funcionalidadePerfil = new SegFuncionalidade("Perfil", "PERF", "Gerenciar Perfil", listFuncionalidadeOperacaoPerfil);
        SegFuncionalidadeOperacao perfilCadastrar = new SegFuncionalidadeOperacao(funcionalidadePerfil, cadastrar, true, 1);
        SegFuncionalidadeOperacao perfilVisualizar = new SegFuncionalidadeOperacao(funcionalidadePerfil, visualizar, true, 2);
        SegFuncionalidadeOperacao perfilEditar = new SegFuncionalidadeOperacao(funcionalidadePerfil, editar, false, 3);
        SegFuncionalidadeOperacao perfilExcluir = new SegFuncionalidadeOperacao(funcionalidadePerfil, excluir, false, 4);
        SegFuncionalidadeOperacao perfilConsultar = new SegFuncionalidadeOperacao(funcionalidadePerfil, consultar, false, 5);
        listFuncionalidadeOperacaoPerfil.add(perfilCadastrar);
        listFuncionalidadeOperacaoPerfil.add(perfilVisualizar);
        listFuncionalidadeOperacaoPerfil.add(perfilEditar);
        listFuncionalidadeOperacaoPerfil.add(perfilExcluir);
        listFuncionalidadeOperacaoPerfil.add(perfilConsultar);

        listFuncionalidade.add(funcionalidadePerfil);

        List<SegFuncionalidadeOperacao> listFuncionalidadeOperacaoPermissao = new ArrayList<SegFuncionalidadeOperacao>();

        SegFuncionalidade funcionalidadePermissao = new SegFuncionalidade("Permissão", "PERM", "Gerenciar Permissão", listFuncionalidadeOperacaoPermissao);
        SegFuncionalidadeOperacao permissaoCadastrar = new SegFuncionalidadeOperacao(funcionalidadePermissao, cadastrar, true, 2);
        SegFuncionalidadeOperacao permissaoEditar = new SegFuncionalidadeOperacao(funcionalidadePermissao, editar, false, 3);
        SegFuncionalidadeOperacao permissaoExcluir = new SegFuncionalidadeOperacao(funcionalidadePermissao, excluir, false, 4);
        listFuncionalidadeOperacaoPermissao.add(permissaoCadastrar);
        listFuncionalidadeOperacaoPermissao.add(permissaoEditar);
        listFuncionalidadeOperacaoPermissao.add(permissaoExcluir);

        listFuncionalidade.add(funcionalidadePermissao);

        List<SegFuncionalidadeOperacao> listFuncionalidadeOperacaoUsuario = new ArrayList<SegFuncionalidadeOperacao>();

        SegFuncionalidade funcionalidadeUsuario = new SegFuncionalidade("Usuário", "USUA", "Gerenciar Usuário", listFuncionalidadeOperacaoUsuario);
        SegFuncionalidadeOperacao usuarioCadastrar = new SegFuncionalidadeOperacao(funcionalidadeUsuario, cadastrar, true, 1);
        SegFuncionalidadeOperacao usuarioVisualizar = new SegFuncionalidadeOperacao(funcionalidadeUsuario, visualizar, true, 2);
        SegFuncionalidadeOperacao usuarioEditar = new SegFuncionalidadeOperacao(funcionalidadeUsuario, editar, true, 3);
        SegFuncionalidadeOperacao usuarioExcluir = new SegFuncionalidadeOperacao(funcionalidadeUsuario, excluir, true, 4);
        SegFuncionalidadeOperacao usuarioConsultar = new SegFuncionalidadeOperacao(funcionalidadeUsuario, consultar, false, 5);
        listFuncionalidadeOperacaoUsuario.add(usuarioCadastrar);
        listFuncionalidadeOperacaoUsuario.add(usuarioVisualizar);
        listFuncionalidadeOperacaoUsuario.add(usuarioEditar);
        listFuncionalidadeOperacaoUsuario.add(usuarioExcluir);
        listFuncionalidadeOperacaoPerfil.add(usuarioConsultar);

        listFuncionalidade.add(funcionalidadeUsuario);

        funcionalidadeService.saveAll(listFuncionalidade);

        //Menu x SubMenu
        List<SegSubMenu> listSubMenu = new ArrayList<SegSubMenu>();

        SegMenu menuSeguranca = new SegMenu("Segurança", "lock", 1, listSubMenu);

        SegSubMenu subMenuOperacao = new SegSubMenu(menuSeguranca, funcionalidadeOperacao, "build", 1);
        listSubMenu.add(subMenuOperacao);

        SegSubMenu subMenufuncionalidade = new SegSubMenu(menuSeguranca, funcionalidadeFuncionalidade, "web", 2);
        listSubMenu.add(subMenufuncionalidade);

        SegSubMenu subMenuMenu = new SegSubMenu(menuSeguranca, funcionalidadeMenu, "format_list_numbered", 3);
        listSubMenu.add(subMenuMenu);

        SegSubMenu subMenuPerfil = new SegSubMenu(menuSeguranca, funcionalidadePerfil, "how_to_reg", 4);
        listSubMenu.add(subMenuPerfil);

        SegSubMenu subMenuPermissao = new SegSubMenu(menuSeguranca, funcionalidadePermissao, "touch_app", 5);
        listSubMenu.add(subMenuPermissao);

        SegSubMenu subMenuUsuario = new SegSubMenu(menuSeguranca, funcionalidadeUsuario, "account_circle", 6);
        listSubMenu.add(subMenuUsuario);

        menuService.save(menuSeguranca);

        //Permissão
        List<SegPermissao> listPermissao = new ArrayList<SegPermissao>();

        /*
         * PERMISSÃO USUARIO
         * */
        SegPermissao permissaoAdministradorUsuarioConsultar = new SegPermissao();
        permissaoAdministradorUsuarioConsultar.setFuncionalidadeOperacao(usuarioConsultar);
        listPermissao.add(permissaoAdministradorUsuarioConsultar);

        SegPermissao permissaoAdministradorUsuarioVisualizar = new SegPermissao();
        permissaoAdministradorUsuarioVisualizar.setFuncionalidadeOperacao(usuarioVisualizar);
        listPermissao.add(permissaoAdministradorUsuarioVisualizar);

        SegPermissao permissaoAdministradorUsuarioCadastrar = new SegPermissao();
        permissaoAdministradorUsuarioCadastrar.setFuncionalidadeOperacao(usuarioCadastrar);
        listPermissao.add(permissaoAdministradorUsuarioCadastrar);

        SegPermissao permissaoAdministradorUsuarioEditar = new SegPermissao();
        permissaoAdministradorUsuarioEditar.setFuncionalidadeOperacao(usuarioEditar);
        listPermissao.add(permissaoAdministradorUsuarioEditar);

        SegPermissao permissaoAdministradorUsuarioExcluir = new SegPermissao();
        permissaoAdministradorUsuarioExcluir.setFuncionalidadeOperacao(usuarioExcluir);
        listPermissao.add(permissaoAdministradorUsuarioExcluir);

        /*
         * PERMISSÃO PERFIL
         * */

        /*SegPermissao permissaoAdministradorPerfilConsultar = new SegPermissao();
        permissaoAdministradorPerfilConsultar.setFuncionalidadeOperacao(perfilConsultar);
        listPermissao.add(permissaoAdministradorPerfilConsultar);

        SegPermissao permissaoAdministradorPerfilVisualizar = new SegPermissao();
        permissaoAdministradorPerfilVisualizar.setFuncionalidadeOperacao(perfilVisualizar);
        listPermissao.add(permissaoAdministradorPerfilVisualizar);

        SegPermissao permissaoAdministradorPerfilCadastrar = new SegPermissao();
        permissaoAdministradorPerfilCadastrar.setFuncionalidadeOperacao(perfilCadastrar);
        listPermissao.add(permissaoAdministradorPerfilCadastrar);

        SegPermissao permissaoAdministradorPerfilEditar = new SegPermissao();
        permissaoAdministradorPerfilEditar.setFuncionalidadeOperacao(perfilEditar);
        listPermissao.add(permissaoAdministradorPerfilEditar);

        SegPermissao permissaoAdministradorPerfilExcluir = new SegPermissao();
        permissaoAdministradorPerfilExcluir.setFuncionalidadeOperacao(perfilExcluir);
        listPermissao.add(permissaoAdministradorPerfilExcluir);*/

        /*
         * PERMISSÃO OPERAÇÃO
         * */

        SegPermissao permissaoAdministradorOperacaoConsultar = new SegPermissao();
        permissaoAdministradorOperacaoConsultar.setFuncionalidadeOperacao(operacaoConsultar);
        listPermissao.add(permissaoAdministradorOperacaoConsultar);

        SegPermissao permissaoAdministradorOperacaoCadastrar = new SegPermissao();
        permissaoAdministradorOperacaoCadastrar.setFuncionalidadeOperacao(operacaoCadastrar);
        listPermissao.add(permissaoAdministradorOperacaoCadastrar);

        SegPermissao permissaoAdministradorOperacaoVisualizar = new SegPermissao();
        permissaoAdministradorOperacaoVisualizar.setFuncionalidadeOperacao(operacaoVisualizar);
        listPermissao.add(permissaoAdministradorOperacaoVisualizar);

        SegPermissao permissaoAdministradorOperacaoEditar = new SegPermissao();
        permissaoAdministradorOperacaoEditar.setFuncionalidadeOperacao(operacaoEditar);
        listPermissao.add(permissaoAdministradorOperacaoEditar);

        SegPermissao permissaoAdministradorOperacaoExcluir = new SegPermissao();
        permissaoAdministradorOperacaoExcluir.setFuncionalidadeOperacao(operacaoExcluir);
        listPermissao.add(permissaoAdministradorOperacaoExcluir);

        // PERFIL ADMINISTRADOR
        SegPerfil perfilAdministrador = new SegPerfil("Administrador", "Perfil Administrador", listPermissao);


        // OPERACAO
        permissaoAdministradorOperacaoConsultar.setPerfil(perfilAdministrador);
        permissaoAdministradorOperacaoEditar.setPerfil(perfilAdministrador);
        permissaoAdministradorOperacaoExcluir.setPerfil(perfilAdministrador);
        permissaoAdministradorOperacaoCadastrar.setPerfil(perfilAdministrador);
        permissaoAdministradorOperacaoVisualizar.setPerfil(perfilAdministrador);

        // PERFIL
        /*permissaoAdministradorPerfilConsultar.setPerfil(perfilAdministrador);
        permissaoAdministradorPerfilCadastrar.setPerfil(perfilAdministrador);
        permissaoAdministradorPerfilEditar.setPerfil(perfilAdministrador);
        permissaoAdministradorPerfilExcluir.setPerfil(perfilAdministrador);
        permissaoAdministradorPerfilVisualizar.setPerfil(perfilAdministrador);*/

        // USUARIO
        permissaoAdministradorUsuarioCadastrar.setPerfil(perfilAdministrador);
        permissaoAdministradorUsuarioEditar.setPerfil(perfilAdministrador);
        permissaoAdministradorUsuarioExcluir.setPerfil(perfilAdministrador);
        permissaoAdministradorUsuarioConsultar.setPerfil(perfilAdministrador);
        permissaoAdministradorUsuarioVisualizar.setPerfil(perfilAdministrador);


        segPerfilService.save(perfilAdministrador);

        //Usuários
        List<SegUsuario> listUsuario = new ArrayList<SegUsuario>();

        SegUsuario usuarioThiago = new SegUsuario("Thiago", "admin", "thiago@gmail.com", "{bcrypt}$2a$10$PVCoHRfWFAKopAfCvqK1n.t1z9jvCFS4VrQDDs42lWxNXuQqCF.2u", perfilAdministrador);
        listUsuario.add(usuarioThiago);

        SegUsuario usuarioBrenda = new SegUsuario("Brenda", "brenda", "brenda@gmail.com", "{bcrypt}$2a$10$PVCoHRfWFAKopAfCvqK1n.t1z9jvCFS4VrQDDs42lWxNXuQqCF.2u", perfilAdministrador);
        listUsuario.add(usuarioBrenda);

        usuarioService.saveAll(listUsuario);

    }

}
