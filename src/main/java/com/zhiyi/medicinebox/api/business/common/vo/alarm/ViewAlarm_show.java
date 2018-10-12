package com.zhiyi.medicinebox.api.business.common.vo.alarm;

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
public class ViewAlarm_show implements Serializable {
	private static final long serialVersionUID = 1L;

	private int alarmId;

	private int alarmLock;

	private Date alarmTime;

	private String dosage;

	private int medId;

	private String medName;

	private String status;

	private int statusId;

	private String url;

	private int userId;
	private String opendId;

	private String alarmDate_show;
	private String alarmTime_show;

	public ViewAlarm_show(ViewAlarm v) {
//		this.alarmId = v.getAlarmId();
//		this.alarmLock = v.getAlarmLock();
		this.alarmTime = v.getAlarmTime();
		this.dosage = v.getDosage();
//		this.medId = v.getMedId();
		this.medName = v.getMedName();
		this.status = v.getStatus();
//		this.statusId = v.getStatusId();
		this.url = v.getUrl();
//		this.userId = v.getUserId();
//		this.opendId = v.getOpenId();

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
                this.alarmDate_show = DateUtils.date2String(alarmTime, "YYY-MM-dd");
            }

			this.alarmTime_show = DateUtils.date2String(alarmTime, "HH：mm");
		}
	}

}