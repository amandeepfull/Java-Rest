package com.commons.services;


import com.commons.DaoImplServices.TokenDaoImpl;
import com.commons.Enum.ReservedClaims;
import com.commons.constants.CommonConstants;
import com.commons.entity.Token;
import com.commons.utils.HashUtil;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import java.util.List;

@Slf4j
public class JWTService {


    public Token createUserAccessToken(String clientId, String userName, List<String> scopes, float mins) {

        Preconditions.checkArgument(ObjUtil.isBlank(clientId), "clientId cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isBlank(userName), "unique id for user cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isNullOrEmpty(scopes), "scopes cannot be null/empty");

        Token token = new Token();

        try {

            final JwtClaims claims = new JwtClaims();


            claims.setIssuer(CommonConstants.AUTH_API_KEY);
            claims.setExpirationTimeMinutesInTheFuture(mins);
            claims.setSubject(userName);
            claims.setClaim(ReservedClaims.ISSUED_TO.toString(), clientId);
            claims.setClaim(ReservedClaims.SCOPES.toString(), scopes);

            claims.setIssuedAtToNow();

            // Generate the payload
            final JsonWebSignature jws = new JsonWebSignature();
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
            jws.setHeader("typ", "JWT");

            String claimJson = claims.toJson();
            jws.setPayload(claimJson);

            // Sign using the private key
            String privateKey = HashUtil.sha256(claimJson + CommonConstants.SIGN_SECRET_KEY);
            HmacKey key = new HmacKey(HashUtil.sha256(privateKey).getBytes());
            jws.setKey(key);


            token.setPrivateKey(privateKey);
            token.setAccessToken(jws.getCompactSerialization());
            return token;
        } catch (JoseException e) {
            log.error("exception while creating access token : ", e.getMessage(), e);
            return null;
        }
    }



    public String createAuthToken(String clientId, String userName, float mins) {


        Preconditions.checkArgument(ObjUtil.isBlank(clientId), "clientId cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isBlank(userName), "unique id for user cannot be null/empty");

        try {

            final JwtClaims claims = new JwtClaims();


            claims.setIssuer(CommonConstants.AUTH_API_KEY);
            claims.setExpirationTimeMinutesInTheFuture(mins);
            claims.setSubject(userName);
            claims.setClaim(ReservedClaims.ISSUED_TO.toString(), clientId);

            claims.setIssuedAtToNow();

            // Generate the payload
            final JsonWebSignature jws = new JsonWebSignature();
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
            jws.setHeader("typ", "JWT");

            jws.setPayload(claims.toJson());

            HmacKey key = new HmacKey(CommonConstants.SIGN_SECRET_KEY.getBytes());
            jws.setKey(key);


            return jws.getCompactSerialization();
        } catch (JoseException e) {
            log.error("exception while creating access token : ", e.getMessage(), e);
            return null;
        }
    }

    private static class JWTServiceInitializer {
        private static final JWTService jwtService = new JWTService();
    }

    public static JWTService getInstance() {
        return JWTServiceInitializer.jwtService;
    }


    public JwtClaims decodeAccessToken(String token, String privateKey) {


        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setExpectedIssuer(CommonConstants.AUTH_API_KEY)
                .setVerificationKey(new HmacKey(HashUtil.sha256(privateKey).getBytes()))
                .setJwsAlgorithmConstraints(
                        new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.WHITELIST,
                                AlgorithmIdentifiers.HMAC_SHA256))
                .build();

        try {
            return jwtConsumer.processToClaims(token);

        } catch (InvalidJwtException e) {
            log.error("exception while getting claims from jwt:", e.getMessage(), e);
            return null;
        }
    }

    public JwtClaims decodeAuthToken(String token) {

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setExpectedIssuer(CommonConstants.AUTH_API_KEY)
                .setVerificationKey(new HmacKey(CommonConstants.SIGN_SECRET_KEY.getBytes()))
                .setJwsAlgorithmConstraints(
                        new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.WHITELIST,
                                AlgorithmIdentifiers.HMAC_SHA256))
                .build();

        try {
            return jwtConsumer.processToClaims(token);

        } catch (InvalidJwtException e) {
            log.error("exception while getting claims from jwt:", e.getMessage(), e);
            return null;
        }
    }

}
