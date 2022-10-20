package com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService {
    public static final String Token_Secrete="qwertykeypad";

    public String createToken(ObjectId userid)
    {
        try{
            //Here We said about token secrete  Random Generating String using with Token Secret
            //We are Using HMAC256 Algorithms to Generate the Token
            Algorithm algorithm=Algorithm.HMAC256(Token_Secrete);

            //We are Using Claims of UserId and Created a Date using Date Object.
            //
            String token= JWT.create().
                    withClaim("userId",userid.toString())
                    .withClaim("createdAt",new Date())
                    .sign(algorithm);
            return token;
        }
        catch(UnsupportedEncodingException exception)
        {
            exception.printStackTrace();
        }
        catch(JWTCreationException exception)
        {
            exception.printStackTrace();
        }
        return null;

    }

    public String getUserIdFromToken(String token)
    {
        try{
            //Here We said about token secrete  Random Generating String using with Token Secret
            //We are Using HMAC256 Algorithms to Generate the Token
            Algorithm algorithm=Algorithm.HMAC256(Token_Secrete);
            JWTVerifier jwtVerifier=JWT.require(algorithm).build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            return decodedJWT.getClaim("userId").asString();
        }
        catch(UnsupportedEncodingException exception)
        {
            exception.printStackTrace();
        }
        catch(JWTCreationException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public boolean isTokenValid(String token)
    {
        String userId=this.getUserIdFromToken(token);
        return (userId!=null);
    }


}
