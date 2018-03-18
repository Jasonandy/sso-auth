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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
* @Package：cn.ucaner.sso.client.util   
* @ClassName：HTTPUtil   
* @Description：   <p> HTTPUtil</p>
* @Author： - Jason   
* @CreatTime：2018年3月16日 上午11:13:12   
* @Modify By：   
* @ModifyTime：  2018年3月16日
* @Modify marker：   
* @version    V1.0
 */
public class HTTPUtil {
	
	/**
	 * @Description: 向目标url发送post请求
	 * @param url
	 * @param params
	 * @return boolean
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	public static boolean post(String url, Map<String, String> params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		// 参数处理
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(list, Consts.UTF_8));
		}
		// 执行请求
		try {
			CloseableHttpResponse response = httpclient.execute(httpPost);
			response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * @Description: sso.ucaner.cn
	 * @param args void
	 * @Autor: Jason - jasonandy@hotmail.com
	 */
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("clientUrl", "httputil");
		post("http://sso.ucaner.cn/", params);
	}
}