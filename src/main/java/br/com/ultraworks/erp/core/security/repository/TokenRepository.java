package br.com.ultraworks.erp.core.security.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultraworks.erp.core.security.domain.token.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, BigDecimal> {
	void deleteByOwnerId(BigDecimal id);
}
