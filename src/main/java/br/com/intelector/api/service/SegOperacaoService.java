package br.com.intelector.api.service;

import br.com.intelector.api.controller.utils.StringUtils;
import br.com.intelector.api.model.SegOperacao;
import br.com.intelector.api.model.enums.SituacaoRegistroEnum;
import br.com.intelector.api.repository.SegOperacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SegOperacaoService extends GenericService<SegOperacaoRepository, SegOperacao, Long>{

	SegOperacaoService(SegOperacaoRepository repository) {
		super(repository);
	}

	public Page<SegOperacao> findByFilter(Map<String, String> params) {
		return repository.findByNomeIgnoreCaseContainingAndSituacaoOrderByIdDesc(StringUtils.noNull(params.get("nome")),
																				 SituacaoRegistroEnum.Ativo,
																				 generatePageable(params));
	}
	
}
