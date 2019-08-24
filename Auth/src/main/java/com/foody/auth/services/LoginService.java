package com.foody.auth.services;

import com.foody.auth.baseEndpoints.objectify.OfyService;
import com.foody.auth.entities.Login;
import com.foody.auth.entities.Token;
import sun.rmi.runtime.Log;

public class LoginService extends OfyService {

    public boolean login(Login login){

     Login dbLogin =  get(Login.class, login.getEmail());

     if(dbLogin == null || dbLogin.getPassword().equals(login.getPassword()))
     return false;

      return redirectToRedirectUri(JWTService.getInstance().createAuthToken(login.getEmail(), 1));


    }

    private boolean redirectToRedirectUri(String authToken) {

        // todo redirectUri can be ex: http://localhost/authcallback?authCode=authToken

        // this authcode can be used to retrive accesstoken,

        //
        return true;
    }
}
