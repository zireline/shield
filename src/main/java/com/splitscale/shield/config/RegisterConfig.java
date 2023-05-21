package com.splitscale.shield.config;

import com.splitscale.shield.register.Register;
import com.splitscale.shield.user.add.AddUserInteractor;
import com.splitscale.shield.user.read.ReadUserInteractor;
import com.splitscale.shield.user.workflows.register.RegisterWorkflow;

public class RegisterConfig {

  RegisterConfig() {
    // default configuration
  }

  public static Register getRegister() {
    AddUserInteractor interactor = UserRepositoryConfig.getAddUserInteractor();
    ReadUserInteractor userReader = UserRepositoryConfig.getReadUserInteractor();

    RegisterWorkflow workflow = getRegisterWorkflow(interactor, userReader);

    return new Register(workflow);
  }

  private static RegisterWorkflow getRegisterWorkflow(AddUserInteractor interactor, ReadUserInteractor userReader) {
    return new RegisterWorkflow(interactor, userReader);
  }

}
