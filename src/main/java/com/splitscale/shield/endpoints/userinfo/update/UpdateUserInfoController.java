package com.splitscale.shield.endpoints.userinfo.update;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitscale.shield.shielduser.ShieldUser;

@RestController
@RequestMapping("/api/v1")
public class UpdateUserInfoController {
  private UpdateUserInfo updateUserInfo;

  public UpdateUserInfoController(UpdateUserInfo updateUserInfo) {
    this.updateUserInfo = updateUserInfo;
  }

  @ResponseBody
  @PutMapping("/userInfo")
  public ResponseEntity<Object> updateUserInfo(@RequestBody ShieldUser request) throws IOException {
    updateUserInfo.update(request);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Exception handlers
  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleInternalServerError(IOException e) {
    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
