package com.zhiyi.medicinebox.api.business.service.record;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.alarm.search.vo.ViewRecordShow;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.ViewRecordMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guanchen
 * @version $Id RecordSearchService.java, v 0.1 2018-10-18 17:32 star Exp $$
 */
@Service
@Slf4j
public class RecordSearchService {

    @Resource
    private ViewRecordMapper viewRecordMapper;

    public RecordListResp searchByUserId(int userId){
        RecordListResp resp = new RecordListResp();

        List<ViewRecord> list = viewRecordMapper.selectByUserId(userId);
        if (list.isEmpty()){
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("获取内容为空");
        }
        List<ViewRecordShow> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(new ViewRecordShow(list.get(i)));
        }
        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setList(result);
        return resp;
    }
}
