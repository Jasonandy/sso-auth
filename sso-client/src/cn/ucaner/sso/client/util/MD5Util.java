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
	 * @Description: MD5加密字符串
	 * @param src
	 * @return String
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	public static String encode(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	/**
	 * @Description: 获取MD5加密数据
	 * @param source
	 * @return String
	 * @Autor: Jason - jasonandy@hotamil.com
	 */
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd','e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); 
			char str[] = new char[16 * 2]; 
			int k = 0; 
			for (int i = 0; i < 16; i++) { 
				byte byte0 = tmp[i]; 
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				str[k++] = hexDigits[byte0 & 0xf]; 
			}
			s = new String(str); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * @Description: Just for test 
	 */
	public static void main(String[] args) {
		String str = "HelloWorld";
		String encode = encode(str);
		String format = String.format("加密前数据: %s \n 加密后数据: %s", str,encode);
		System.out.println(format);
		/*加密前数据: Jason 
 		     加密后数据: 472d46cb829018f9dbd65fb8479a49bb
 		    加密前数据: HelloWorld 
		     加密后数据: 68e109f0f40ca72a15e05cc22786f8e6 */
	}
	
}