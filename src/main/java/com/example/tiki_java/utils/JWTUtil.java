package com.example.tiki_java.utils;

import com.example.tiki_java.entity.JWTProperty;
import com.example.tiki_java.entity.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JWTUtil {
    static Logger log = LoggerFactory.getLogger(JWTUtil.class);

    final JWTProperty jwtProperty;

    public String generateToken(User user) {
        try {
            // 1. Tạo payload với claims
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getId())
                    .claim("email", user.getEmail())
                    .issuer(jwtProperty.getIssuer())
                    .expirationTime(new Date(System.currentTimeMillis() + jwtProperty.getExpiresIn().toMillis()))
                    .build();

            // 2. Tạo header
            JWSHeader header = new JWSHeader(jwtProperty.getAlgorithm());

            // 3. Tạo SignedJWT
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            signedJWT.sign(new MACSigner(jwtProperty.getKey()));

            return signedJWT.serialize();

        } catch (JOSEException e) {
            log.error("Error generating JWT token", e);
            throw new RuntimeException("JWT creation failed", e);
        }
    }


}
