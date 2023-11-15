package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.credential.update.UpdateCredentialUsernameInteractor;
import com.splitscale.shield.endpoints.users.update.UpdateUserInfo;
import com.splitscale.shield.repositories.CredentialRepository;
import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.update.UpdateUserInfoInteractor;
import com.splitscale.shield.workflows.updateuserinfo.UpdateUserInfoWorkflow;

@Configuration
public class UpdateUserInfoConfig {
  @Bean
  public UpdateUserInfo getUpdateUserInfo(UpdateUserInfoWorkflow workflow) {
    return new UpdateUserInfo(workflow);
  }

  @Bean
  public UpdateUserInfoWorkflow getUpdateUserInfoWorkflow(UpdateUserInfoInteractor updateUserInfoInteractor,
      UpdateCredentialUsernameInteractor updateCredentialInteractor) {
    return new UpdateUserInfoWorkflow(updateUserInfoInteractor, updateCredentialInteractor);
  }

  @Bean
  public UpdateUserInfoInteractor getUpdateUserInfoInteractor(UserInfoRepository repository) {
    return new UpdateUserInfoInteractor(repository);
  }

  @Bean
  public UpdateCredentialUsernameInteractor getUpdateCredentialUsernameInteractor(CredentialRepository repository) {
    return new UpdateCredentialUsernameInteractor(repository);
  }
}
