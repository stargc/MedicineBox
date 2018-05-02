package com.zhiyi.medicinebox.controller.alarm;

import com.ibm.icu.util.Calendar;
import com.zhiyi.medicinebox.constant.Consts;
import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.*;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.*;
import com.zhiyi.medicinebox.util.ResponseUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/viewalarm")
public class ViewAlarmController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private AlarmService alarmService;
	
	@Resource
	private DosageService dosageService;
	
	@Resource
	private MedicineService medicineService;
	
	@Resource
	private ViewAlarmService viewAlarmService;
	
	@Resource
	private RecordService recordService;
	
	@Resource
	private PlatformTransactionManager transactionManager;
	
	@RequestMapping(value=("/add"),method=RequestMethod.POST)
	@ResponseBody
	public ParmResponse add(Alarm alarm, Dosage dosage, Medicine medicine, Date startDate, Date endDate,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		String val = viewAlarmService.addVal(alarm,dosage,medicine,startDate,endDate);
		if (val != null){
			return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, val,request);
		}
		boolean isDone = false;
		logger.info( alarm.getUserId() + " add alarm: medicine name = " + medicine.getMedName() + "；alarm time = " + alarm.getAlarmTime());
		Date date = new Date();
		String imgPath = null;
		if (file != null) {
			SimpleDateFormat sf_path = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sf_name = new SimpleDateFormat("hhmmssSSSS");
			StringBuffer path = new StringBuffer(ConfigUtil.getValue("file_save_path_liunx"))
					.append("/").append(sf_path.format(date))
					.append("/").append(alarm.getUserId()).append(sf_name.format(date));
			imgPath = path.toString();
			try {
				FileUtils.saveFile(file, path.toString());
			} catch (UnsupportedEncodingException e1) {
				logger.error(ExceptionUtils.getStackTrace(e1));
				return ResponseUtils.getErrorResponse(ResultCode.RESULT_FILE_SAVE_ERROR, "文件保存错误", request);
			}
		}

		if (medicine != null && medicine.getMedName() != null) {
			if (dosage != null && dosage.getDosage() != null) {
				if (alarm != null && alarm.getAlarmTime() != null && startDate != null && endDate != null) {
					DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
				    defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				    TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
				    try {
				    	medicine.setUrl(imgPath);
				    	if (medicineService.add(medicine)) {
				    		dosage.setMedId(medicine.getMedId());
				    		if (dosageService.add(dosage)) {
				    			int days = Long.valueOf((endDate.getTime() - startDate.getTime())/1000/60/60/24).intValue() + 1;
				    			Calendar calendar = Calendar.getInstance();
				    			calendar.setTime(startDate);
								if (alarm.getStatusId() == 0){
									//默认状态为 未服药 状态
									alarm.setStatusId(1);
								}
				    			//添加当天记录
								alarm.setAlarmDate(calendar.getTime());
				    			alarm.setDosageId(dosage.getDosageId());
				    			isDone = alarmService.add(alarm);
				    			for (int i = 1; i < days; i++) {
									calendar.add(Calendar.DAY_OF_MONTH, 1);
									alarm.setAlarmDate(calendar.getTime());
					    			alarm.setDosageId(dosage.getDosageId());
					    			alarm.setCreateDate(date);
					    			isDone = alarmService.add(alarm);
								}
							}
						}
				        transactionManager.commit(status);
				    } catch (Exception e) {
				        transactionManager.rollback(status);
						logger.error(ExceptionUtils.getStackTrace(e));
				        return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误，导致上传数据失败",request);
				    }
				}
			}
		}
		return ResponseUtils.getBooleanResponse(isDone, Alarm.class.toString(),request);
	}
	
	@RequestMapping("/findAlarmByUserAndDate")
	@ResponseBody
	public ParmResponse findAlarmByUserAndDate(int userId, Date date) {
		List<ViewAlarm_show> result = new ArrayList<>();
		if (date == null) {
			date = new Date();
		}
		List<ViewAlarm> list = viewAlarmService.findByUserId(userId, date);
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				result.add(new ViewAlarm_show(list.get(i)));
			}
			return ResponseUtils.getListResponse(result.toArray(), ViewRecord.class.toString(),request);
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_NULL, "数据为空",request);
	}

	/***
	 * statusID: 《1：未服药   2：已服药   3：跳过   4：其他》
	 * @param alarm
	 * @return
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public ParmResponse updateStatus(Alarm alarm) {
		boolean isDone = false;
		if (alarm == null) {
			return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "没有上传提醒信息",request);
		}
		if (alarm.getAlarmId() == 0) {
			return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误",request);
		}
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
	    defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
	    
		switch (alarm.getStatusId()) {
		case 1://未服药
			return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "状态未改变",request);
		case 2://已服药
			try {
				if(alarmService.updateStatus(alarm)){
					ViewAlarm newAlarm = viewAlarmService.findById(alarm);
					if (newAlarm != null) {
						Record record = new Record(newAlarm, new Date());
						record.setType(Consts.TAKE_MED_STATUS_EATED);
						isDone = recordService.add(record);
					}
				}
		        transactionManager.commit(status);
		    } catch (Exception e) {
		        transactionManager.rollback(status);
				logger.error(ExceptionUtils.getStackTrace(e));
		        return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误，导致上传数据失败",request);
		    }
			break;
		case 3://跳过
			try {
				if(alarmService.updateStatus(alarm)){
					ViewAlarm newAlarm = viewAlarmService.findById(alarm);
					if (newAlarm != null) {
						Record record = new Record(newAlarm, new Date());
						record.setType(Consts.TAKE_MED_STATUS_SKIP);
						isDone = recordService.add(record);
					}
				}
		        transactionManager.commit(status);
		    } catch (Exception e) {
		        transactionManager.rollback(status);
				logger.error(ExceptionUtils.getStackTrace(e));
		        return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误，导致上传数据失败",request);
		    }
			break;
		case 4://其他
			isDone = alarmService.updateStatus(alarm);
			break;
		default:
			break;
		}
		return ResponseUtils.getBooleanResponse(isDone, ViewAlarm.class.toString(),request);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public ParmResponse findById(int alarmId){
		ViewAlarm alarm = viewAlarmService.findById(alarmId);
		if(alarm != null){
			return ResponseUtils.getBeanResponse(alarm, ViewAlarm.class.toString(),request);
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_NULL, "数据为空",request);
	}
}
