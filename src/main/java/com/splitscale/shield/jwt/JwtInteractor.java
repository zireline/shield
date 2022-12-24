package com.splitscale.shield.jwt;

import java.io.IOException;

import com.splitscale.fordastore.core.auth.Authorization;
import com.splitscale.fordastore.core.auth.PublicKey;
import com.splitscale.fordastore.core.repositories.AuthRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.auth.Authorizer;

public class JwtInteractor {
  AuthRepository repository;

  public JwtInteractor(AuthRepository repository) {
    this.repository = repository;
  }

  public String generateJwtFromUser(User user) throws IOException {
    Authorization auth = Authorizer.getAuthorization(user);

    // converted to base 64 for storage purposes
    String base64PublicKey = Authorizer.publicKeyToBase64(auth.getPublicKey());

    PublicKey publicKey = new PublicKey(user.getUid(), base64PublicKey);

    repository.insert(publicKey);

    return auth.getJwt();
  }
}
