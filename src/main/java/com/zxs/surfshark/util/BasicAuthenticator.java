package com.zxs.surfshark.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *配置代理授权
 *
 *@version2017年3月30日
 *@authorZeninte
 *@sinceJDK1.6
 *
 */
public class BasicAuthenticator extends Authenticator{
    String userName;
    String password;

    public BasicAuthenticator(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    /**
     *Called when password authorizationis needed.Subclasses should override the default implementation,which
     *returnsnull.
     *
     *@return The Password Authentication collected from the user,or null if none is provided.
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName,password.toCharArray());
    }

}