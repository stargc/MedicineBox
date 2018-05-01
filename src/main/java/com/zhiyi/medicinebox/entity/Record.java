package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the record database table.
 * 
 */
@Entity
@Table(name="record")
@NamedQuery(name="Record.findAll", query="SELECT r FROM Record r")
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordId;

	private int alarmId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date alarmDate;

	private Date alarmTime;
	
	private Date createDate;

	private String dosage;

	private String medName;

	private int statusId;

	private String url;

	private int userId;
	
	private String type;

	public Record() {
	}
	
	public Record(ViewAlarm alarm, Date date) {
		this.alarmDate = alarm.getAlarmDate();
		this.alarmId = alarm.getAlarmId();
		this.alarmTime = alarm.getAlarmTime();
		this.createDate = date;
		this.dosage = alarm.getDosage();
		this.medName = alarm.getMedName();
		this.statusId = alarm.getStatusId();
		this.userId = alarm.getUserId();
	}

	public int getRecordId() {
		return this.recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
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

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getMedName() {
		return this.medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
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

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}