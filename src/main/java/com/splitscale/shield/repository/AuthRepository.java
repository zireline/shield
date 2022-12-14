package com.splitscale.shield.repository;

import java.security.PublicKey;

import com.splitscale.shield.auth.Token;

public interface AuthRepository {
  public PublicKey getByID(String id);

  public boolean insert(Token token);

  public boolean update(Token token);

  public boolean deleteByID(Long id);

}
