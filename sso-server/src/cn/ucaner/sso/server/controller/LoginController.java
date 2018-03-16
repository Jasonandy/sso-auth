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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ucaner.sso.client.constant.AuthConst;
import cn.ucaner.sso.client.domain.User;
import cn.ucaner.sso.client.storage.SessionStorage;
import cn.ucaner.sso.client.util.HTTPUtil;
import cn.ucaner.sso.server.service.UserService;
import cn.ucaner.sso.server.storage.ClientStorage;

/**
* @Package：cn.ucaner.sso.server.controller   
* @ClassName：LoginController   
* @Description：   <p> 登录和注销控制器</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 下午1:49:22   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	/**
	 * @Description: 登录
	 * @param request
	 * @param input
	 * @param model
	 * @return String
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, User input, Model model) {
		// 验证用户
		User user = userService.find(input);
		if (user == null) {
			model.addAttribute("error", "username or password is wrong.");
			return "redirect:/";
		}

		// 授权
		String token = UUID.randomUUID().toString();
		request.getSession().setAttribute(AuthConst.IS_LOGIN, true);
		request.getSession().setAttribute(AuthConst.TOKEN, token);
		
		// 存储，用于注销
		SessionStorage.INSTANCE.set(token, request.getSession());

		// 子系统跳转过来的登录请求，授权、存储后，跳转回去
		String clientUrl = request.getParameter(AuthConst.CLIENT_URL);
		if (clientUrl != null && !"".equals(clientUrl)) {
			// 存储，用于注销
			ClientStorage.INSTANCE.set(token, clientUrl);
			return "redirect:" + clientUrl + "?" + AuthConst.TOKEN + "=" + token;
		}

		return "redirect:/";
	}

	
	/**
	 * @Description: 注销
	 * @param request
	 * @return String
	 * @Autor: Jason - Jasonandy@hotmail.com
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String token = request.getParameter(AuthConst.LOGOUT_REQUEST);
		
		// token存在于请求中，表示从客户端发起的注销；token存在于会话中，表示从认证中心发起的注销
		if (token != null && !"".equals(token)) {
			session = SessionStorage.INSTANCE.get(token);
		} else {
			token = (String) session.getAttribute(AuthConst.TOKEN);
		}
		
		if (session != null) {
			session.invalidate();
		}
		
		// 注销子系统
		List<String> list = ClientStorage.INSTANCE.get(token);
		if (list != null && list.size() > 0) {
			Map<String, String> params = new HashMap<String, String>();
			params.put(AuthConst.LOGOUT_REQUEST, token);
			for (String url : list) {
				HTTPUtil.post(url, params);
			}
		}
		
		return "redirect:/";
	}
}