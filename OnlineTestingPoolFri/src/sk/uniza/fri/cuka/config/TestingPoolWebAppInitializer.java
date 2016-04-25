package sk.uniza.fri.cuka.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class TestingPoolWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Moje Beans (middle-tier, data-tier)
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	/**
	 * Handlers pre Dispatcher Servlet (Spring Veci) napr viewResolver, kodovanie, resources, SpringMVC...
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	/**
	 * Defaultne mapovanie pre Dispatcher Servlet, prva url co sa spusti....
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
