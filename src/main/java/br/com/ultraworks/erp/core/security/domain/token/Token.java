package br.com.ultraworks.erp.core.security.domain.token;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultraworks.erp.core.security.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

	@Id
	@SequenceGenerator(name = "TOKEN_ID", sequenceName = "SEQ_TOKEN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOKEN_ID")
	@Column(name = "id")
	private BigDecimal id;

	@ManyToOne(targetEntity = User.class)
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User owner;
	
	@Column(name = "token")
	private String token;

	@Column(name = "refresh_token")
	private String refreshToken;
	
	@Column(name = "date_create")
	@CreationTimestamp
	private LocalDateTime criadoEm;

	public Token(User owner) {
		super();
		this.owner = owner;
		this.token = "not defined";
		this.refreshToken = "not defined";
		this.criadoEm = LocalDateTime.now();
	}
	
	
}
