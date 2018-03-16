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
package cn.ucaner.sso.server.dao;

import org.apache.ibatis.annotations.Param;

import cn.ucaner.sso.client.domain.User;

/**
* @Package：cn.ucaner.sso.server.dao   
* @ClassName：UserDao   
* @Description：   <p> UserDao</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 下午1:49:04   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public interface UserDao {
	
	
	public User find(@Param("user") User user, @Param("table") String table, @Param("userid") String userid,
			@Param("username") String username, @Param("password") String password);
}