package com.splitscale.shield.endpoints.login;

import java.io.IOException;
import java.security.GeneralSecurityException;

import io.jsonwebtoken.security.InvalidKeyException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.repositories.ObjectNotFoundException;

@RestController
@RequestMapping("/auth/v1/credential")
public class LoginController {
  private Login login;

  public LoginController(Login login) {
    this.login = login;
  }

  @ResponseBody
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> loginUser(@RequestBody CredentialRequest request)
      throws InvalidKeyException, IOException, ObjectNotFoundException {
    LoginResponse response = login.loginUser(request);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // Exception handlers
  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidKeyException.class)
  public ResponseEntity<String> handleGeneralSecurityException(InvalidKeyException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(GeneralSecurityException.class)
  public ResponseEntity<String> handleGeneralSecurityException(GeneralSecurityException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }
}
