package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the medicine database table.
 * 
 */
@Entity
@Table(name="medicine")
@NamedQuery(name="Medicine.findAll", query="SELECT m FROM Medicine m")
public class Medicine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int medId;

	private String instructions;

	private String malady;

	private String medName;

	private String url;

	public Medicine() {
	}

	public int getMedId() {
		return this.medId;
	}

	public void setMedId(int medId) {
		this.medId = medId;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getMalady() {
		return this.malady;
	}

	public void setMalady(String malady) {
		this.malady = malady;
	}

	public String getMedName() {
		return this.medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}