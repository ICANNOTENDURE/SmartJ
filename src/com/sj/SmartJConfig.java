package com.sj;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.sj.controller.index.IndexController;
import com.sj.controller.sys.FuncController;
import com.sj.controller.sys.UserController;
import com.sj.entity.sys.Func;
import com.sj.entity.sys.User;

public class SmartJConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants ct) {
		PropKit.use("config.txt");
		ct.setDevMode(PropKit.getBoolean("devMode", false));
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers arg0) {
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors arg0) {
	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins plugins) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		plugins.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		plugins.add(arp);
		//系统模块
		arp.addMapping("t_sys_user", "user_id",User.class); // 用户表
		arp.addMapping("t_sys_func", "func_id",Func.class); // 权限表
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes routes) {
		routes.add("/index", IndexController.class,"/html"); //登录后首页
		routes.add("/test", IndexController.class,"/html");
		
		//sys 系统模块
		routes.add("/sys/user", UserController.class,"/html/sys/user"); //用户管理
		routes.add("/sys/func", FuncController.class,"/html/sys/func"); //权限管理
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 88, "/", 5);
	}
}
