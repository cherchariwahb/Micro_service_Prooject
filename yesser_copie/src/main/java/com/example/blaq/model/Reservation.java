package com.example.blaq.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 
	
    @Column(name = "datedeb")
    private Date datedeb;
	
	
    @Column(name = "datefin")
    private Date datefin;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipement_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Equipement equipement;
    
    
	public Reservation(Long id, Date datedeb, Date datefin, Equipement equipement) {
		super();
		this.id = id;
		this.datedeb = datedeb;
		this.datefin = datefin;
		this.equipement = equipement;
	}
	public Reservation() {
		super();
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatedeb() {
		return datedeb;
	}

	public void setDatedeb(Date datedeb) {
		this.datedeb = datedeb;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public Equipement getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}

	

}
