package br.com.tcb.api.service;

import br.com.tcb.api.model.*;
import br.com.tcb.api.repository.SegMenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SegMenuService extends GenericService<SegMenuRepository, SegMenu, Long>{

	SegMenuService(SegMenuRepository repository) {
		super(repository);
	}

	@Override
	public Page<SegMenu> findByFilter(Map<String, String> params) {
		return null;
	}

	public List<SegMenu> findByUserAutenticated(SegUsuario segUsuario) {
		Iterable<SegMenu> listMenuAtivo = findByAtivo();
		List<SegMenu> listMenuAutorized = new ArrayList<>();
		StreamSupport.stream(listMenuAtivo.spliterator(), false).forEach(segMenu -> {
			if (isMenuPermited(segMenu, segUsuario.getPerfil().getListPermissao())) {
				segMenu.setListSubMenu(listSegSubMenuAutorized(segMenu, segUsuario.getPerfil().getListPermissao()));
				listMenuAutorized.add(segMenu);
			}
		});
		return listMenuAutorized;
	}

	private List<SegSubMenu> listSegSubMenuAutorized(SegMenu segMenu, List<SegPermissao> listPermissao) {
		return segMenu.getListSubMenu()
				.stream()
				.filter(segSubMenu -> isSubMenuPermited(segSubMenu, listPermissao))
				.map(segSubMenu -> mapOperacaoAutorized(segSubMenu, listPermissao))
				.collect(Collectors.toList());
	}

	private boolean isMenuPermited(SegMenu segMenu, List<SegPermissao> listPermissao) {
		return segMenu.getListSubMenu()
				.stream()
				.anyMatch(segSubMenu -> isSubMenuPermited(segSubMenu, listPermissao));
	}

	private boolean isSubMenuPermited(SegSubMenu segSubMenu, List<SegPermissao> listPermissao) {
		return listPermissao
				.stream().anyMatch(segPermissao -> segPermissao.getFuncionalidadeOperacao().getFuncionalidade().getSigla().equals(segSubMenu.getFuncionalidade().getSigla()));
	}

	private SegSubMenu mapOperacaoAutorized(SegSubMenu segSubMenu, List<SegPermissao> listPermissao) {
		List<SegFuncionalidadeOperacao> listFuncionalidadeOperacao = segSubMenu.getFuncionalidade().getListFuncionalidadeOperacao()
				.stream()
				.filter(segFuncionalidadeOperacao -> isOperacaoAutorized(segFuncionalidadeOperacao, listPermissao))
				.collect(Collectors.toList());
		segSubMenu.getFuncionalidade().setListFuncionalidadeOperacao(listFuncionalidadeOperacao);
		return segSubMenu;
	}

	private boolean isOperacaoAutorized(SegFuncionalidadeOperacao segFuncionalidadeOperacao, List<SegPermissao> listPermissao) {
		return listPermissao
				.stream()
				.anyMatch(segPermissao -> segPermissao.getFuncionalidadeOperacao().getFuncionalidade().getSigla().equals(segFuncionalidadeOperacao.getFuncionalidade().getSigla()) &&
						segPermissao.getFuncionalidadeOperacao().getOperacao().getSigla().equals(segFuncionalidadeOperacao.getOperacao().getSigla()));
	}


}
