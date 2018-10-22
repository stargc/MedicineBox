package com.zhiyi.medicinebox.api.business.service.medicine.medstatus;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.StatusMedMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.StatusMed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guanchen
 * @version $Id MedStatusSearchService.java, v 0.1 2018-10-18 18:06 star Exp $$
 */
@Service
@Slf4j
public class MedStatusSearchService {

    @Resource
    private StatusMedMapper statusMedMapper;

    public MedStatusSearchResp searchById(int id){
        MedStatusSearchResp resp = new MedStatusSearchResp();
        StatusMed statusMed = statusMedMapper.selectByPrimaryKey(id);
        if (statusMed == null){
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("获取内容为空");
            return resp;
        }
        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setStatusMed(statusMed);
        return resp;
    }
}
