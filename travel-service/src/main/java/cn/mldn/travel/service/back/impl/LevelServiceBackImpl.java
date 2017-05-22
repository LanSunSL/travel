package cn.mldn.travel.service.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.travel.dao.ILevelDAO;
import cn.mldn.travel.service.back.ILevelServiceBack;
import cn.mldn.travel.vo.Level;
@Service
public class LevelServiceBackImpl implements ILevelServiceBack {
	@Resource
	private ILevelDAO levelDAO;
	@Override
	public List<Level> list() {
		return this.levelDAO.findAll();
	}

}
