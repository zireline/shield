package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.credential.add.AddCredentialInteractor;
import com.splitscale.shield.credential.read.ReadCredentialInteractor;
import com.splitscale.shield.endpoints.register.Register;
import com.splitscale.shield.repositories.CredentialRepository;
import com.splitscale.shield.userinfo.add.AddUserInfoInteractor;
import com.splitscale.shield.workflows.register.RegisterWorkflow;

@Configuration
public class RegisterConfig {
  @Bean
  Register getRegister(RegisterWorkflow workflow) {
    return new Register(workflow);
  }

  @Bean
  RegisterWorkflow getRegisterWorkflow(AddCredentialInteractor interactor, ReadCredentialInteractor userReader,
      AddUserInfoInteractor addUserInfoInteractor) {
    return new RegisterWorkflow(interactor, userReader, addUserInfoInteractor);
  }

  @Bean
  AddCredentialInteractor getAddUserInteractor(CredentialRepository repository) {
    return new AddCredentialInteractor(repository);
  }
}
