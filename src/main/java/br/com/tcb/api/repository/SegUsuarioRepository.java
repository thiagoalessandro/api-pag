package br.com.tcb.api.repository;

import br.com.tcb.api.model.SegUsuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegUsuarioRepository extends GenericRepository<SegUsuario, Long>{
	Optional<SegUsuario> findByLogin(String login);
}
