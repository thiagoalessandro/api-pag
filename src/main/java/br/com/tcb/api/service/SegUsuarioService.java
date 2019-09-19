package br.com.intelector.api.service;

import br.com.intelector.api.model.SegUsuario;
import br.com.intelector.api.repository.GenericRepository;
import br.com.intelector.api.repository.SegUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class SegUsuarioService extends GenericService<SegUsuarioRepository, SegUsuario, Long> {

	SegUsuarioService(SegUsuarioRepository repository) {
		super(repository);
	}

	@Override
	public Page<SegUsuario> findByFilter(Map<String, String> params) {
		return null;
	}

	public Optional<SegUsuario> findByLogin(String login) {
		return repository.findByLogin(login);
	}
}
