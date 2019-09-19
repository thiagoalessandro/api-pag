package br.com.intelector.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.intelector.api.model.SegMenu;
import br.com.intelector.api.model.enums.SituacaoRegistroEnum;

@Repository
public interface SegMenuRepository extends GenericRepository<SegMenu, Long>{
}
