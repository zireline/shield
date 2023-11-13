package com.splitscale.shield.endpoints.refresh;

import java.io.IOException;

import com.splitscale.shield.endpoints.login.Tokens;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.refreshtoken.RefreshTokenManager;
import java.util.concurrent.TimeUnit;

public class Refresh {
  private RefreshTokenManager refreshTokenProvider;
  private GetJwt accessTokenProvider;

  public Refresh(RefreshTokenManager refreshTokenProvider, GetJwt accessTokenProvider) {
    this.refreshTokenProvider = refreshTokenProvider;
    this.accessTokenProvider = accessTokenProvider;
  }

  public Tokens refresh(RefreshRequest request) throws IOException {
    boolean isValid = refreshTokenProvider.validateRefreshToken(request.getRefreshToken(), request.getUserId());

    if (!isValid) {
      throw new IllegalArgumentException("Refresh token is not valid");
    }

    String uid = request.getUserId();

    String accessToken = accessTokenProvider.get(uid);
    String refreshToken = refreshTokenProvider.generateRefreshToken(uid, 30, TimeUnit.DAYS);

    return new Tokens(accessToken, refreshToken);
  }
}
