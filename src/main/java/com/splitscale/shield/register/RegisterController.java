package com.splitscale.shield.register;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.shield.user.UserRequest;

import io.jsonwebtoken.security.InvalidKeyException;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {
  private Register register;

  public RegisterController(Register register) {
    this.register = register;
  }

  @ResponseBody
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserRequest request)
      throws InvalidKeyException, IOException {
    String id = register.registerUser(request);

    return new ResponseEntity<>(id, HttpStatus.OK);
  }

  // Exception handlers
  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleInternalServerError(IllegalArgumentException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidKeyException.class)
  public ResponseEntity<String> handleGeneralSecurityException(InvalidKeyException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
