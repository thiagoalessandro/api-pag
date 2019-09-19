package br.com.intelector.api.service;

import br.com.intelector.api.model.SegPerfil;
import br.com.intelector.api.repository.SegPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
