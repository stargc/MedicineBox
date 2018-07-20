//package com.zhiyi.medicinebox.entity.vo;
//
//import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
//import com.zhiyi.medicinebox.util.tools.DateUtil;
//
//import java.io.Serializable;
//import java.util.Calendar;
//import java.util.Date;
//
//
///**
// * The persistent class for the view_alarm database table.
// *
// */
//public class ViewAlarm_show implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	private int alarmId;
//
//	private int alarmLock;
//
//	private Date alarmTime;
//
//	private String dosage;
//
//	private int medId;
//
//	private String medName;
//
//	private String status;
//
//	private int statusId;
//
//	private String url;
//
//	private int userId;
//
//	private String alarmDate_show;
//	private String alarmTime_show;
//
//	public ViewAlarm_show(ViewAlarm v) {
//		this.alarmId = v.getAlarmId();
//		this.alarmLock = v.getAlarmLock();
//		this.alarmTime = v.getAlarmTime();
//		this.dosage = v.getDosage();
//		this.medId = v.getMedId();
//		this.medName = v.getMedName();
//		this.status = v.getStatus();
//		this.statusId = v.getStatusId();
//		this.url = v.getUrl();
//		this.userId = v.getUserId();
//
//		long oneday = 24 * 60 * 60 * 1000;
//		if (alarmDate != null) {
//			long des = Math.abs(System.currentTimeMillis() - alarmDate.getTime());
//			Calendar alarmDateCalendar = Calendar.getInstance();
//			alarmDateCalendar.setTime(alarmDate);
//			if (des <= oneday && alarmDateCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
//				this.alarmDate_show = "今天";
//			} else if (des <= 2 * oneday && alarmDateCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1) {
//				this.alarmDate_show = "昨天";
//			} else {
//				this.alarmDate_show = DateUtil.date2String(alarmDate, "YYY-MM-dd");
//			}
//		}
//		if (alarmTime != null) {
//			this.alarmTime_show = DateUtil.date2String(alarmTime, "HH：mm");
//		}
//	}
//
//	public Date getAlarmDate() {
//		return this.alarmDate;
//	}
//
//	public void setAlarmDate(Date alarmDate) {
//		this.alarmDate = alarmDate;
//	}
//
//	public int getAlarmId() {
//		return this.alarmId;
//	}
//
//	public void setAlarmId(int alarmId) {
//		this.alarmId = alarmId;
//	}
//
//	public int getAlarmLock() {
//		return this.alarmLock;
//	}
//
//	public void setAlarmLock(int alarmLock) {
//		this.alarmLock = alarmLock;
//	}
//
//	public Date getAlarmTime() {
//		return this.alarmTime;
//	}
//
//	public void setAlarmTime(Date alarmTime) {
//		this.alarmTime = alarmTime;
//	}
//
//
//	public String getDosage() {
//		return dosage;
//	}
//
//	public void setDosage(String dosage) {
//		this.dosage = dosage;
//	}
//
//	public int getMedId() {
//		return this.medId;
//	}
//
//	public void setMedId(int medId) {
//		this.medId = medId;
//	}
//
//	public String getMedName() {
//		return this.medName;
//	}
//
//	public void setMedName(String medName) {
//		this.medName = medName;
//	}
//
//	public String getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public int getStatusId() {
//		return this.statusId;
//	}
//
//	public void setStatusId(int statusId) {
//		this.statusId = statusId;
//	}
//
//	public String getUrl() {
//		return this.url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public int getUserId() {
//		return this.userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//
//	public String getAlarmDate_show() {
//		return alarmDate_show;
//	}
//
//	public void setAlarmDate_show(String alarmDate_show) {
//		this.alarmDate_show = alarmDate_show;
//	}
//
//	public String getAlarmTime_show() {
//		return alarmTime_show;
//	}
//
//	public void setAlarmTime_show(String alarmTime_show) {
//		this.alarmTime_show = alarmTime_show;
//	}
//
//}