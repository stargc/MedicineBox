package com.zhiyi.medicinebox.api.application.controller.base;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.user.UserAddService;
import com.zhiyi.medicinebox.api.business.service.user.UserResp;
import com.zhiyi.medicinebox.api.business.service.user.UserSearchService;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/****
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserAddService userAddService;

	@Autowired
	private UserSearchService userSearchService;

	/***
	 * 用于添加用户
	 * @param userBean
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public BaseResponse add(User userBean) {
		UserResp resp = new UserResp();
		if(userBean == null || userBean.getOpenId() == null || userBean.getOpenId().equals("undefined")){
			resp.setResultCode(BaseResponse.FAILED);
			resp.setResultMsg("参数错误！");
			return resp;
		}
		return userAddService.responseVale(userBean);
	}

	/****
	 * 根据微信openId 判断是否存在该用户，
	 * @param openId
	 * @return
	 */
	@RequestMapping("/findUserByOpenId")
	@ResponseBody
	public UserResp findUserByOpenId(String openId){
		UserResp resp = new UserResp();
		if(openId == null){
			resp.setResultCode(BaseResponse.FAILED);
			resp.setResultMsg("参数异常");
			return resp;
		}
		return userSearchService.findUserByOpenId(openId);
	}
}
