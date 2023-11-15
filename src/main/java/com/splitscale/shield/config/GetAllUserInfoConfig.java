package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.endpoints.users.get.GetAllUserInfo;
import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.getall.GetAllUserInfoInteractor;
import com.splitscale.shield.workflows.updateuserinfo.GetAllUserInfoWorkflow;

@Configuration
public class GetAllUserInfoConfig {
  @Bean
  public GetAllUserInfo getAllUserInfo(GetAllUserInfoWorkflow workflow) {
    return new GetAllUserInfo(workflow);
  }

  @Bean
  public GetAllUserInfoWorkflow getAllUserInfoWorkflow(GetAllUserInfoInteractor interactor) {
    return new GetAllUserInfoWorkflow(interactor);
  }

  @Bean
  public GetAllUserInfoInteractor getAllUserInfoInteractor(UserInfoRepository repository) {
    return new GetAllUserInfoInteractor(repository);
  }
}
