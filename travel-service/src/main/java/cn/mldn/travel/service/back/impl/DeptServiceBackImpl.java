package cn.mldn.travel.service.back.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.travel.dao.IDeptDAO;
import cn.mldn.travel.dao.IEmpDAO;
import cn.mldn.travel.service.back.IDeptServiceBack;
import cn.mldn.travel.vo.Dept;
@Service
public class DeptServiceBackImpl implements IDeptServiceBack {
	@Resource
	private IDeptDAO deptDAO;
	@Resource
	private IEmpDAO empDAO;
	@Override
	public Map<Object, Object> listMgr() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<Dept> all = this.deptDAO.findAll();
		Iterator<Dept> iter = all.iterator();
		while (iter.hasNext()) {
			Dept dept = iter.next();
			map.put(dept, this.empDAO.findById(dept.getEid()));
		}
		return map;
	}
	@Override
	public boolean edit(Dept vo) {
		return this.deptDAO.doUpdate(vo);
	}
	@Override
	public List<Dept> list() {
		return this.deptDAO.findAll();
	}

}
