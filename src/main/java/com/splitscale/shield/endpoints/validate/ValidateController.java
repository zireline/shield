package com.splitscale.shield.endpoints.validate;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ValidateController {
  private ValidateJwt validateJwt;

  public ValidateController(ValidateJwt validateJwt) {
    this.validateJwt = validateJwt;
  }

  @ResponseBody
  @PostMapping("/validateJwt")
  public ResponseEntity<ValidJwtResponse> validateJwt(@RequestBody ValidateRequest request)
      throws GeneralSecurityException, IOException {
    ValidJwtResponse response = validateJwt.validate(request.getJwtToken(), request.getUserId());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // Exception handlers
  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(GeneralSecurityException.class)
  public ResponseEntity<String> handleGeneralSecurityException(GeneralSecurityException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }
}
