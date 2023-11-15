package com.splitscale.shield.endpoints.users.get;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.shield.userinfo.UserInfo;

@RestController
@RequestMapping("/auth/v1")
public class GetAllUserInfoController {
  private GetAllUserInfo getAllUserInfo;

  public GetAllUserInfoController(GetAllUserInfo getAllUserInfo) {
    this.getAllUserInfo = getAllUserInfo;
  }

  @ResponseBody
  @GetMapping("/users")
  public ResponseEntity<List<UserInfo>> updateUserInfo(@RequestParam(name = "token") String token)
      throws IOException, GeneralSecurityException {

    // For information about the token please see:
    // https://discord.com/channels/1123626960833228852/1126775716567060581/1173807457756266516
    String accessToken = "chaddenard";
    boolean isValid = token.matches(accessToken);

    System.out.println("[token match]: " + isValid);

    if (!isValid) {
      throw new GeneralSecurityException(
          "Invalid token, please use your SUPER_ADMIN_PASSWORD token since this token is hardcoded in this endpoint");
    }

    List<UserInfo> users = getAllUserInfo.getALl();

    return new ResponseEntity<>(users, HttpStatus.OK);
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
