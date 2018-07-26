package com.zhiyi.medicinebox.controller.base;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.po.base.User;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.base.UserService;
import com.zhiyi.medicinebox.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/****
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource
	private UserService userService;
	
	/***
	 * 用于添加用户
	 * @param userBean
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ParmResponse add(User userBean) {
		if(userBean != null && userBean.getOpenId() != null && !userBean.getOpenId().equals("undefined")){
			User oldUser = userService.findByOpenId(userBean.getOpenId());
			if (oldUser == null || StringUtils.isEmpty(oldUser.getOpenId())) {
				boolean isDone = userService.add(userBean);
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
			User user = userService.findByOpenId(openId);
			return ResponseUtils.getBeanResponse(user, User.class.toString());
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR,"openId 为空");
	}
}
