package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.register.Register;
import com.splitscale.shield.user.add.AddUserInteractor;
import com.splitscale.shield.user.read.ReadUserInteractor;
import com.splitscale.shield.user.repository.UserRepository;
import com.splitscale.shield.user.workflows.register.RegisterWorkflow;

@Configuration
public class RegisterConfig {
  @Bean
  Register getRegister(RegisterWorkflow workflow) {
    return new Register(workflow);
  }

  @Bean
  RegisterWorkflow gRegisterWorkflow(AddUserInteractor interactor, ReadUserInteractor userReader) {
    return new RegisterWorkflow(interactor, userReader);
  }

  @Bean
  AddUserInteractor gAddUserInteractor(UserRepository repository) {
    return new AddUserInteractor(repository);
  }
}
