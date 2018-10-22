package com.zhiyi.medicinebox.api.business.service.user;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.UserMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guanchen
 * @version $Id UserService.java, v 0.1 2018-10-22 15:45 star Exp $$
 */
@Service
@Slf4j
public class UserAddService {
    @Resource
    private UserMapper userMapper;

    public UserResp responseVale(User userBean) {
        UserResp resp = new UserResp();
        User oldUser = userMapper.selectByOpenId(userBean.getOpenId());
        if (oldUser != null && StringUtils.isEmpty(oldUser.getOpenId())) {
            resp.setResultCode(BaseResponse.SUCCESS);
            resp.setUser(oldUser);
            return resp;
        }
        boolean isDone = userMapper.insertSelective(userBean) > 0;
        if (isDone) {
            resp.setResultCode(BaseResponse.SUCCESS);
            resp.setUser(userBean);
            return resp;
        }
        resp.setResultCode(BaseResponse.FAILED);
        resp.setResultMsg("用户添加失败");
        return resp;

    }
}
