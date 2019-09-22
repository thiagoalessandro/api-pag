package br.com.tcb.api.service;

import br.com.tcb.api.model.SegUsuario;
import br.com.tcb.api.repository.SegUsuarioRepository;
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
