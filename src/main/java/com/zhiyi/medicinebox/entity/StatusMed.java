package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the status_med database table.
 * 
 */
@Entity
@Table(name="status_med")
@NamedQuery(name="StatusMed.findAll", query="SELECT s FROM StatusMed s")
public class StatusMed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int statusId;

	private String status;

	public StatusMed() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}