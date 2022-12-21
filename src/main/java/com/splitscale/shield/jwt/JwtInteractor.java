package com.splitscale.shield.jwt;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.Authorizer;
import com.splitscale.shield.auth.Authorization;
import com.splitscale.shield.auth.Token;
import com.splitscale.shield.repository.AuthRepository;

public class JwtInteractor {
  AuthRepository authRepo;
  Authorizer authorizer;

  public JwtInteractor(AuthRepository authRepo, Authorizer authorizer) {
    this.authRepo = authRepo;
    this.authorizer = authorizer;
  }

  public String generateJwtFromUser(User user) {
    Authorization auth = authorizer.getAuthorization(user);

    // converted to base 64 for storage purposes
    String base64PublicKey = authorizer.publicKeyToBase64(auth.getPublicKey());

    Token token = new Token(user.getUid(), base64PublicKey);

    authRepo.insert(token);

    return auth.getToken();
  }
}
