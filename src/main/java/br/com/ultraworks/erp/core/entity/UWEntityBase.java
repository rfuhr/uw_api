package br.com.ultraworks.erp.core.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.ultraworks.erp.core.entity.listener.BaseListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EntityListeners(BaseListener.class)
@EqualsAndHashCode
public abstract class UWEntityBase {

	@Column(name = "user_create")
	private long criadoPor;

	@Column(name = "date_create")
	@CreationTimestamp
	private LocalDateTime criadoEm;

	@Column(name = "user_update")
	private long alteradoPor;

	@Column(name = "date_update")
	@UpdateTimestamp
	private LocalDateTime alteradoEm;

	@Transient
	private boolean isUpdated = false;

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public LocalDateTime getAlteradoEm() {
		return alteradoEm;
	}

	public void setAlteradoEm(LocalDateTime alteradoEm) {
		this.alteradoEm = alteradoEm;
	}

	public long getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(long criadoPor) {
		this.criadoPor = criadoPor;
	}

	public long getAlteradoPor() {
		return alteradoPor;
	}

	public void setAlteradoPor(long alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
