package com.splitscale.shield.jws.config;

import java.util.Properties;

import javax.crypto.SecretKey;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwsProps {
  Properties properties;

  public JwsProps(Properties properties) {
    this.properties = properties;
  }

  public String getIssuer() {
    return properties.getProperty("jwt.issuer");
  }

  public SecretKey getSigningKey() {
    String secretString = properties.getProperty("jwt.signingKey");

    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
  }

  public String getAudience() {
    return properties.getProperty("jwt.audience");
  }
}
