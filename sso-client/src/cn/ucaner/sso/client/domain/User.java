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
package cn.ucaner.sso.client.domain;

/**
* @Package：cn.ucaner.sso.client.domain   
* @ClassName：User   
* @Description：   <p> 用户基本信息</p>
* @Author： - DaoDou   
* @CreatTime：2018年3月16日 上午11:10:49   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class User {
	// id
	private String id;
	// 名称
	private String username;
	// 密码
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}