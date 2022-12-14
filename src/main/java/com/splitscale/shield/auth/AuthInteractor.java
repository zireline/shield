package com.splitscale.shield.auth;

import com.splitscale.shield.repository.AuthRepository;

public class AuthInteractor {
  AuthRepository repository;

  /**
   * Should throw err if token exist
   * 
   * @param token
   * @return
   */
  public boolean insertToken(Token token) {
    return repository.insert(token);
  }

  public boolean updateToken(Token token) {
    return repository.update(token);
  }

  public boolean deleteToken(Long id) {
    return repository.deleteByID(id);
  }
}
