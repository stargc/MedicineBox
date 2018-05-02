package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the dosage database table.
 * 
 */
@Entity
@Table(name="dosage")
@NamedQuery(name="Dosage.findAll", query="SELECT i FROM Dosage i")
public class Dosage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int dosageId;

	private String dosage;

	private int medId;

	private int statusId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public Dosage() {
	}

	public int getDosageId() {
		return dosageId;
	}

	public void setDosageId(int dosageId) {
		this.dosageId = dosageId;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int medId) {
		this.medId = medId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}