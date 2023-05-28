package com.splitscale.shield;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Component;

import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.endpoints.invalidate.InvalidateJwt;
import com.splitscale.shield.endpoints.login.Login;
import com.splitscale.shield.endpoints.login.LoginResponse;
import com.splitscale.shield.endpoints.register.Register;
import com.splitscale.shield.endpoints.userinfo.update.UpdateUserInfo;
import com.splitscale.shield.endpoints.validate.ValidJwtResponse;
import com.splitscale.shield.endpoints.validate.ValidateJwt;
import com.splitscale.shield.repositories.ObjectNotFoundException;
import com.splitscale.shield.shielduser.ShieldUser;

@Component
public class Shield {
  private Login login;
  private Register register;
  private ValidateJwt validateJwt;
  private InvalidateJwt invalidateJwt;
  private UpdateUserInfo updateUserInfo;

  public Shield(Login login, Register register, ValidateJwt validateJwt, InvalidateJwt invalidateJwt,
      UpdateUserInfo updateUserInfo) {
    this.login = login;
    this.register = register;
    this.validateJwt = validateJwt;
    this.invalidateJwt = invalidateJwt;
    this.updateUserInfo = updateUserInfo;
  }

  public LoginResponse loginUser(CredentialRequest request)
      throws IllegalArgumentException, IOException, ObjectNotFoundException {
    return login.loginUser(request);
  }

  public String registerUser(CredentialRequest request) throws IllegalArgumentException, IOException {
    return register.registerUser(request);
  }

  public ValidJwtResponse validateJwt(String jwtToken, String userId) throws GeneralSecurityException, IOException {
    return validateJwt.validate(jwtToken, userId);
  }

  public String inValidateJwt(String jwtToken) throws GeneralSecurityException {
    return invalidateJwt.invalidate(jwtToken);
  }

  public void updateShieldUser(ShieldUser shieldUser) throws IOException {
    updateUserInfo.update(shieldUser);
  }
}