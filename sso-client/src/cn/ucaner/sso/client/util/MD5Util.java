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
package cn.ucaner.sso.client.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
* @Package：cn.ucaner.sso.client.util   
* @ClassName：MD5Util   
* @Description：   <p> MD5Util</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 上午11:21:28   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class MD5Util {
	/**
	 * MD5加密字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String encode(String src) {
		return DigestUtils.md5Hex(src);
	}
}