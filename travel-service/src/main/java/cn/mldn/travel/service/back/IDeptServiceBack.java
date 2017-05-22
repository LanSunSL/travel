package cn.mldn.travel.service.back;

import java.util.List;
import java.util.Map;

import cn.mldn.travel.vo.Dept;

public interface IDeptServiceBack {
	public Map<Object, Object> listMgr();
	public boolean edit(Dept vo);
	public List<Dept> list();
}
