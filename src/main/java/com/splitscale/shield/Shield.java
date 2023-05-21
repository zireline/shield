package com.splitscale.shield;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Component;

import com.splitscale.shield.jwt.invalidate.InvalidateJwt;
import com.splitscale.shield.jwt.validate.ValidJwtResponse;
import com.splitscale.shield.jwt.validate.ValidateJwt;
import com.splitscale.shield.login.Login;
import com.splitscale.shield.login.LoginResponse;
import com.splitscale.shield.register.Register;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.repository.ObjectNotFoundException;

import io.jsonwebtoken.security.InvalidKeyException;

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

  public LoginResponse loginUser(UserRequest request) throws InvalidKeyException, IOException, ObjectNotFoundException {
    return login.loginUser(request);
  }

  public void registerUser(UserRequest request) throws InvalidKeyException, IOException {
    register.registerUser(request);
  }

  public ValidJwtResponse validateJwt(String jwtToken, String userId) throws GeneralSecurityException, IOException {
    return validateJwt.validate(jwtToken, userId);
  }

  public String inValidateJwt(String jwtToken) throws GeneralSecurityException {
    return invalidateJwt.invalidate(jwtToken);
  }
}
