package com.zhiyi.medicinebox.service.sendmsg;

import com.zhiyi.medicinebox.dao.SendmessageParmMapper;
import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageParm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SendMessageParmService {

	@Resource
	private SendmessageParmMapper sendmessageParmMapper;

	
	public boolean add(SendmessageParm parm) {
		if (parm != null) {
			parm.setCreateDate(new Date());
			return sendmessageParmMapper.insertSelective(parm) > 0;
		}
		return false;
	}
	
	public boolean delete(SendmessageParm parm) {

		if (parm != null && parm.getId() != 0) {
			return sendmessageParmMapper.deleteByPrimaryKey(parm.getId()) > 0;
		}
		return false;
	}
	
	public boolean update(SendmessageParm parm){
		if (parm != null && parm.getId() != 0) {
			return sendmessageParmMapper.updateByPrimaryKeySelective(parm) > 0;
		}
		return false;
	}
	
	public int deleteByDate(Date createDate){
		if (createDate != null) {
			return sendmessageParmMapper.deleteByDate(createDate);
		}
		return 0;
	}
	
	public List<SendmessageParm> findByUserId(int userid){
		return sendmessageParmMapper.findByUserId(userid);
	}
	
	public int updateIsSend(Short isUserd,int id){
		SendmessageParm bean = new SendmessageParm();
		bean.setId(id);
		bean.setIsUsed(isUserd);
		return sendmessageParmMapper.updateByPrimaryKeySelective(bean);
	}

	public int scrapParm(Date createDate){
		if (createDate != null) {
			return sendmessageParmMapper.scrapParm(createDate);
		}
		return 0;
	}
}
