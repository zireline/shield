package com.splitscale.shield.register;

import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.workflows.register.RegisterWorkflow;

public class Register {

  RegisterWorkflow workflow;

  public Register(RegisterWorkflow workflow) {
    this.workflow = workflow;
  }

  public void registerUser(UserRequest request) {
    workflow.register(request);
  }
}
