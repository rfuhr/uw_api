package br.com.ultraworks.erp.core.security.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.core.security.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {

	Optional<User> findByUsername(String username);

}