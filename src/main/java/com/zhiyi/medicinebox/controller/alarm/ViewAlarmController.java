package com.zhiyi.medicinebox.controller.alarm;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.po.alarm.Alarm;
import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
import com.zhiyi.medicinebox.entity.po.alarm.ViewRecord;
import com.zhiyi.medicinebox.entity.po.base.Medicine;
import com.zhiyi.medicinebox.entity.vo.alarm.AlarmAddReq;
import com.zhiyi.medicinebox.entity.vo.alarm.ViewAlarm_show;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.service.alarm.RecordService;
import com.zhiyi.medicinebox.service.base.MedicineService;
import com.zhiyi.medicinebox.strategy.AlarmStrategy;
import com.zhiyi.medicinebox.util.ResponseUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.FileUtils;
import com.zhiyi.medicinebox.validate.AlarmVal;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/viewalarm")
public class ViewAlarmController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Resource
    private AlarmService alarmService;

    @Resource
    private MedicineService medicineService;

    @Resource
    private RecordService recordService;

    @Resource
    private AlarmStrategy alarmStrategy;

    @Resource
    private AlarmVal alarmVal;

    @RequestMapping(value = ("/add"), method = RequestMethod.POST)
    @ResponseBody
    public ParmResponse add(AlarmAddReq alarmAddReq,
                            @RequestParam(value = "file", required = false) MultipartFile file) {
        String val = alarmVal.addVal(alarmAddReq);
        if (val != null) {
            logger.error("新建提醒参数错误 + " + val);
            return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, val);
        }
//        try {
//            alarmAddReq.setMedName(new String(alarmAddReq.getMedName().getBytes("iso8859-1"), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            logger.info("用户 " + alarmAddReq.getUserId() + " add alarm 药品名称解析错误 ");
//        }
        logger.info("用户 " + alarmAddReq.getUserId() + " add alarm: medicine name = " + alarmAddReq.getMedName() + "；alarm time = " + alarmAddReq.getAlarmTime());
        Date date = new Date();

        String imgPath = new StringBuffer(ConfigUtil.getValue("default_alarm_image_url")).toString();
        if (file != null) {
            SimpleDateFormat sf_path = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sf_name = new SimpleDateFormat("hhmmssSSSS");
            StringBuffer path = new StringBuffer(ConfigUtil.getValue("file_save_path_liunx"))
                    .append(sf_path.format(date))
                    .append("/").append(alarmAddReq.getUserId()).append(sf_name.format(date));
            imgPath = path.toString();
            try {
                FileUtils.saveFile(file, path.toString());
            } catch (UnsupportedEncodingException e1) {
                logger.error("用户 " + alarmAddReq.getUserId() + ExceptionUtils.getStackTrace(e1));
                return ResponseUtils.getErrorResponse(ResultCode.RESULT_FILE_SAVE_ERROR, "文件保存错误");
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

        return ResponseUtils.getBooleanResponse(isDone, Alarm.class.toString());
    }

    @RequestMapping("/findAlarmByUserAndDate")
    @ResponseBody
    public ParmResponse findAlarmByUserAndDate(int userId, Date date) {
        List<ViewAlarm_show> result = new ArrayList<>();
        if (date == null) {
            date = new Date();
        }
        List<ViewAlarm> list = alarmService.findViewAlarmByUserId(userId, date);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                result.add(new ViewAlarm_show(list.get(i)));
            }
            return ResponseUtils.getListResponse(result.toArray(), ViewRecord.class.toString());
        }
        return ResponseUtils.getErrorResponse(ResultCode.RESULT_NULL, "数据为空");
    }

    /***
     * @param statusId: 《1：未服药   2：已服药   3：跳过   4：其他》
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public ParmResponse updateStatus(int alarmId, int statusId) {
        boolean isDone = false;
        Alarm alarm = alarmService.findByid(alarmId);
        if (alarm == null){
            return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "未找到对应的用药提醒");
        }
        if (Integer.compare(alarm.getStatusId(),statusId) == 0) {
            return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "状态未改变");
        }
        logger.info("用户[" + alarm.getUserId() + "]更改提醒[" + alarmId + "]状态为" + statusId);

        switch (statusId) {
            case 1://未服药
                isDone = alarmService.updateStatus(alarmId, statusId);
                break;
            case 2://已服药
                if (Integer.compare(alarm.getStatusId(),1) != 0){
                    return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "该提醒状态不是 未服药");
                }
                isDone = alarmStrategy.takeMedicine(alarmId, statusId);
                break;
            case 3://跳过
                if (Integer.compare(alarm.getStatusId(),1) != 0){
                    return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "该提醒状态不是 未服药");
                }
                isDone = alarmStrategy.skipMedicine(alarmId, statusId);
                break;
            case 4://其他
                isDone = alarmService.updateStatus(alarmId, statusId);
                break;
            default:
                return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "未找到合适的状态");
        }
        return ResponseUtils.getBooleanResponse(isDone, ViewAlarm.class.toString());
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ParmResponse findById(int alarmId) {
        ViewAlarm alarm = alarmService.findViewAlarmById(alarmId);
        if (alarm != null) {
            return ResponseUtils.getBeanResponse(alarm, ViewAlarm.class.toString());
        }
        return ResponseUtils.getErrorResponse(ResultCode.RESULT_NULL, "数据为空");
    }

}
