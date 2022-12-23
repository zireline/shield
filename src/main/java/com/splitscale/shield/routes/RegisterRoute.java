package com.splitscale.shield.routes;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.jwt.JwtInteractor;
import com.splitscale.shield.registration.RegistrationSanitizer;

public class RegisterRoute {
  RegistrationSanitizer sanitizer;
  JwtInteractor jwtInteractor;

  public RegisterRoute(RegistrationSanitizer sanitizer, JwtInteractor jwtInteractor) {
    this.sanitizer = sanitizer;
    this.jwtInteractor = jwtInteractor;
  }

  public String register(UserRequest userRequest) throws Exception {
    // first step
    User user = sanitizer.sanitizeAndSave(userRequest);

    // make the toke and return
    return jwtInteractor.generateJwtFromUser(user);
  }
}
