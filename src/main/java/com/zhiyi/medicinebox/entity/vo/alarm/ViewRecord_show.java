package com.zhiyi.medicinebox.entity.vo.alarm;

import com.zhiyi.medicinebox.entity.po.alarm.ViewRecord;
import com.zhiyi.medicinebox.util.tools.DateUtil;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the view_recore database table.
 *
 */
public class ViewRecord_show implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date alarmTime;

	private String dosage;

	private String medName;

	private Date createDate;

	private int recordId;

	private int alarmId;

	private int userId;

	private String status;

	private int statusId;

	private String url;

	private String type;

	private String alarmDate_show;
	private String alarmTime_show;

	public ViewRecord_show(ViewRecord v) {
//		this.alarmId = v.getAlarmId();
		this.alarmTime = v.getAlarmTime();
		this.dosage = v.getDosage();
		this.medName = v.getMedName();
		this.status = v.getStatus();
//		this.statusId = v.getStatusId();
		this.url = v.getUrl();
//		this.userId = v.getUserId();
//		this.recordId = v.getRecordId();
//		this.createDate = v.getCreateDate();
//		this.type = v.getType();

		long oneday = 24 * 60 * 60 * 1000;
		if (alarmTime != null) {
            long des = Math.abs(System.currentTimeMillis() - alarmTime.getTime());
            Calendar alarmDateCalendar = Calendar.getInstance();
            alarmDateCalendar.setTime(alarmTime);
            if (des <= oneday && alarmDateCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                this.alarmDate_show = "今天";
            } else if (des <= 2 * oneday && alarmDateCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1) {
                this.alarmDate_show = "昨天";
            } else {
                this.alarmDate_show = DateUtil.date2String(alarmTime, "YYY-MM-dd");
            }
			this.alarmTime_show = DateUtil.date2String(alarmTime, "HH:mm");
		}
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

	public String getAlarmDate_show() {
		return alarmDate_show;
	}

	public void setAlarmDate_show(String alarmDate_show) {
		this.alarmDate_show = alarmDate_show;
	}

	public String getAlarmTime_show() {
		return alarmTime_show;
	}

	public void setAlarmTime_show(String alarmTime_show) {
		this.alarmTime_show = alarmTime_show;
	}

}