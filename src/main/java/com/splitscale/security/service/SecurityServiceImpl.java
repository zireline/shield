package com.splitscale.security.service;

import com.splitscale.security.encoder.Encodable;
import com.splitscale.security.encoder.Encoder;

public class SecurityServiceImpl implements SecurityService {

  @Override
  public String encode(String input) {
    Encodable encoder = new Encoder();

    return encoder.encode(input);
  }

  @Override
  public boolean compare(String input, String encryptedInput) {
    return encode(input).equals(encryptedInput);
  }

}
