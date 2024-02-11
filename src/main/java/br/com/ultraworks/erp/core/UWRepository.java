package br.com.ultraworks.erp.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UWRepository<Entity, Id> extends JpaRepository<Entity, Id>, JpaSpecificationExecutor<Entity> {

}
