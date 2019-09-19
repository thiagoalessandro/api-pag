package br.com.intelector.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.intelector.api.model.SegOperacao;
import br.com.intelector.api.model.enums.SituacaoRegistroEnum;

@Repository
public interface SegOperacaoRepository extends GenericRepository<SegOperacao, Long>{
	
	Page<SegOperacao> findByNomeIgnoreCaseContainingAndSituacaoOrderByIdDesc(String nome, SituacaoRegistroEnum situacaoRegistroEnum, Pageable pages);
	
}
