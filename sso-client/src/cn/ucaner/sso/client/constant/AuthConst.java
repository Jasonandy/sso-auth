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
package cn.ucaner.sso.client.constant;

/**
* @Package：cn.ucaner.sso.client.constant   
* @ClassName：AuthConst   
* @Description：   <p> 授权过程中涉及到的常量字符串</p>
* @Author： - DaoDou   
* @CreatTime：2018年3月16日 上午11:04:42   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public interface AuthConst {
	
	// 会话是否授权标志
	public static String IS_LOGIN = "isLogin";
	// 登录中心url
	public static String LOGIN_URL = "loginUrl";
	// 注销url
	public static String LOGOUT_URL = "logoutUrl";
	// 客户端url
	public static String CLIENT_URL = "clientUrl";
	// 授权令牌
	public static String TOKEN = "token";
	// 注销请求
	public static String LOGOUT_REQUEST = "logoutRequest";
}