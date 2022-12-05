package com.splitscale.security.service;

public interface SecurityService {
  String encode(String input);

  boolean compare(String input, String encryptedInput);
}
