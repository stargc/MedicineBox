package com.zhiyi.medicinebox.service.sendmsg;

import com.zhiyi.medicinebox.dao.SendmessageLogMapper;
import com.zhiyi.medicinebox.entity.sendmsg.SendmessageLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SendMessageLogService {

	@Resource
	private SendmessageLogMapper mapper;
	
	public boolean add(SendmessageLog sendMessageLog) {
		if (sendMessageLog != null) {
			return mapper.insertSelective(sendMessageLog) > 0;
		}
		return false;
	}
	
	public boolean delete(SendmessageLog sendMessageLog) {
		if (sendMessageLog != null && sendMessageLog.getId() != 0) {
			return mapper.deleteByPrimaryKey(sendMessageLog.getId()) > 0;
		}
		return false;
	}
	
	public boolean update(SendmessageLog sendMessageLog){
		if (sendMessageLog != null && sendMessageLog.getId() != 0) {
			return mapper.updateByPrimaryKeySelective(sendMessageLog) > 0;
		}
		return false;
	}
}
