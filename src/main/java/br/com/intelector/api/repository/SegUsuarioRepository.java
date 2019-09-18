package br.com.intelector.api.repository;

import br.com.intelector.api.model.SegUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegUsuarioRepository extends GenericRepository<SegUsuario, Long>{
	Optional<SegUsuario> findByLogin(String login);
}
