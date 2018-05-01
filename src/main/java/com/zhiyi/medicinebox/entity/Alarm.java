package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the alarm database table.
 * 
 */
@Entity
@Table(name="alarm")
@NamedQuery(name="Alarm.findAll", query="SELECT a FROM Alarm a")
public class Alarm implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int alarmId;

	@Temporal(TemporalType.DATE)
	private Date alarmDate;

	private int alarmLock;

	@Temporal(TemporalType.TIME)
	private Date alarmTime;

	private int dosageId;

	private int userId;
	
	private int statusId;
	
	private int issend;

	public Alarm() {
	}

	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
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

	public int getDosageId() {
		return dosageId;
	}

	public void setDosageId(int dosageId) {
		this.dosageId = dosageId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getIssend() {
		return issend;
	}

	public void setIssend(int issend) {
		this.issend = issend;
	}
}