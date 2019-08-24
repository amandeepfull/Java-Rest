package com.foody.auth.services;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import java.security.AlgorithmConstraints;
import java.util.List;


public class JWTService {


    public String createUserAccessToken(String userName, float mins) {


        try {

            final JwtClaims claims = new JwtClaims();


            claims.setIssuer("foody");
            claims.setExpirationTimeMinutesInTheFuture(mins);
            claims.setSubject(userName);
            claims.setIssuedAtToNow();

            // Generate the payload
            final JsonWebSignature jws = new JsonWebSignature();
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
            jws.setHeader("typ", "JWT");

            String claimJson = claims.toJson();
            jws.setPayload(claimJson);

            // Sign using the private key

            HmacKey key = new HmacKey("authkey".getBytes());
            jws.setKey(key);


            return jws.getCompactSerialization();

        } catch (JoseException e) {
            return null;
        }
    }



    public String createAuthToken(String userName, float mins) {


        try {

            final JwtClaims claims = new JwtClaims();


            claims.setIssuer("foody");
            claims.setExpirationTimeMinutesInTheFuture(mins);
            claims.setSubject(userName);
            claims.setIssuedAtToNow();

            // Generate the payload
            final JsonWebSignature jws = new JsonWebSignature();
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
            jws.setHeader("typ", "JWT");

            jws.setPayload(claims.toJson());

            HmacKey key = new HmacKey("some private key".getBytes());
            jws.setKey(key);


            return jws.getCompactSerialization();
        } catch (JoseException e) {
            return null;
        }
    }


    private static class JWTServiceInitializer {
        private static final JWTService jwtService = new JWTService();
    }

    public static JWTService getInstance() {
        return JWTServiceInitializer.jwtService;
    }


    public JwtClaims decodeToken(String token) {


        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setExpectedIssuer("authkey")
                .setVerificationKey(new HmacKey("some private key".getBytes()))

                .build();

        try {
            return jwtConsumer.processToClaims(token);

        } catch (InvalidJwtException e) {
            return null;
        }
    }



}