package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the view_alarm database table.
 * 
 */
@Entity
@Table(name="view_alarm")
@NamedQuery(name="ViewAlarm.findAll", query="SELECT v FROM ViewAlarm v")
public class ViewAlarm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date alarmDate;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int alarmId;

	private int alarmLock;

	@Temporal(TemporalType.TIME)
	private Date alarmTime;

	private String dosage;

	private int medId;

	private String medName;

	private String status;

	private int statusId;

	private String url;

	private int userId;
	
	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public ViewAlarm() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	public int getAlarmLock() {
		return alarmLock;
	}

	public void setAlarmLock(int alarmLock) {
		this.alarmLock = alarmLock;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
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

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}