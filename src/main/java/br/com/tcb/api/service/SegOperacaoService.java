package br.com.tcb.api.service;

import br.com.tcb.api.controller.utils.StringUtils;
import br.com.tcb.api.model.SegOperacao;
import br.com.tcb.api.model.enums.SituacaoRegistroEnum;
import br.com.tcb.api.repository.SegOperacaoRepository;
import org.springframework.data.domain.Page;
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
