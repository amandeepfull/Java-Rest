package com.commons.Enum;

public enum  OauthCaterScopes {
    FULL_ACCESS("oauthcater.access.full"), READ_ACCESS("oauthcater.access.read"), WRITE_ACCESS("oauthcater.access.write"),
    MANAGE_ACCESS("oauthcater.access.manage");

    String scope;
     OauthCaterScopes(String scope){
        this.scope = scope;
    }

    public static OauthCaterScopes fromValue(String value) {

        if (value == null)
            return null;

        for (OauthCaterScopes type : OauthCaterScopes.values()) {

            if (type.scope.equalsIgnoreCase(value))
                return type;
        }

        return null;
    }

    @Override
    public String toString(){
         return this.scope;
    }
}
