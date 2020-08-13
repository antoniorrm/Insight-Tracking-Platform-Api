package br.com.insight.api.swagger2;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class ResourceHandlerConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html**").addResourceLocations("classpath:/resources/swagger-ui.html");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/resources/webjars/");
	}
}
