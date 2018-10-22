package com.zhiyi.medicinebox.api.business.service.alarm.updateStatus;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.strategy.AlarmStrategy;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.AlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Alarm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guanchen
 * @version $Id UpdateStatusService.java, v 0.1 2018-10-18 14:31 star Exp $$
 */
@Service
@Slf4j
public class AlarmUpdateStatusService {

    @Autowired
    private AlarmMapper alarmMapper;

    @Autowired
    private AlarmStrategy alarmStrategy;

    public BaseResponse updateStatus(int alarmId, int statusId){
        Boolean isDone = false;
        BaseResponse resp = new BaseResponse();
        Alarm alarm = alarmMapper.selectByPrimaryKey(alarmId);
        if (alarm == null){
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("未找到对应的用药提醒");
            return resp;
        }
        if (Integer.compare(alarm.getStatusId(),statusId) == 0) {
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("状态未改变");
            return resp;
        }
        log.info("用户[" + alarm.getUserId() + "]更改提醒[" + alarmId + "]状态为" + statusId);

        switch (statusId) {
            case 1://未服药
                Alarm bean = new Alarm();
                bean.setAlarmId(alarmId);
                bean.setStatusId(statusId);
                Integer num = alarmMapper.updateByPrimaryKeySelective(bean);
                isDone = (num > 0);
                break;
            case 2://已服药
                if (Integer.compare(alarm.getStatusId(),1) != 0){
                    resp.setResultCode(BaseResponse.FAILED);
                    resp.setResultMsg("该提醒状态不是 未服药");
                    return resp;
                }
                isDone = alarmStrategy.takeMedicine(alarmId, statusId);
                break;
            case 3://跳过
                if (Integer.compare(alarm.getStatusId(),1) != 0){
                    resp.setResultCode(BaseResponse.FAILED);
                    resp.setResultMsg("该提醒状态不是 未服药");
                    return resp;
                }
                isDone = alarmStrategy.skipMedicine(alarmId, statusId);
                break;
            case 4://其他
                Alarm bean4 = new Alarm();
                bean4.setAlarmId(alarmId);
                bean4.setStatusId(statusId);
                Integer num4 = alarmMapper.updateByPrimaryKeySelective(bean4);
                isDone = (num4 > 0);
                break;
            default:
                resp.setResultCode(BaseResponse.FAILED);
                resp.setResultMsg("未找到对应的状态");
                return resp;
        }
        resp.setResultCode(isDone ? BaseResponse.SUCCESS : BaseResponse.FAILED);
        resp.setResultMsg(isDone ? "success" : "操作失败");
        return resp;
    }
}
