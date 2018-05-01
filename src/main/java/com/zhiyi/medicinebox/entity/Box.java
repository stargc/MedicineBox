package com.zhiyi.medicinebox.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the box database table.
 * 
 */
@Entity
@Table(name="box")
@NamedQuery(name="Box.findAll", query="SELECT b FROM Box b")
public class Box implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int boxId;

	private String boxName;

	@Lob
	private String qrCode;

	public Box() {
	}

	public int getBoxId() {
		return this.boxId;
	}

	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}

	public String getBoxName() {
		return this.boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}

	public String getQrCode() {
		return this.qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

}