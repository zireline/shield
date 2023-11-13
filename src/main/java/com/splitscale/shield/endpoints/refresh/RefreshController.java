package com.splitscale.shield.endpoints.refresh;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.shield.endpoints.login.Tokens;

import io.jsonwebtoken.security.InvalidKeyException;

@RestController
@RequestMapping("/auth/v1")
public class RefreshController {
  private Refresh refresh;

  public RefreshController(Refresh refresh) {
    this.refresh = refresh;
  }

  @ResponseBody
  @PostMapping("/credential/refresh")
  public ResponseEntity<Tokens> refreshToken(@RequestBody RefreshRequest request)
      throws InvalidKeyException, IOException {

    System.out.println("[Request] Refresh UserId: " + request.getUserId());
    System.out.println("[Request] Refresh Token: " + request.getRefreshToken());

    Tokens tokensResponse = refresh.refresh(request);

    return new ResponseEntity<>(tokensResponse, HttpStatus.OK);
  }

  // Exception handlers
  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleInternalServerError(IllegalArgumentException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(InvalidKeyException.class)
  public ResponseEntity<String> handleGeneralSecurityException(InvalidKeyException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
