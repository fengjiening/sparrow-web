package com.fengjiening.bootstrap.config;

import com.fengjiening.pub.authc.interceptor.OnlineInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Boot 2.0 解决跨域问题
 *
 * @Author qinfeng
 *
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Value("${jeecg.path.upload}")
	private String upLoadPath;
	@Value("${jeecg.path.webapp}")
	private String webAppPath;

	@Value("${spring.resource.static-locations}")
	private String staticLocations;

	@Bean
	public OnlineInterceptor onlineInterceptor(){
		return new OnlineInterceptor();
	}


	//@Override
	//public void addCorsMappings(CorsRegistry registry) {
	//	registry.addMapping("/**")  // 拦截所有的请求
	//			//.allowedOrigins("*")  // 可跨域的域名，可以为 *
	//			.allowedOriginPatterns("*")
	//			.allowCredentials(true)
	//			.allowedMethods("*")   // 允许跨域的方法，可以单独配置
	//			.allowedHeaders("*");  // 允许跨域的请求头，可以单独配置
	//}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		/* 是否允许请求带有验证信息 */
		corsConfiguration.setAllowCredentials(true);
		/* 允许访问的客户端域名 */
		//corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedOriginPattern("*");
		/* 允许服务端访问的客户端请求头 */
		corsConfiguration.addAllowedHeader("*");
		/* 允许访问的方法名,GET POST等 */
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	/**
	 * 静态资源的配置 - 使得可以从磁盘中读取 Html、图片、视频、音频等
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		.addResourceLocations("file:" + upLoadPath + "//", "file:" + webAppPath + "//")
		.addResourceLocations(staticLocations.split(","));
	}

	/**
	 * 方案一： 默认访问根路径跳转 doc.html页面 （swagger文档页面）
	 * 方案二： 访问根路径改成跳转 index.html页面 （简化部署方案： 可以把前端打包直接放到项目的 webapp，上面的配置）
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("doc.html");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		String [] exculudes = new String[]{"/*.html","/html/**","/js/**","/css/**","/images/**"};
		registry.addInterceptor(onlineInterceptor()).excludePathPatterns(exculudes).addPathPatterns("/online/cgform/api/**");
	}
}
