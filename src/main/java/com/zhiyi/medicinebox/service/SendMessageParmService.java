package com.zhiyi.medicinebox.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.SendMessageParm;
import com.zhiyi.medicinebox.entity.sendMeg.WXSendEatMedParmBean;
import com.zhiyi.medicinebox.util.tools.DateUtil;

@Service("SendMessageParmService")
public class SendMessageParmService {

	@Resource
	private BaseDAO<SendMessageParm> sendParmDAO;
	@Resource
	private BaseDAO<WXSendEatMedParmBean> wxDAO;

	
	public boolean add(SendMessageParm o) {
		if (o != null) {
			return sendParmDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(SendMessageParm o) {
		if (o != null && o.getId() != 0) {
			return sendParmDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(SendMessageParm o){
		if (o != null && o.getId() != 0) {
			return sendParmDAO.update(o);
		}
		return false;
	}
	
	public int deleteByDate(Date createDate){
		if (createDate != null) {
			return sendParmDAO.excuteBySql(
					"DELETE from sendmessage_parm where createDate < '"+ 
							DateUtil.date2String(createDate) +"'");
		}
		return 0;
		
	}
	
	public List<WXSendEatMedParmBean> findSendEatMedParm(Date date, Date startTime, Date endTime){
		String sql = "select a.alarmId, a.alarmDate , a.alarmTime,a.statusId,d.dosage,m.medName, "
				+ "s.formId,s.prepayId,s.ID as parmId1,u.openId,u.userId,u.userName,m.medId " +
				"from alarm a LEFT JOIN user u on a.userId = u.userId " +
				"LEFT JOIN  dosage d on a.dosageId = d.dosageId " +
				"LEFT JOIN medicine m on d.medId = m.medId " +
				"LEFT JOIN sendmessage_parm s on a.userId = s.userId " +
				"where (a.statusId = '1' or a.statusId = '3') and a.issend=0  and s.isused=0 " +
				"and a.alarmDate = '" + DateUtil.date2String(date, "yyy-MM-dd") + 
				"' and a.alarmTime >= '" + DateUtil.Date2TimeString(startTime) + 
				"' and a.alarmTime <= '" + DateUtil.Date2TimeString(endTime) + "' GROUP BY a.alarmId";
		return wxDAO.queryBySql(sql, WXSendEatMedParmBean.class);
	}
	
	public boolean updateIsSend(SendMessageParm bean){
		StringBuffer sql = new StringBuffer("UPDATE sendmessage_parm set isused = ")
				.append(bean.getIsused())
				.append(" where ID = ")
				.append(bean.getId());
		Integer num = wxDAO.excuteBySql(sql.toString());
		if (num != null && num > 0) {
			return true;
		}
		return false;
	}
}
