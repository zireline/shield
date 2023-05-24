package com.splitscale.shield.register;

import java.io.IOException;

import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.workflows.register.RegisterWorkflow;

public class Register {

  RegisterWorkflow workflow;

  public Register(RegisterWorkflow workflow) {
    this.workflow = workflow;
  }

  public String registerUser(UserRequest request) throws IOException, IllegalArgumentException {
    return workflow.register(request);
  }
}
