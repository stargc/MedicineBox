package com.zhiyi.medicinebox.api.business.service.base;

import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.BoxMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Box;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BoxService {

	@Resource
	private BoxMapper mapper;

	public boolean add(Box box) {
		if (box != null) {
			return mapper.insertSelective(box) > 0;
		}
		return false;
	}
	
	public boolean delete(Box box) {
		if (box != null && box.getBoxId() != 0) {
			return mapper.deleteByPrimaryKey(box.getBoxId()) > 0;
		}
		return false;
	}
	
	public boolean update(Box box){
		if (box != null && box.getBoxId() != 0) {
			return mapper.updateByPrimaryKeySelective(box) > 0;
		}
		return false;
	}
}
