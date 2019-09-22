package br.com.tcb.api.service;

import br.com.tcb.api.model.SegFuncionalidade;
import br.com.tcb.api.repository.SegFuncionalidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SegFuncionalidadeService extends GenericService<SegFuncionalidadeRepository, SegFuncionalidade, Long>{

    SegFuncionalidadeService(SegFuncionalidadeRepository repository) {
        super(repository);
    }

    @Override
    public Page<SegFuncionalidade> findByFilter(Map<String, String> params) {
        return null;
    }
}
