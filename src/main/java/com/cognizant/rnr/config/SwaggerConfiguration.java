package com.cognizant.rnr.config;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger configuration class
 * 
 * @author 628700
 * @since 0.0.0
 */
@Profile("dev")
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

  @Value("${info.build.version}")
  private String buildVersion;

  @Value("${info.swagger.title}")
  private String apiTitle;

  @Value("${info.swagger.description}")
  private String apiDescription;

  @Value("${info.swagger.license}")
  private String apiLicense;

  @Value("${info.swagger.licenseUrl}")
  private String apiLicenseUrl;

  @Value("${info.swagger.termsOfServiceUrl}")
  private String apiTermsOfServiceUrl;

  @Value("${info.swagger.contact.name}")
  private String apiContactName;

  @Value("${info.swagger.contact.url}")
  private String apiContactUrl;

  @Value("${info.swagger.contact.email}")
  private String apiContactEmail;

  /**
   * Publish a bean to generate swagger2 endpoints
   * 
   * @return a swagger configuration bean
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(apiPaths())
        .apis(RequestHandlerSelectors.any()).build().useDefaultResponseMessages(false);
  }


  /**
   * Api info
   * 
   * @return ApiInfo
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title(apiTitle).version(buildVersion).description(apiDescription)
        .license(apiLicense).licenseUrl(apiLicenseUrl).termsOfServiceUrl(apiTermsOfServiceUrl)
        .contact(new Contact(apiContactName, apiContactUrl, apiContactEmail)).build();
  }

  /**
   * Config paths. Return PathSelectors.any() or configure the predicate
   *
   * @return the predicate
   */
  private Predicate<String> apiPaths() {
    return regex("/api.*");
  }
}
