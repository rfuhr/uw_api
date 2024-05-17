package br.com.ultraworks.erp.core.entity.listener;

import java.io.Serializable;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.core.entity.UWEntityBase;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.service.UniqueValidationService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Service
public class BaseListener implements Serializable {

	UniqueValidationService unique;
	
	private static final long serialVersionUID = 4506029740189227365L;


	public BaseListener(@Lazy UniqueValidationService unique) {
		super();
		this.unique = unique;
	}

	@PrePersist
	public void prePersist(UWEntityBase entity) throws Exception {
		this.unique.verificarUnicidade(entity, true);
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		entity.setCriadoPor(user.getId().longValue());
//		FieldsTransform.transform(entity);
	}

	@PreUpdate
	public void preUpdate(UWEntityBase entity) throws Exception {
		if (!entity.isUpdated() ) {
			entity.setUpdated(true);
			this.unique.verificarUnicidade(entity, false);
			CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			entity.setAlteradoPor(user.getId().longValue());
//			FieldsTransform.transform(entity);
		}
	}
}
