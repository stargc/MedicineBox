package com.zhiyi.medicinebox.service.sendmsg;

import com.zhiyi.medicinebox.dao.SendmessageParmMapper;
import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageParm;
import com.zhiyi.medicinebox.entity.vo.sendmsg.WXSendEatMedParmBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SendMessageParmService {

	@Resource
	private SendmessageParmMapper parmMapper;

	
	public boolean add(SendmessageParm parm) {
		if (parm != null) {
			parm.setCreateDate(new Date());
			return parmMapper.insertSelective(parm) > 0;
		}
		return false;
	}
	
	public boolean delete(SendmessageParm parm) {

		if (parm != null && parm.getId() != 0) {
			return parmMapper.deleteByPrimaryKey(parm.getId()) > 0;
		}
		return false;
	}
	
	public boolean update(SendmessageParm parm){
		if (parm != null && parm.getId() != 0) {
			return parmMapper.updateByPrimaryKeySelective(parm) > 0;
		}
		return false;
	}
	
	public int deleteByDate(Date createDate){
		if (createDate != null) {
			return parmMapper.deleteByDate(createDate);
		}
		return 0;
		
	}
	
	public List<WXSendEatMedParmBean> findWXSendEatMedParm(Date startTime, Date endTime){
		return parmMapper.findWXSendEatMedParm(startTime,endTime);
	}
	
	public int updateIsSend(Short isUserd,int id){
		SendmessageParm bean = new SendmessageParm();
		bean.setId(id);
		bean.setIsUsed(isUserd);
		return parmMapper.updateByPrimaryKeySelective(bean);
	}
}
