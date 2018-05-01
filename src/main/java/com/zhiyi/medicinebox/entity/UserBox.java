package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_box database table.
 * 
 */
@Entity
@Table(name="user_box")
@NamedQuery(name="UserBox.findAll", query="SELECT u FROM UserBox u")
public class UserBox implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ubId;

	private int boxId;

	private int userId;

	public UserBox() {
	}

	public int getUbId() {
		return this.ubId;
	}

	public void setUbId(int ubId) {
		this.ubId = ubId;
	}

	public int getBoxId() {
		return this.boxId;
	}

	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}