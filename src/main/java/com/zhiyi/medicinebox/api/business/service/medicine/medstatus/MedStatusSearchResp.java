package com.zhiyi.medicinebox.api.business.service.medicine.medstatus;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.StatusMed;
import lombok.Data;

/**
 * @author guanchen
 * @version $Id MedStatusSearchResp.java, v 0.1 2018-10-18 18:07 star Exp $$
 */
@Data
public class MedStatusSearchResp extends BaseResponse {
    private StatusMed statusMed;
}
