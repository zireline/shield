package com.splitscale.shield.register;

import java.io.IOException;

import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.workflows.register.RegisterWorkflow;

public class Register {

  RegisterWorkflow workflow;

  public Register(RegisterWorkflow workflow) {
    this.workflow = workflow;
  }

  public void registerUser(UserRequest request) throws IOException {
    try {
      workflow.register(request);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
