package com.zhiyi.medicinebox.api.application.controller.base;

import com.zhiyi.medicinebox.api.business.common.constant.ResultCode;
import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.UserMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.User;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/****
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Resource
	private UserMapper userMapper;
	
	/***
	 * 用于添加用户
	 * @param userBean
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ParmResponse add(User userBean) {
		if(userBean != null && userBean.getOpenId() != null && !userBean.getOpenId().equals("undefined")){
			User oldUser = userMapper.selectByOpenId(userBean.getOpenId());
			if (oldUser == null || StringUtils.isEmpty(oldUser.getOpenId())) {
				boolean isDone = userMapper.insertSelective(userBean) > 0;
				if (isDone) {
					return ResponseUtils.getBeanResponse(userBean, User.class.toString());
				}
				return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "参数错误！");
			} else {
				return ResponseUtils.getBeanResponse(oldUser, User.class.toString());
			}
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误！");
	}

	/****
	 * 根据微信openId 判断是否存在该用户，
	 * @param openId
	 * @return
	 */
	@RequestMapping("/findUserByOpenId")
	@ResponseBody
	public ParmResponse findUserByOpenId(String openId){
		if(openId != null){
			User user = userMapper.selectByOpenId(openId);
			return ResponseUtils.getBeanResponse(user, User.class.toString());
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR,"openId 为空");
	}
}
