package com.splitscale.shield;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SecurityServiceApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(SecurityServiceApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }
}
