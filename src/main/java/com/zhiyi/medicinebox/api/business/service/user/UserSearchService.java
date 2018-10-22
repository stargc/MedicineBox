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
public class UserSearchService {
    @Resource
    private UserMapper userMapper;

    public UserResp findUserByOpenId(String openId) {
        UserResp resp = new UserResp();
        User user = userMapper.selectByOpenId(openId);
        if (user == null){
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("用户不存在");
            return resp;
        }
        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setUser(user);
        return resp;
    }
}
