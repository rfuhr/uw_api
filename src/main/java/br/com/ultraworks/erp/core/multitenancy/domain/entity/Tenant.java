package br.com.ultraworks.erp.core.multitenancy.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Tenant {

	@Id
	@SequenceGenerator(name = "TENANT_ID", sequenceName = "tenantidseq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TENANT_ID")
	@Column(name = "id")
	private BigDecimal id;

	@Size(max = 255)
	@Column(name = "datasource_name")
	private String db;

    @Size(max = 255)
    @Column(name = "datasource_username")
    private String username;
    
    @Size(max = 255)
    @Column(name = "datasource_password")
    private String password;
    
	@Column(name = "tenant_id")
    private String tenantId;
  
}