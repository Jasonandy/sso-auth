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

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
* @Package：cn.ucaners.sso.client.util   
* @ClassName：RSAUtil   
* @Description：   <p> RSAUtil</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 上午11:06:40   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class RSAUtil {
	
	private static Key PRIVATE_KEY;
	private static Key PUBLIC_KEY;

	private static final String ALGORITHM = "RSA";
	private static final int KEYSIZE = 1024;

	static {
		genKeyPair();
	}

	/**
	 * 生成RSA密钥对
	 */
	public static void genKeyPair() {
		KeyPairGenerator keyPairGenerator = null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyPairGenerator.initialize(KEYSIZE);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PRIVATE_KEY = keyPair.getPrivate();
		PUBLIC_KEY = keyPair.getPublic();
	}

	/**
	 * 使用RSA加密字符串
	 * @param src
	 * @return
	 */
	public static String encode(String src) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, PUBLIC_KEY);
			return Base64.encodeBase64String(cipher.doFinal(src.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用RSA解密字符串
	 * @param src
	 * @return
	 */
	public static String decode(String src) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
			return new String(cipher.doFinal(Base64.decodeBase64(src)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}