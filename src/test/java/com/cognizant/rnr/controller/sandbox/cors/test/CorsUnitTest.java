package com.cognizant.rnr.controller.sandbox.cors.test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.cognizant.rnr.config.RestConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@SuppressWarnings("unchecked")
public class CorsUnitTest {

  private final String SOURCE_DOMAIN = "https://differentdomain.com";

  private MockMvc mockMvc;

  // @Mock
  @Autowired
  private FilterRegistrationBean corsFilter;

  @InjectMocks
  private GreetingController greetingController;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(greetingController).addFilters(corsFilter.getFilter())
        .build();
  }

  @Test
  public void mustNotAllowRequestIfCorsDoesnHaveSourceDomainAllowedAsOrigin() throws Exception {
    final String ALLOWED_DOMAINS = "http://localhost";

    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin(ALLOWED_DOMAINS);
    config.addAllowedHeader("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);

    mockMvc =
        MockMvcBuilders.standaloneSetup(greetingController).addFilters(bean.getFilter()).build();

    mockMvc.perform(options("/greeting")
        // CORS HEADERS
        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
        .header(HttpHeaders.ORIGIN, SOURCE_DOMAIN)).andExpect(status().is(// 403
            HttpStatus.FORBIDDEN.value()));

  }

  @Test
  public void corsShouldAllowRequestOptionsGreetingAndHasRightHeaders() throws Exception {
    mockMvc.perform(options("/greeting")
        // CORS HEADERS
        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
        .header(HttpHeaders.ORIGIN, SOURCE_DOMAIN)).andExpect(status().isOk())
        // @SafeVarargs
        .andExpect(header().stringValues(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,
            hasItems(containsString("POST"), containsString("GET"), containsString("PUT"),
                containsString("OPTIONS"), containsString("DELETE"))))
        .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true"))
        .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_MAX_AGE,
            ((Long) RestConfiguration.CORS_MAX_AGE).toString()))
        .andExpect(header().stringValues(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, SOURCE_DOMAIN));

  }


  @Test
  public void corsShouldAllowGetRequestWithDefaultAnnotation() throws Exception {
    mockMvc.perform(get("/greeting")
        // CORS HEADERS
        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
        .header(HttpHeaders.ORIGIN, SOURCE_DOMAIN)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(content().json(getExpectedJsonString("World")))
        .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true"))
        .andExpect(header().stringValues(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, SOURCE_DOMAIN));
  }

  @Test
  public void corsShouldAllowGetRequestWithAnnotationUsingParameter() throws Exception {
    final String user1 = "User1";
    mockMvc.perform(get("/greeting?name=" + user1)
        // CORS HEADERS
        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
        .header(HttpHeaders.ORIGIN, SOURCE_DOMAIN)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(content().json(getExpectedJsonString(user1)))
        .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true"))
        .andExpect(header().stringValues(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, SOURCE_DOMAIN));
  }

  @Test
  public void corsShouldAllowGetRequestWithJavaconfig() throws Exception {
    mockMvc.perform(get("/greeting-javaconfig")
        // CORS HEADERS
        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
        .header(HttpHeaders.ORIGIN, SOURCE_DOMAIN)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(content().json(getExpectedJsonString("World")))
        .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true"))
        .andExpect(header().stringValues(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, SOURCE_DOMAIN));
  }

  private String getExpectedJsonString(String name) throws JSONException {
    String jsonString =
        new JSONObject().put("id", 1).put("content", "Hello, " + name + "!").toString();
    return jsonString;
  }

}

