package com.zhiyi.medicinebox.api.business.service.weixin.vo;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import lombok.Data;

/**
 * @author guanchen
 * @version $Id weiXinResp.java, v 0.1 2018-10-22 16:20 star Exp $$
 */
@Data
public class WXOpenIdResp extends BaseResponse{

    private String openid;
    private String sessionKey;

}
