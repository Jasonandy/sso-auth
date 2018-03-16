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
package cn.ucaner.sso.client.storage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
* @Package：cn.ucaner.sso.client.storage   
* @ClassName：SessionStorage   
* @Description：   <p> Seesion存储信息</p>
* @Author： - DaoDou   
* @CreatTime：2018年3月16日 上午11:11:20   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public enum SessionStorage {
	INSTANCE;
	private Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	public void set(String token, HttpSession session) {
		map.put(token, session);
	}
	
	public HttpSession get(String token) {
		if (map.containsKey(token)) {
			return map.remove(token);
		}
		return null;
	}
}