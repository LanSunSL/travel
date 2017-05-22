$(function(){
	$("button[id^=edit-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-")[1] ;
			dname = $("#dname-" + did).val() ;
			if (dname == "") { 
				operateAlert(false,"","部门名称不允许为空，请确认后再提交更新！") ;
			} else {
				$.post("pages/back/admin/dept/edit.action",{"did":did,"dname":dname},function(data){
					operateAlert(data.trim() == "true","部门名称更新成功！","部门名称更新失败！") ;
				},"text");
			}
		}) ;
	}) ;
	$("span[id^=eid-]").each(function(){
		$(this).on("click",function(){
			eid = this.id.replace("eid-","") ;
			$.post("pages/back/admin/emp/get.action",{"eid":eid},
					function(data){
				$("#modal-name").text(data.emp.ename) ;
				$("#modal-level").text(data.level.title) ;
				$("#modal-dept").text(data.dept.dname) ;
				$("#modal-phone").text(data.emp.phone) ;
				$("#modal-hiredate").text(new Date(data.emp.hiredate.time).format("yyyy-MM-dd hh:mm:ss")) ;
				$("#modal-note").html(data.emp.note) ;
			},"json") ;
			$("#userInfo").modal("toggle") ;
		}) ;
	}) ;
}) ;