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
package cn.ucaner.sso.server.service;

import cn.ucaner.sso.client.domain.User;

/**
* @Package：cn.ucaner.sso.server.service   
* @ClassName：UserService   
* @Description：   <p> UserService</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 下午1:47:31   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public interface UserService {
	
	/**
	 * @Description: 根据username和password查找数据库中的用户并返回
	 * @param user
	 * @return User
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	public User find(User user);
}