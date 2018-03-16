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
package cn.ucaner.sso.server.filter;

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
import cn.ucaner.sso.server.storage.ClientStorage;

/**
* @Package：cn.ucaner.sso.server.filter   
* @ClassName：SessionFilter   
* @Description：   <p> sso认证中心会话过滤</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 下午1:48:44   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class SessionFilter implements Filter{
	
	
	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		
		// 注销请求，放行
		if ("/logout".equals(uri)) {
			chain.doFilter(req, res);
			return;
		}
		
		// 已经登录，放行
		if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
			// 如果是客户端发起的登录请求，跳转回客户端，并附带token
			String clientUrl = request.getParameter(AuthConst.CLIENT_URL);
			String token = (String) session.getAttribute(AuthConst.TOKEN);
			if (clientUrl != null && !"".equals(clientUrl)) {
				// 存储，用于注销
				ClientStorage.INSTANCE.set(token, clientUrl);
				response.sendRedirect(clientUrl + "?" + AuthConst.TOKEN + "=" + token);
				return;
			}
			if (!"/success".equals(uri)) {
				response.sendRedirect("/success");
				return;
			}
			chain.doFilter(req, res);
			return;
		}
		// 登录请求，放行
		if ("/".equals(uri) || "/login".equals(uri)) {
			chain.doFilter(req, res);
			return;
		}
		
		// 其他请求，拦截
		response.sendRedirect("/");
	}

	public void init(FilterConfig config) throws ServletException {}
}