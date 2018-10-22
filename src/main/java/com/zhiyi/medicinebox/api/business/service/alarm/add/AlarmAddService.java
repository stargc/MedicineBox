package com.zhiyi.medicinebox.api.business.service.alarm.add;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.common.vo.alarm.AlarmAddReq;
import com.zhiyi.medicinebox.api.business.strategy.AlarmStrategy;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Alarm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Medicine;
import com.zhiyi.medicinebox.api.infrastructure.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmAddService.java, v 0.1 2018-10-17 17:37 star Exp $$
 */
@Service
@Slf4j
public class AlarmAddService {

    @Value("default_alarm_image_url")
    private String defaultAlarmImageUrl;

    @Value("file_save_path_liunx")
    private String fileSavePathLiunx;

    @Resource
    private AlarmStrategy alarmStrategy;

    public BaseResponse responseValue(AlarmAddReq alarmAddReq, MultipartFile file) {

        log.info("用户 " + alarmAddReq.getUserId() + " add alarm: medicine name = " + alarmAddReq.getMedName() +
                "；alarm time = " + alarmAddReq.getAlarmTime() + " 开始时间：" + alarmAddReq.getStartDate() + " 结束时间：" + alarmAddReq.getEndDate());
        BaseResponse resp = new BaseResponse();
        Date date = new Date();

        String imgPath = defaultAlarmImageUrl;
        if (file != null) {
            SimpleDateFormat sfPath = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sfName = new SimpleDateFormat("hhmmssSSSS");
            StringBuilder path = new StringBuilder(fileSavePathLiunx)
                    .append(sfPath.format(date))
                    .append("/").append(alarmAddReq.getUserId()).append(sfName.format(date));
            imgPath = path.toString();
            try {
                FileUtils.saveFile(file, path.toString());
            } catch (UnsupportedEncodingException e1) {
                log.error("用户 " + alarmAddReq.getUserId() + ExceptionUtils.getStackTrace(e1));
                resp.setResultCode(BaseResponse.FAILED);
                resp.setResultMsg("文件保存错误");
                return resp;
            }
        }

        Medicine medicine = new Medicine();
        medicine.setMedName(alarmAddReq.getMedName());
        medicine.setUrl(imgPath);
        Alarm alarm = new Alarm();
        alarm.setStatusId(alarmAddReq.getStatusId());
        alarm.setDosage(alarmAddReq.getDosage());
        alarm.setUserId(alarmAddReq.getUserId());
        alarm.setAlarmTime(alarmAddReq.getAlarmTime());

        boolean isDone = alarmStrategy.addViewAlarm(medicine, alarm, alarmAddReq.getStartDate(), alarmAddReq.getEndDate());
        resp.setResultCode(isDone ? BaseResponse.SUCCESS:BaseResponse.FAILED);
        return resp;
    }
}
