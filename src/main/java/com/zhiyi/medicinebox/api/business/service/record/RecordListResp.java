package com.zhiyi.medicinebox.api.business.service.record;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.common.vo.alarm.ViewRecordShow;
import lombok.Data;

import java.util.List;

/**
 * @author guanchen
 * @version $Id RecordListResp.java, v 0.1 2018-10-18 17:20 star Exp $$
 */
@Data
public class RecordListResp extends BaseResponse {
    private List<ViewRecordShow> list;
}
