package com.example.tiki_java.entity;

import lombok.*;

import java.util.Base64;
import java.time.Duration;

import com.nimbusds.jose.JWSAlgorithm;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "app.jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTProperty {
    byte[] key;
    JWSAlgorithm algorithm;
    String issuer;
    Duration expiresIn;

    public void setKey(String key) {
        this.key = Base64.getDecoder().decode(key);
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = JWSAlgorithm.parse(algorithm);
    }

    public String getStringAlgorithm() {
        return algorithm.toString();
    }
}
