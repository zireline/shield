package com.splitscale.shield;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Component;

import com.splitscale.shield.invalidate.InvalidateJwt;
import com.splitscale.shield.validate.ValidJwtResponse;
import com.splitscale.shield.validate.ValidateJwt;
import com.splitscale.shield.login.Login;
import com.splitscale.shield.login.LoginResponse;
import com.splitscale.shield.register.Register;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.repository.ObjectNotFoundException;

@Component
public class Shield {
  private Login login;
  private Register register;
  private ValidateJwt validateJwt;
  private InvalidateJwt invalidateJwt;

  public Shield(Login login, Register register, ValidateJwt validateJwt, InvalidateJwt invalidateJwt) {
    this.login = login;
    this.register = register;
    this.validateJwt = validateJwt;
    this.invalidateJwt = invalidateJwt;
  }

  public LoginResponse loginUser(UserRequest request)
      throws IllegalArgumentException, IOException, ObjectNotFoundException {
    return login.loginUser(request);
  }

  public String registerUser(UserRequest request) throws IllegalArgumentException, IOException {
    return register.registerUser(request);
  }

  public ValidJwtResponse validateJwt(String jwtToken, String userId) throws GeneralSecurityException, IOException {
    return validateJwt.validate(jwtToken, userId);
  }

  public String inValidateJwt(String jwtToken) throws GeneralSecurityException {
    return invalidateJwt.invalidate(jwtToken);
  }
}