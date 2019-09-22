package br.com.tcb.api.service;

import br.com.tcb.api.model.SegPerfil;
import br.com.tcb.api.repository.SegPerfilRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SegPerfilService extends GenericService<SegPerfilRepository, SegPerfil, Long>{

    SegPerfilService(SegPerfilRepository repository) {
        super(repository);
    }

    @Override
    public Page<SegPerfil> findByFilter(Map<String, String> params) {
        return null;
    }

}
