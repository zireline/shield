package com.splitscale.shield;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.shield.config.InValidateJwtConfig;
import com.splitscale.shield.config.LoginConfig;
import com.splitscale.shield.config.RegisterConfig;
import com.splitscale.shield.config.ValidateJwtConfig;
import com.splitscale.shield.invalidate.InvalidateJwt;
import com.splitscale.shield.validate.ValidJwtResponse;
import com.splitscale.shield.validate.ValidateJwt;
import com.splitscale.shield.login.Login;
import com.splitscale.shield.login.LoginResponse;
import com.splitscale.shield.register.Register;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.repository.ObjectNotFoundException;

import io.jsonwebtoken.security.InvalidKeyException;

public class Shield {
  private static Login login;
  private static Register register;
  private static ValidateJwt validateJwt;
  private static InvalidateJwt invalidateJwt;

  public static void initialize() {
    login = LoginConfig.getLogin();
    register = RegisterConfig.getRegister();
    validateJwt = ValidateJwtConfig.getValidateJwt();
    invalidateJwt = InValidateJwtConfig.getInValidateJwt();
  }

  public static LoginResponse loginUser(UserRequest request)
      throws InvalidKeyException, IOException, ObjectNotFoundException {
    return login.loginUser(request);
  }

  public static void registerUser(UserRequest request) throws InvalidKeyException, IOException {
    register.registerUser(request);
  }

  public static ValidJwtResponse validateJwt(String jwtToken, String userId)
      throws GeneralSecurityException, IOException {
    return validateJwt.validate(jwtToken, userId);
  }

  public static String inValidateJwt(String jwtToken) throws GeneralSecurityException {
    return invalidateJwt.invalidate(jwtToken);
  }
}
