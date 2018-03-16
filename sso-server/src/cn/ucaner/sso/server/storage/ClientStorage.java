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
package cn.ucaner.sso.server.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Package：cn.ucaner.sso.server.storage   
* @ClassName：ClientStorage   
* @Description：   <p> ClientStorage</p>
* @Author： - DaoDou   
* @CreatTime：2018年3月16日 下午1:47:00   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public enum ClientStorage {
	INSTANCE;
	private Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	
	public void set(String token, String url) {
		if (!map.containsKey(token)) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(url);
			map.put(token, list);
			return;
		}
		map.get(token).add(url);
	}
	
	public List<String> get(String token) {
		if (map.containsKey(token)) {
			return map.remove(token);
		}
		return null;
	}
}
