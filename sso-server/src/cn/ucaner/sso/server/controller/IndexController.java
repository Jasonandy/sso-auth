/**
 * <html>
 * <body>
 *  <P> Copyright 1994-2018. JasonInternational.</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2018年</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.sso.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ucaner.sso.client.constant.AuthConst;

/**
* @Package：cn.ucaner.sso.server.controller   
* @ClassName：IndexController   
* @Description：   <p> IndexController</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 下午1:46:21   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
@Controller
public class IndexController {
	
	public Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * @Description: 登录页面
	 * @param request
	 * @param model
	 * @return String
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	@RequestMapping("/loginPage")
	public String index(HttpServletRequest request, Model model) {
		//request.getParameter(AuthConst.CLIENT_URL);
		//request.setAttribute(AuthConst.CLIENT_URL,"http://sso.ucaner.cn");
		model.addAttribute(AuthConst.CLIENT_URL,"http://sso.ucaner.cn");
		return "index";
	}

	/**
	 * @Description: 登录成功页面
	 * @return String
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	@RequestMapping("/success")
	public String success() {
		return "success";
	}
}