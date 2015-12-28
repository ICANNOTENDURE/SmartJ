package com.sj.controller.sys;

import com.jfinal.core.Controller;
import com.sj.entity.sys.User;
import com.sj.vo.common.OperateResult;

public class UserController extends Controller {
	
	public void index(){
		setAttr("dataPage", User.me.paginate(getParaToInt(("start"), 1), getParaToInt(("end"), 10)));
		render("userList.html");
	}
	
	//保存
	public void save() {
		OperateResult operateResult=new OperateResult();
		try {
			getModel(User.class).save();
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.setResultCode(1);
			operateResult.setResultContent(e.getMessage());
		}finally{
			renderJson(operateResult);
		}
	}
}
