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
package cn.ucaner.common.framework.common.spring.ext;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.selector.ContextSelector;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import ch.qos.logback.core.joran.spi.JoranException;

/**
* @Package：cn.ucaner.common.framework.common.spring.ext   
* @ClassName：LogbackConfigurer   
* @Description：   <p> LogbackConfigurer</p>
* @Author： - Jason
* @CreatTime：2018年3月13日 下午3:54:18   
* @Modify By：   
* @ModifyTime：  2018年3月13日
* @Modify marker：   
* @version    V1.0
 */
public class LogbackConfigurer {

	private LogbackConfigurer() {
	}

	/**
	 * Initialize logback from the given file.
	 *
	 * @param location the location of the config file: either a "classpath:" location
	 *                 (e.g. "classpath:logback.xml"), an absolute file URL
	 *                 (e.g. "file:C:/logback.xml), or a plain absolute path in the file system
	 *                 (e.g. "C:/logback.xml")
	 * @throws java.io.FileNotFoundException if the location specifies an invalid file path
	 * @throws ch.qos.logback.core.joran.spi.JoranException
	 *                                       Thrown
	 */
	public static void initLogging(String location) throws FileNotFoundException, JoranException {
		String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
		URL url = ResourceUtils.getURL(resolvedLocation);
		LoggerContext loggerContext = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();

		// in the current version logback automatically configures at startup the context, so we have to reset it
		loggerContext.reset();

		// reinitialize the logger context.  calling this method allows configuration through groovy or xml
		new ContextInitializer(loggerContext).configureByResource(url);
	}

	/**
	 * Set the specified system property to the current working directory.
	 * <p/>
	 * This can be used e.g. for test environments, for applications that leverage
	 * LogbackWebConfigurer's "webAppRootKey" support in a web environment.
	 *
	 * @param key system property key to use, as expected in Logback configuration
	 *            (for example: "demo.root", used as "${demo.root}/WEB-INF/demo.log")
	 * @see ch.qos.logback.ext.spring.web.WebLogbackConfigurer WebLogbackConfigurer
	 */
	public static void setWorkingDirSystemProperty(String key) {
		System.setProperty(key, new File("").getAbsolutePath());
	}

	/**
	 * Shut down Logback.
	 * <p/>
	 * This isn't strictly necessary, but recommended for shutting down
	 * logback in a scenario where the host VM stays alive (for example, when
	 * shutting down an application in a J2EE environment).
	 */
	public static void shutdownLogging() {
		ContextSelector selector = ContextSelectorStaticBinder.getSingleton().getContextSelector();
		LoggerContext loggerContext = selector.getLoggerContext();
		String loggerContextName = loggerContext.getName();
		LoggerContext context = selector.detachLoggerContext(loggerContextName);
		context.reset();
	}
}
