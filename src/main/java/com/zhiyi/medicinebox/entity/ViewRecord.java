package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the view_recore database table.
 * 
 */
@Entity
@Table(name="view_record")
@NamedQuery(name="ViewRecord.findAll", query="SELECT v FROM ViewRecord v")
public class ViewRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alarmDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alarmTime;

	private String dosage;

	private String medName;
	
	private Date createDate;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordId;

	private int alarmId;

	private int userId;

	private String status;

	private int statusId;

	private String url;
	
	private String type;
	
	private String userName;

	public ViewRecord() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getAlarmDate() {
		return this.alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public Date getAlarmTime() {
		return this.alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getMedName() {
		return this.medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public int getRecordId() {
		return this.recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}


	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}