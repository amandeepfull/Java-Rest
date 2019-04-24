package com.commons.services;


import com.commons.Enum.ReservedClaims;
import com.commons.constants.CommonConstants;
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

@Slf4j
public class JWTService {


    public String createToken(String clientId, String userId,  float mins) {

        try {

            final JwtClaims claims = new JwtClaims();


            claims.setIssuer(CommonConstants.AUTH_API_KEY);
            claims.setExpirationTimeMinutesInTheFuture(mins);
            claims.setSubject(clientId);
            claims.setClaim(ReservedClaims.USERID.toString(), userId);
            claims.setIssuedAtToNow();

            // Generate the payload
            final JsonWebSignature jws = new JsonWebSignature();
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
            jws.setHeader("typ", "JWT");

            jws.setPayload(claims.toJson());
            // Sign using the private key
            HmacKey key = new HmacKey(CommonConstants.SIGN_SECRET_KEY.getBytes());
            jws.setKey(key);


            return jws.getCompactSerialization();
        } catch (JoseException e) {
            log.error("exception while creating access token : ",e.getMessage(), e);
            return null;
        }
    }

    private static class JWTServiceInitializer{
        private static final JWTService jwtService = new JWTService();
    }

    public static JWTService getInstance(){
        return JWTServiceInitializer.jwtService;
    }



    public JwtClaims decodeToken(String token)  {

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

        }catch (InvalidJwtException e){
            log.error("exception while getting claims from jwt:",e.getMessage(), e);
            return null;
        }
    }

//    public static void main(String arg[]){
//
//
//        Contact contact = new Contact();
//        contact.setId("love");
//        contact.setClientId("nothing");
//
//       String authCode = new JWTService().createAuthCode( "sfdsfdsfdsf","clientId");
//        System.out.println(authCode);
//        System.out.println(new JWTService().decodeAuthCode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJmZmJjY2EwZi01ZWI1LTQ1ODUtYTYwMC0yZjgzZGMyNDNhYjAiLCJleHAiOjE1NTYxMDQ0OTUsInN1YiI6IjJmZGZiNTAwLTJjY2EtNGI3NC1hZDRkLWVmMWQxODBkOTI2YSIsInVzZXJJZCI6IjcyMmUyNzdkLTNjZTUtNDQxNS04YjA0LTJiYjJiZDYyMTliYyIsImlhdCI6MTU1NjEwNDQzNX0.CZXZ6sIWyIqd3uSpO3FdKEygO1VSNCRFjrN40ZFK7VI"));
//    }
}
