package br.com.tcb.api.repository;

import br.com.tcb.api.model.SegOperacao;
import br.com.tcb.api.model.enums.SituacaoRegistroEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SegOperacaoRepository extends GenericRepository<SegOperacao, Long>{
	
	Page<SegOperacao> findByNomeIgnoreCaseContainingAndSituacaoOrderByIdDesc(String nome, SituacaoRegistroEnum situacaoRegistroEnum, Pageable pages);
	
}
