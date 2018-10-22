package com.zhiyi.medicinebox.api.business.service.medicine.search;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.MedicineMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Medicine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guanchen
 * @version $Id MedicineSearchService.java, v 0.1 2018-10-18 17:55 star Exp $$
 */
@Service
@Slf4j
public class MedicineSearchService {

    @Autowired
    private MedicineMapper medicineMapper;

    public MedicineResp searchByMedId(int medId){
        MedicineResp resp = new MedicineResp();
        Medicine medicine = medicineMapper.selectByPrimaryKey(medId);
        if (medicine == null){
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("获取内容为空");
            return resp;
        }
        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setMedicine(medicine);
        return resp;
    }
}
