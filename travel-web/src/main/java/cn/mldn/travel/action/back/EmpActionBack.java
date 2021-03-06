package cn.mldn.travel.action.back;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.travel.service.back.IDeptServiceBack;
import cn.mldn.travel.service.back.IEmpServiceBack;
import cn.mldn.travel.service.back.ILevelServiceBack;
import cn.mldn.travel.vo.Emp;
import cn.mldn.util.action.abs.AbstractBaseAction;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/pages/back/admin/emp/*")
public class EmpActionBack extends AbstractBaseAction {
	private static final String FLAG = "雇员";
	@Resource
	private IEmpServiceBack empService;
	@Resource
	private IDeptServiceBack deptService;
	@Resource
	private ILevelServiceBack levelService;
	
	@RequestMapping("add_pre")
	@RequiresUser
	@RequiresRoles("emp")
	@RequiresPermissions("emp:add")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getUrl("emp.add.page"));
		mav.addObject("allDepts", this.deptService.list());
		mav.addObject("allLevels", this.levelService.list());
		return mav;
	}

	@RequestMapping("add")
	@RequiresUser
	@RequiresRoles("emp")
	@RequiresPermissions("emp:add")
	public ModelAndView add(Emp vo, MultipartFile pic, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getUrl("back.forward.page"));
		// super.setUrlAndMsg(request, "emp.add.action", "vo.add.failure",
		// FLAG);
		super.setUrlAndMsg(request, "emp.add.action", "vo.add.success", FLAG);
		return mav;
	}

	@RequestMapping("edit_pre")
	@RequiresUser
	@RequiresRoles("emp")
	@RequiresPermissions("emp:edit")
	public ModelAndView editPre(String eid) {
		ModelAndView mav = new ModelAndView(super.getUrl("emp.edit.page"));
		return mav;
	}

	@RequestMapping("edit")
	@RequiresUser
	@RequiresRoles("emp")
	@RequiresPermissions("emp:edit")
	public ModelAndView edit(Emp vo, MultipartFile pic, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getUrl("back.forward.page"));
		// super.setUrlAndMsg(request, "emp.list.action", "vo.edit.failure",
		// FLAG);
		super.setUrlAndMsg(request, "emp.list.action", "vo.edit.success", FLAG);
		return mav;
	}

	@RequestMapping("get")
	@RequiresUser
	@RequiresRoles(value={ "emp", "empshow" }, logical = Logical.OR)
	@RequiresPermissions(value = { "emp:get", "empshow:get"}, logical = Logical.OR) 
	public ModelAndView get(String eid, HttpServletResponse response) {
		JSONObject obj = new JSONObject() ;
		Map<String, Object> map = this.empService.show(eid);
		Iterator<Map.Entry<String,Object>> iter = map.entrySet().iterator() ;
		while (iter.hasNext()) {
			Map.Entry<String, Object> me = iter.next() ;
			obj.put(me.getKey(), me.getValue()) ;
		}
		super.print(response, obj);
		return null;
	}

	@RequestMapping("list")
	@RequiresUser
	@RequiresRoles(value = { "emp", "empshow" }, logical = Logical.OR)
	@RequiresPermissions(value = { "emp:list", "empshow:list" }, logical = Logical.OR)
	public ModelAndView list(String ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getUrl("emp.list.page"));
		mav.addObject("allEmps", this.empService.list());
		return mav;
	}

	@RequestMapping("delete")
	@RequiresUser
	@RequiresRoles("emp")
	@RequiresPermissions("emp:delete")
	public ModelAndView delete(String ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getUrl("back.forward.page"));
		// super.setUrlAndMsg(request, "emp.list.action", "vo.delete.failure",
		// FLAG);
		super.setUrlAndMsg(request, "emp.list.action", "vo.delete.success", FLAG);
		return mav;
	}
}
