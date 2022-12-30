package com.splitscale.shield.auth;

import java.io.IOException;

import com.splitscale.fordastore.core.auth.AuthPublicKey;
import com.splitscale.fordastore.core.auth.Authorization;
import com.splitscale.fordastore.core.repositories.AuthRepository;
import com.splitscale.fordastore.core.user.User;

public class AuthPublicKeyInteractor {
  static AuthRepository repository;

  public AuthPublicKeyInteractor(AuthRepository repository) {
    this.repository = repository;
  }

  public String generateJwtFromUser(User user) throws IOException {
    Authorization auth = Authorizer.getAuthorization(user);

    // converted to base 64 for storage purposes
    String base64PublicKey = PublicKeyConverter.publicKeyToBase64(auth.getPublicKey());

    AuthPublicKey publicKey = new AuthPublicKey(user.getUid(), base64PublicKey);

    repository.insert(publicKey);

    return auth.getJwt();
  }

  public String getPublicKeyByUID(String uid) throws IOException {
    return repository.getByUid(uid).getPublicKey();
  }

  public void deletePublicKeyByUID(String uid) throws IOException {
    repository.deleteByUid(uid);
  }
}
