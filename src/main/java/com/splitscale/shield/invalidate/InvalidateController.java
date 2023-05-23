package com.splitscale.shield.invalidate;

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
public class InvalidateController {
  private InvalidateJwt invalidateJwt;

  public InvalidateController(InvalidateJwt invalidateJwt) {
    this.invalidateJwt = invalidateJwt;
  }

  @ResponseBody
  @PostMapping("/inValidateJwt")
  public ResponseEntity<String> inValidateJwt(@RequestBody String jwtToken)
      throws GeneralSecurityException {
    String response = invalidateJwt.invalidate(jwtToken);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @ExceptionHandler(GeneralSecurityException.class)
  public ResponseEntity<String> handleGeneralSecurityException(GeneralSecurityException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }
}
