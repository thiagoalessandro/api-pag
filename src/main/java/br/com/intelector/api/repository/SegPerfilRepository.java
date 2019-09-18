package br.com.intelector.api.repository;

import br.com.intelector.api.model.SegPerfil;
import br.com.intelector.api.model.SegUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegPerfilRepository extends GenericRepository<SegPerfil, Long>{
}
