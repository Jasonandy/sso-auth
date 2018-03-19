/**
 * <html>
 * <body>
 *  <P> Copyright 1994-2018. JasonInternational.</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2018年3月13日 下午3:45:46</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.common.framework.freemark;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**     
 * @Package：cn.ucaner.common.framework.freemark   
 * @ClassName：CustomerFreeMark   
 * @Description：   <p> 自定义视图</p>
 * @Author： - Jason   
 * @CreatTime：2018年3月13日 下午3:45:46   
 * @Modify By：   
 * @ModifyTime：  2018年3月13日
 * @Modify marker：   
 * @version    V1.0
 */
public class CustomerFreeMark  extends FreeMarkerView{
	
	public static Logger logger = LoggerFactory.getLogger(CustomerFreeMark.class);
	
	public static String DEFAULT_VERSION_NUMBER = "1.0";
	
	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		model.put("path", path);
		super.exposeHelpers(model, request);
	}

}
