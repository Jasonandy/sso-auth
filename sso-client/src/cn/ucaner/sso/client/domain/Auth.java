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

import java.util.Date;

/**
* @Package：cn.ucaner.sso.client.domain   
* @ClassName：Auth   
* @Description：   <p> 授权信息</p>
* @Author： - Jason
* @CreatTime：2018年3月16日 上午11:11:04   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class Auth {
	// 授权用户
	private User user;
	// 随机码
	private String code;
	// 过期时间
	private Date expire;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}
}