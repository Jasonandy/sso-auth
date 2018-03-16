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
package cn.ucaner.sso.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ucaner.sso.client.domain.User;
import cn.ucaner.sso.server.dao.UserDao;
import cn.ucaner.sso.server.service.UserService;

/**
* @Package：cn.ucaner.sso.server.service   
* @ClassName：UserServiceImpl   
* @Description：   <p> UserServiceImpl</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 下午1:47:14   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Value("${db.table}")
	private String table;
	
	@Value("${db.table.userid}")
	private String userid;
	
	@Value("${db.table.username}")
	private String username;
	
	@Value("${db.table.password}")
	private String password;

	public User find(User user) {
		return userDao.find(user, table, userid, username, password);
	}
}