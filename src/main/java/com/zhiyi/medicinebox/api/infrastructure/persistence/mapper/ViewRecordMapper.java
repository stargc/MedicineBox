package com.zhiyi.medicinebox.api.infrastructure.persistence.mapper;


import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewRecord;

import java.util.List;

public interface ViewRecordMapper {
    List<ViewRecord> selectByUserId(Integer userId);
}