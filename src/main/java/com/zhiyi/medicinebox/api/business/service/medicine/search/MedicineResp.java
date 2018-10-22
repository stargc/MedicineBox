package com.zhiyi.medicinebox.api.business.service.medicine.search;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Medicine;
import lombok.Data;

/**
 * @author guanchen
 * @version $Id MedicineResp.java, v 0.1 2018-10-18 17:56 star Exp $$
 */
@Data
public class MedicineResp extends BaseResponse{
    private Medicine medicine;
}
