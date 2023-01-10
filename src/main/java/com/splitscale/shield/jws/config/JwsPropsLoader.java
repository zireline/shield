package com.splitscale.shield.jws.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JwsPropsLoader {
  private static String path = "./jws.properties";

  private JwsPropsLoader() {
    // default
  }

  public static JwsProps loadProps() {
    Properties props = new Properties();
    InputStream fileStream;

    try {
      fileStream = new FileInputStream(path);
      props.load(fileStream);
    } catch (FileNotFoundException e) {
      System.err.println("Missing: " + "jwt.issuer");
      System.err.println("Missing: " + "jwt.signingKey");
      System.err.println("Missing: " + "jwt.audience");

      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new JwsProps(props);
  }

}
