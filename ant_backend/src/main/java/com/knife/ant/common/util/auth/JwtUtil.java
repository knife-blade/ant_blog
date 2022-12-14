package com.knife.ant.common.util.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.knife.ant.common.util.ApplicationContextHolder;
import com.knife.ant.config.properties.JwtProperty;

import java.util.Date;

public class JwtUtil {
    private static final JwtProperty JWT_PROPERTY;

    static {
        JWT_PROPERTY = ApplicationContextHolder.getContext().getBean(JwtProperty.class);
    }

    // 创建jwt token
    public static String createToken(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + JWT_PROPERTY.getExpire() * 1000);
            Algorithm algorithm = Algorithm.HMAC512(JWT_PROPERTY.getSecret());
            return JWT.create()
                    // 自定义私有的payload的key-value。比如：.withClaim("userName", "Tony")
                    // .withClaim("key1", "value1")
                    .withAudience(userId)  // 将 user id 保存到 token 里面
                    .withExpiresAt(date)   // date之后，token过期
                    .sign(algorithm);      // token 的密钥
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验token
     * 若校验失败，会抛出异常：{@link JWTVerificationException}
     * 失败情况（按先后顺序）：
     * - 算法不匹配：{@link com.auth0.jwt.exceptions.AlgorithmMismatchException}
     * - 签名验证失败：{@link com.auth0.jwt.exceptions.SignatureVerificationException}
     * - Claim无效：{@link com.auth0.jwt.exceptions.InvalidClaimException}
     * - token超期：{@link com.auth0.jwt.exceptions.TokenExpiredException}
     */
    public static void verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(JWT_PROPERTY.getSecret());

        JWTVerifier jwtVerifier = JWT.require(algorithm)
                // .withIssuer("auth0")
                // .withClaim("userName", userName)
                .build();

        DecodedJWT jwt = jwtVerifier.verify(token);
    }

    public static String getUserIdByToken(String token) {
        try {
            return JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static boolean isTokenExpired(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().before(new Date());
    }

}
