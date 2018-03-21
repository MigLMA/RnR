package com.cognizant.rnr.controller.sandbox.cors.test;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.rnr.controller.sandbox.cors.test.Greeting;

@RestController
@Profile("dev")
public class GreetingController {

  private static final String TEMPLATE = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
  }

  @RequestMapping(value = "/greeting-javaconfig", method = RequestMethod.GET)
  public Greeting greetingWithJavaconfig(
      @RequestParam(required = false, defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
  }
}
