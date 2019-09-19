package br.com.intelector.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.intelector.api.model.SegFuncionalidade;

@Repository
public interface SegFuncionalidadeRepository extends GenericRepository<SegFuncionalidade, Long>{

}
