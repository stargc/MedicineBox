package com.zhiyi.medicinebox.controller.alarm;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.po.alarm.Alarm;
import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
import com.zhiyi.medicinebox.entity.po.base.Medicine;
import com.zhiyi.medicinebox.entity.vo.alarm.AlarmAddReq;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.service.alarm.RecordService;
import com.zhiyi.medicinebox.service.base.MedicineService;
import com.zhiyi.medicinebox.strategy.AlarmStrategy;
import com.zhiyi.medicinebox.util.ResponseUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/viewalarm")
public class ViewAlarmController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	private HttpServletRequest request;

	@Resource
	private AlarmService alarmService;

	@Resource
	private MedicineService medicineService;

	@Resource
	private RecordService recordService;

	@Resource
	private AlarmStrategy alarmStrategy;

	@Resource
	private PlatformTransactionManager transactionManager;

	@RequestMapping(value=("/add"),method= RequestMethod.POST)
	@ResponseBody
	public ParmResponse add(AlarmAddReq alarmAddReq,
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            HttpServletRequest request) {
		String val = alarmStrategy.addVal(alarmAddReq);
		if (val != null){
			return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, val,request);
		}
		logger.info( alarmAddReq.getUserId() + " add alarm: medicine name = " + alarmAddReq.getMedName() + "；alarm time = " + alarmAddReq.getAlarmTime());
		Date date = new Date();
		String imgPath = null;
//		if (file != null) {
//			SimpleDateFormat sf_path = new SimpleDateFormat("yyyyMMdd");
//			SimpleDateFormat sf_name = new SimpleDateFormat("hhmmssSSSS");
//			StringBuffer path = new StringBuffer(ConfigUtil.getValue("file_save_path_liunx"))
//					.append("/").append(sf_path.format(date))
//					.append("/").append(alarmAddReq.getUserId()).append(sf_name.format(date));
//			imgPath = path.toString();
//			try {
//				FileUtils.saveFile(file, path.toString());
//			} catch (UnsupportedEncodingException e1) {
//				logger.error(ExceptionUtils.getStackTrace(e1));
//				return ResponseUtils.getErrorResponse(ResultCode.RESULT_FILE_SAVE_ERROR, "文件保存错误", request);
//			}
//		}
		Medicine medicine = new Medicine();
		medicine.setMedName(alarmAddReq.getMedName());
		medicine.setUrl(imgPath);
		Alarm alarm = new Alarm();
		alarm.setStatusId(alarmAddReq.getStatusId());
		alarm.setDosage(alarmAddReq.getDosage());
		alarm.setUserId(alarmAddReq.getUserId());

		boolean isDone = alarmStrategy.addViewAlarm(medicine,alarm, alarmAddReq.getStartDate(), alarmAddReq.getEndDate());

        return ResponseUtils.getBooleanResponse(isDone, Alarm.class.toString(),request);
	}

//	@RequestMapping("/findAlarmByUserAndDate")
//	@ResponseBody
//	public ParmResponse findAlarmByUserAndDate(int userId, Date date) {
//		List<ViewAlarm_show> result = new ArrayList<>();
//		if (date == null) {
//			date = new Date();
//		}
//		List<ViewAlarm> list = viewAlarmService.findByUserId(userId, date);
//		if (list != null && list.size() != 0) {
//			for (int i = 0; i < list.size(); i++) {
//				result.add(new ViewAlarm_show(list.get(i)));
//			}
//			return ResponseUtils.getListResponse(result.toArray(), ViewRecord.class.toString(),request);
//		}
//		return ResponseUtils.getErrorResponse(ResultCode.RESULT_NULL, "数据为空",request);
//	}
//
//	/***
//	 * @param statusid: 《1：未服药   2：已服药   3：跳过   4：其他》
//	 * @return
//	 */
//	@RequestMapping("/updateStatus")
//	@ResponseBody
//	public ParmResponse updateStatus(int alarmid, int statusid) {
//		boolean isDone = false;
//		if (alarmid == 0) {
//			return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误",request);
//		}
//		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
//	    defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//	    TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
//
//		switch (alarmid) {
//		case 1://未服药
//			return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "状态未改变",request);
//		case 2://已服药
//			try {
//				if(alarmService.updateStatus(alarmid,statusid)){
//					ViewAlarm newAlarm = alarmService.findViewAlarmById(alarmid);
//					if (newAlarm != null) {
//						Record record = new Record(newAlarm, new Date());
//						record.setType(Consts.TAKE_MED_STATUS_EATED);
//						isDone = recordService.add(record);
//					}
//				}
//		        transactionManager.commit(status);
//		    } catch (Exception e) {
//		        transactionManager.rollback(status);
//				logger.error(ExceptionUtils.getStackTrace(e));
//		        return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误，导致上传数据失败",request);
//		    }
//			break;
//		case 3://跳过
//			try {
//				if(alarmService.updateStatus(alarmid,statusid)){
//					ViewAlarm newAlarm = alarmService.findViewAlarmById(alarmid);
//					if (newAlarm != null) {
//						Record record = new Record(newAlarm, new Date());
//						record.setType(Consts.TAKE_MED_STATUS_SKIP);
//						isDone = recordService.add(record);
//					}
//				}
//		        transactionManager.commit(status);
//		    } catch (Exception e) {
//		        transactionManager.rollback(status);
//				logger.error(ExceptionUtils.getStackTrace(e));
//		        return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误，导致上传数据失败",request);
//		    }
//			break;
//		case 4://其他
//			isDone = alarmService.updateStatus(alarmid,statusid);
//			break;
//		default:
//			break;
//		}
//		return ResponseUtils.getBooleanResponse(isDone, ViewAlarm.class.toString(),request);
//	}

	@RequestMapping("/findById")
	@ResponseBody
	public ParmResponse findById(int alarmId){
		ViewAlarm alarm = alarmService.findViewAlarmById(alarmId);
		if(alarm != null){
			return ResponseUtils.getBeanResponse(alarm, ViewAlarm.class.toString(),request);
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_NULL, "数据为空",request);
	}

}
