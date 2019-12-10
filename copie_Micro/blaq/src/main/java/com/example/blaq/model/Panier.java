package com.example.blaq.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "panier")
@EntityListeners(AuditingEntityListener.class)
public class Panier implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    private String description;
	
	@NotNull
    private Float montant;
	
	@NotNull
	private Boolean paye;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public Boolean getPaye() {
		return paye;
	}

	public void setPaye(Boolean paye) {
		this.paye = paye;
	}
	
	

}
