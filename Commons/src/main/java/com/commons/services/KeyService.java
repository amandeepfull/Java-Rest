package com.commons.services;

import com.commons.entity.Key;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class KeyService {


    public Key generateKeys(String clientId, String appId){

        try {

            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");


            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();

            Base64.Encoder encoder = Base64.getEncoder();

            String jsonString = "";


            Writer out = new FileWriter("service_account_keys_app_"+appId + ".json");

            out.write("-----BEGIN RSA PRIVATE KEY-----\n");
            out.write(encoder.encodeToString(kp.getPrivate().getEncoded()));
            out.write("\n-----END RSA PRIVATE KEY-----\n");

            System.out.println(new String(kp.getPrivate().getEncoded()));
//            out
//            new GCSService().uploadFile()

        //  return   new Key(kp.getPrivate().g, kp.getPublic().getEncoded(), clientId, appId);
        }catch (Exception e){
            log.error("exception while generating keys : ", e.getMessage(), e);

            return null;
        }


        return null;

    }


    public static void main(String arg[]) throws NoSuchAlgorithmException {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");


        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        byte []  bytes = kp.getPrivate().getEncoded();
        String string = new java.lang.String(bytes, StandardCharsets.UTF_8);
        System.out.println(string);
       /// Base64.Encoder encoder = Base64.getEncoder();

        String jsonString = "";

    }


}
