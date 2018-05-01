package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sendmessage_parm database table.
 * 
 */
@Entity
@Table(name="sendmessage_parm")
@NamedQuery(name="SendMessageParm.findAll", query="SELECT s FROM SendMessageParm s")
public class SendMessageParm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	private String formId;

	private String openId;

	private String prepayId;

	private int userId;
	
	/*Id 来源  FEMB:吃药按钮From；FSMB：跳过用药按钮From；FAMB:添加用药提醒按钮from*/
	private String type;
	
	/* 0: 未使用   1：已使用**/
	private int isused;

	public SendMessageParm() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPrepayId() {
		return this.prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIsused() {
		return isused;
	}

	public void setIsused(int isused) {
		this.isused = isused;
	}

}