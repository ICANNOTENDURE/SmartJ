package com.sj.controller.sys;

import com.jfinal.core.Controller;
import com.sj.entity.sys.Func;
import com.sj.entity.sys.User;
import com.sj.vo.common.OperateResult;

public class FuncController extends Controller {
	
	public void index(){
		setAttr("dataPage", Func.me.paginate(getParaToInt(("pageNo"), 1), getParaToInt(("pageSize"), 10)));
		render("funcList.html");
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
