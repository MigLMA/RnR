package com.cognizant.rnr.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Generate a bean of RestOperation Interface.
 * @author 628700
 * @since 0.0.0
 */
@Configuration
public class RestConfiguration {

    public static final long CORS_MAX_AGE = 3600L;

	@Bean
    public RestOperations restOperations (){
        return new RestTemplate();
    }
   
    /**
     * @see <a href="https://spring.io/blog/2015/06/08/cors-support-in-spring-framework">cors-support-in-spring-framework</a>
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            // @formatter:off
            		registry.addMapping("/**")
            			.allowCredentials( true )
            			.allowedOrigins("*")
            			.allowedHeaders("*")
            			.allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
            			.maxAge( CORS_MAX_AGE ); 
			// @formatter:on
            }
        };
    }
    
    @Bean
	public FilterRegistrationBean corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("DELETE");
	    config.setMaxAge( CORS_MAX_AGE );
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

}
