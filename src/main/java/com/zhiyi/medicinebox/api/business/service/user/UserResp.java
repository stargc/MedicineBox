package com.zhiyi.medicinebox.api.business.service.user;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.User;
import lombok.Data;

/**
 * @author guanchen
 * @version $Id UserResp.java, v 0.1 2018-10-22 15:46 star Exp $$
 */
@Data
public class UserResp extends BaseResponse{

    private User user;
}
