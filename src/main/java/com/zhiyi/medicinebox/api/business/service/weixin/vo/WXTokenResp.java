package com.zhiyi.medicinebox.api.business.service.weixin.vo;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import lombok.Data;

/**
 * @author guanchen
 * @version $Id weiXinResp.java, v 0.1 2018-10-22 16:20 star Exp $$
 */
@Data
public class WXTokenResp extends BaseResponse{

    private String accessToken;//获取到的凭证
    private String expiresIn;//token有效期

}
