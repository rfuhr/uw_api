package br.com.ultraworks.erp.api.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.api.seguranca.domain.modulo.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long>{

}
