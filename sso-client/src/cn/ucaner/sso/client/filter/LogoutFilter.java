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
import java.util.HashMap;
import java.util.Map;

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
import cn.ucaner.sso.client.util.HTTPUtil;

/**
* @Package：cn.ucaner.sso.client.filter   
* @ClassName：LogoutFilter   
* @Description：   <p> 客户端注销filter</p>
* @Author： - DaoDou   
* @CreatTime：2018年3月16日 上午11:10:28   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class LogoutFilter implements Filter {
	
	private FilterConfig config;

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		String logoutUrl = config.getInitParameter(AuthConst.LOGOUT_URL);
		String token = (String) session.getAttribute(AuthConst.TOKEN);
		
		// 主动注销，即子系统提供的注销请求
		if ("/logout".equals(request.getRequestURI())) {
			// 向认证中心发送注销请求
			Map<String, String> params = new HashMap<String, String>();
			params.put(AuthConst.LOGOUT_REQUEST, token);
			HTTPUtil.post(logoutUrl, params);
			// 注销后重定向
			response.sendRedirect("/test");
			// 注销本地会话
			session = SessionStorage.INSTANCE.get(token);
			if (session != null) {
				session.invalidate();
			}
			return;
		}
		
		// 被动注销，即从认证中心发送的注销请求
		token = request.getParameter(AuthConst.LOGOUT_REQUEST);
		if (token != null && !"".equals(token)) {
			session = SessionStorage.INSTANCE.get(token);
			if (session != null) {
				session.invalidate();
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}
}