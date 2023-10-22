package com.learnspringboot.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "DgEkODh1zwY7zOkebPNku2SdI/aSNx+IDws2D5jBKNkUJHPCaOI2VER1A8Srf/6nBhqk0EAsQAz74v6kmBvp7DRGTlHdTLinRMNB0s86klA87MyNenLt6EbG+6aKuWi6dUI/VDMBZOl0dn9wXUzuab+uGvf8cdNPPPKtW6LmFPTPA7Wui2Wts57e2ZINSGnscKs2O8zbFdJLnBXUz091g1T06GmQmtuVTjYDumgE4h7loFBiHo2PsYu6WuLZh5ZYwn5BRRu07H70Arh+SKjatk3buWW1px9rAE0uBPkuHAn0JsIUI8Jdq9mir4oSYmjFCJURgVobSsGmHKFhvPSIoJODluNhlr/+/02SBnziWPk=\r\n"
            + //
            "";

    public String extractUsername(String token) {
        // Subject is username of my user
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Extract claim from my token
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // If you want to extract token just userDetails
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Implement method that will help to generate a token
    // token is a string, setIssuedAt so when this claim was created and this
    // information will help us to calculate the expiration date or to check if the
    // token is still valid or not, you can setExpiration date
    // In this case token will expired in 24 hours plus 1000 miliseconds
    // Compact is the one that will generate and return the token
    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // This method to validate the token, if the token belongs to userDetails
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract all the claims and also another method that allow us
    // to extract one single claim

    // setSigningKey is used to create the signature part of the
    // JWT which is used to verify that the sender of the JWT is
    // who it claims to be and ensure that the message wasn't changed
    // along the way, so we want ensure that the same person or the same client that
    // is sending this JWT key is the one that claims who to be

    // The signing key is used in conjunction with the sign-in algorithm specified
    // in JWT header to create the signature the specific sign-in algorithm and key
    // size will depend on the security requirement of your application and the
    // level of trust you have in the signing party
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}