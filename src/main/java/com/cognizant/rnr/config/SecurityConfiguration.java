package com.cognizant.rnr.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Allows to access properly the resources for the D2 database console.
 *
 * @author 497046
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests()
        .antMatchers("/console/**").permitAll();

    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();

  }

  @Bean
  ServletRegistrationBean<WebServlet> h2servletRegistration() {
    ServletRegistrationBean<WebServlet> registrationBean =
        new ServletRegistrationBean<>(new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
  }
}
