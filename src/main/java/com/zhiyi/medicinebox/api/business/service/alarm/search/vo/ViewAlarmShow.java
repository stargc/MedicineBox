package com.zhiyi.medicinebox.api.business.service.alarm.search.vo;

import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import com.zhiyi.medicinebox.api.infrastructure.util.DateUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the view_alarm database table.
 *
 */
@Data
public class ViewAlarmShow implements Serializable {
	private static final long serialVersionUID = 1L;

	private int alarmId;

	private Date alarmTime;

	private String dosage;

	private String medName;

	private String status;

	private String url;

	private String alarmDateShow;
	private String alarmTimeShow;

	public ViewAlarmShow(ViewAlarm v) {
		this.alarmId = v.getAlarmId();
		this.alarmTime = v.getAlarmTime();
		this.dosage = v.getDosage();
		this.medName = v.getMedName();
		this.status = v.getStatus();
		this.url = v.getUrl();

		long oneday = 24 * 60 * 60 * 1000;
		if (alarmTime != null) {
            long des = Math.abs(System.currentTimeMillis() - alarmTime.getTime());
            Calendar alarmDateCalendar = Calendar.getInstance();
            alarmDateCalendar.setTime(alarmTime);
            if (des <= oneday && alarmDateCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                this.alarmDateShow = "今天";
            } else if (des <= 2 * oneday && alarmDateCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1) {
                this.alarmDateShow = "昨天";
            } else {
                this.alarmDateShow = DateUtils.date2String(alarmTime, "YYY-MM-dd");
            }

			this.alarmTimeShow = DateUtils.date2String(alarmTime, "HH：mm");
		}
	}

}