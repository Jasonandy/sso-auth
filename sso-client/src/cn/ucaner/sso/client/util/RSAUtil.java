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
* @Description：   <p> RSAUtil
* RSA是"非对称加密算法"，非对称加密算法需要两个密钥：公开密钥（publickey）和私有密钥（privatekey）。
* 公钥与私钥是配对的，用公钥加密的数据只有配对的私钥才能解密，反之亦然。因加解密使用两个不同的密钥，所以这种算法叫作非对称加密算法。
*　非对称算法的在应用的过程如下，假设发送方向接收方发送消息（明文）：
*　1.接收方生成公钥和私钥，公钥公开，私钥保留；
*　2.发送方将要发送的消息采用公钥加密，得到密文，然后将密文发送给接收方；
*　3.接收方收到密文后，用自己的私钥进行解密，获得明文。　可以看出，非对称加密解决了对称加密密钥传输的问题。
* {@link https://www.cnblogs.com/songwenlong/p/6523652.html}
* </p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 上午11:06:40   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class RSAUtil {
	
	/**
	 * 私钥
	 */
	private static Key PRIVATE_KEY;
	
	/**
	 * 公钥
	 */
	private static Key PUBLIC_KEY;

	/**
	 * 算法名
	 */
	private static final String ALGORITHM = "RSA";
	
	/**
	 * 长度
	 */
	private static final int KEYSIZE = 1024;

	/**
	 * 静态代码块
	 * 
	 */
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
			cipher.init(Cipher.ENCRYPT_MODE, PUBLIC_KEY);//公钥加密
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
			cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);//私钥解密
			return new String(cipher.doFinal(Base64.decodeBase64(src)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description: Just for test 
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	public static void main(String[] args) {
		String encodeStr = "HelloWorld";
		String encode = encode(encodeStr);
		String format = String.format("明文:%s \n 密文:%s", encodeStr,encode);
		//Jason//23个字数172个字符
		System.out.println(format);
		
		String decode = decode(encode);
		String format2 = String.format("加密后数据:%s \n 解密后:%s", encode,decode);
		System.out.println(format2);
		/*明文:Jason 
		 密文:Qogs28Ejr6PBf1Mrtmg5onZjUOUiOk7jQ5jP5C+cUMjoETz4kq6mhi4GA9N7hZAIqQbXn9LC4eFnTMO8dHFy9rBGBi1z4ExlJA587pk6DdRvrxdOAaXNAdtX4eHW/xkqHl79DSxJg+RRd99SW3bJAi/xrJOZ9RVGz6w5MRoCOw8=
		加密后数据:Qogs28Ejr6PBf1Mrtmg5onZjUOUiOk7jQ5jP5C+cUMjoETz4kq6mhi4GA9N7hZAIqQbXn9LC4eFnTMO8dHFy9rBGBi1z4ExlJA587pk6DdRvrxdOAaXNAdtX4eHW/xkqHl79DSxJg+RRd99SW3bJAi/xrJOZ9RVGz6w5MRoCOw8= 
		 解密后:Jason*/
		
		/*明文:HelloWorld 
		 密文:RWx4HjYvygFyZl9Zk072SHGuY/SKvpupk6mqjUPBfLrKCOQUg+Dng/l1fcGoeU8+d8M/93Af9Zabznru8HUyyOaVcJ6oOQ5Bb1DNx2Pn1GqIAFuRDqeASaQcCXu/vErxTOH7INIsxzDqyxypi7R32AQc/RuhYABciDNz24KXOks=
		加密后数据:RWx4HjYvygFyZl9Zk072SHGuY/SKvpupk6mqjUPBfLrKCOQUg+Dng/l1fcGoeU8+d8M/93Af9Zabznru8HUyyOaVcJ6oOQ5Bb1DNx2Pn1GqIAFuRDqeASaQcCXu/vErxTOH7INIsxzDqyxypi7R32AQc/RuhYABciDNz24KXOks= 
		 解密后:HelloWorld*/
	}
}