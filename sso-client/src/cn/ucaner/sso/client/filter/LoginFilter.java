/**
 * <html>
 * <body>
 *  <P> Copyright 1994-2018 JasonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2018年3月16日 </p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.sso.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ucaner.sso.client.constant.AuthConst;
import cn.ucaner.sso.client.storage.SessionStorage;

/**
* @Package：cn.ucaner.sso.client.filter   
* @ClassName：LoginFilter   
* @Description：   <p> 客户端登录filter</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 上午11:10:13   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class LoginFilter implements Filter {
	
	
	private FilterConfig config;

	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		// 已经登录，放行
		if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
			chain.doFilter(req, res);
			return;
		}
		// 从认证中心回跳的带有token的请求，有效则放行
		String token = request.getParameter(AuthConst.TOKEN);
		if (token != null) {
			session.setAttribute(AuthConst.IS_LOGIN, true);
			session.setAttribute(AuthConst.TOKEN, token);
			// 存储，用于注销
			SessionStorage.INSTANCE.set(token, session);
			chain.doFilter(req, res);
			return;
		}

		// 重定向至登录页面，并附带当前请求地址
		response.sendRedirect(config.getInitParameter(AuthConst.LOGIN_URL) + "?" + AuthConst.CLIENT_URL + "=" + request.getRequestURL());
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}
}