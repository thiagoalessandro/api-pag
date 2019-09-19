package br.com.intelector.api.service;

import br.com.intelector.api.controller.utils.StringUtils;
import br.com.intelector.api.model.AbstractEntity;
import br.com.intelector.api.model.enums.SituacaoRegistroEnum;
import br.com.intelector.api.repository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public abstract class GenericService<R extends GenericRepository, T extends AbstractEntity, ID> {

    R repository;

    GenericService(R repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return (T) repository.save(entity);
    }

    public Iterable<? extends T> saveAll(Iterable<? extends T> entities) {
        return repository.saveAll(entities);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public void deleteLogical(T entity) {
        entity.setSituacao(SituacaoRegistroEnum.Excluido);
        repository.save(entity);
    }

    public Iterable<T> findByAtivo() {
        return repository.findBySituacao(SituacaoRegistroEnum.Ativo);
    }

    public abstract Page<T> findByFilter(Map<String, String> params);

    public Pageable generatePageable(Map<String, String> params){
        if (StringUtils.noNull(params.get("page")).isEmpty() || StringUtils.noNull(params.get("count")).isEmpty())
            return null;
        return PageRequest.of(Integer.parseInt(params.get("page")), Integer.parseInt(params.get("count")));
    }

}
