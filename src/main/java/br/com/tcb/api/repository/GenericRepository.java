package br.com.tcb.api.repository;

import br.com.tcb.api.model.enums.SituacaoRegistroEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {

    Iterable<T> findBySituacao(SituacaoRegistroEnum situacao);

}
