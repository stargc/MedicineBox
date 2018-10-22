package com.zhiyi.medicinebox.api.business.service.alarm.search;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.common.vo.alarm.ViewAlarmShow;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.ViewAlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guanchen
 * @version $Id AlarmSearchService.java, v 0.1 2018-10-18 14:13 star Exp $$
 */
@Service
@Slf4j
public class AlarmSearchService {

    @Resource
    private ViewAlarmMapper viewAlarmMapper;

    public AlarmListResp searchByUserId(int userId) {
        AlarmListResp resp = new AlarmListResp();
        resp.setResultCode(BaseResponse.SUCCESS);

        List<ViewAlarm> list = viewAlarmMapper.findViewAlarmByUserId(userId);


        List<ViewAlarmShow> result = new ArrayList<>();
        if (list.isEmpty()) {
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("该用户没有任何用药提醒");
        }

        for (int i = 0; i < list.size(); i++) {
            result.add(new ViewAlarmShow(list.get(i)));
        }

        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setList(result);
        return resp;
    }

    public AlarmResp searchByAlarmId(int alarmId){
        AlarmResp alarmResp = new AlarmResp();
        ViewAlarm alarm = viewAlarmMapper.findViewAlarmByAlarmId(alarmId);
        if (alarm == null) {
            alarmResp.setResultCode(BaseResponse.FAILED);
            alarmResp.setResultMsg("获取内容为空");
            return alarmResp;
        }
        alarmResp.setResultCode(BaseResponse.SUCCESS);
        alarmResp.fillData(alarm);
        return alarmResp;
    }
}
