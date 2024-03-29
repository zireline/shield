package com.splitscale.shield.endpoints.register;

import java.io.IOException;

import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.workflows.register.RegisterWorkflow;

public class Register {

  RegisterWorkflow workflow;

  public Register(RegisterWorkflow workflow) {
    this.workflow = workflow;
  }

  public void registerUser(CredentialRequest request) throws IOException, IllegalArgumentException {
    workflow.register(request);
  }
}
