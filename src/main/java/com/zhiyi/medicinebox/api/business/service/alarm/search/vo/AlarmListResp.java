package com.zhiyi.medicinebox.api.business.service.alarm.search.vo;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * @author guanchen
 * @version $Id AlarmListResp.java, v 0.1 2018-10-18 14:04 star Exp $$
 */
@Data
public class AlarmListResp extends BaseResponse {
    private List<ViewAlarmShow> list;
}
