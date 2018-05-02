package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sendmessage_log database table.
 * 
 */
@Entity
@Table(name="sendmessage_log")
@NamedQuery(name="SendMessageLog.findAll", query="SELECT s FROM SendMessageLog s")
public class SendMessageLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	private String message;

	private String openId;

	private String type;

	private int userId;

	private String userName;

	public SendMessageLog() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}