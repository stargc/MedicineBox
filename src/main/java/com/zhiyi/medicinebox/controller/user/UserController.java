package com.zhiyi.medicinebox.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zhiyi.medicinebox.entity.sendMeg.WXSendEatMedParmBean;
import com.zhiyi.medicinebox.service.SendMessageParmService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.User;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.UserService;
import com.zhiyi.medicinebox.util.ResponseUtils;

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
	 * @param user
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ParmResponse add(User user) {
		List<User> users = new ArrayList<User>();
		if(user != null && user.getOpenId() != null && !user.getOpenId().equals("undefined")){
			users = userService.findByOpenId(user.getOpenId());
			if (users.size() == 0) {
				boolean isDone = userService.add(user);
				if (isDone) {
					return ResponseUtils.getBeanResponse(user, User.class.toString(),request);
				}
				return ResponseUtils.getErrorResponse(ResultCode.RESULT_FAIL, "参数错误！",request);
			} else {
				return ResponseUtils.getBeanResponse(users.get(0), User.class.toString(),request);
			}
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误！",request);
	}

	/****
	 * 根据微信openId 判断是否存在该用户，
	 * @param openId
	 * @return
	 */
	@RequestMapping("/findUserByOpenId")
	@ResponseBody
	public ParmResponse findUserByOpenId(String openId){
		List<User> users = new ArrayList<User>();
		if(openId != null){
			users = userService.findByOpenId(openId);
		}
		return ResponseUtils.getListResponse(users.toArray(), User.class.toString(),request);
	}
}
