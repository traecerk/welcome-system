package com.tracer.welcomesystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * JWT验证过滤器：配置顺序 CorsFilte->JwtUtilsr-->StrutsPrepareAndExecuteFilter
 *
 */
public class JwtUtils {
    /**
     * JWT_WEB_TTL：WEBAPP应用中token的有效时间,默认30分钟
     */
    public static final long JWT_WEB_TTL = 30 * 60 * 1000;
    /**
     * 将jwt令牌保存到header中的key
     */
    public static final String JWT_HEADER_KEY = "jwt";
    // 指定签名的时候使用的签名算法，也就是header那部分，jwt已经将这部分内容封装好了。
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final String JWT_SECRET = "f356cdce935c42328ad2001d7e9552a3";// JWT密匙
    private static final SecretKey JWT_KEY;// 使用JWT密匙生成的加密key
    static {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        JWT_KEY = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
    private JwtUtils() {
    }
    /**
     * 解密jwt，获得所有声明(包括标准和私有声明)
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_KEY)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
    /**
     * 创建JWT令牌，签发时间为当前时间
     *
     * @param claims
     *            创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
     * @param ttlMillis
     *            JWT的有效时间(单位毫秒)，当前时间+有效时间=过期时间
     * @return jwt令牌
     */
    public static String createJwt(Map<String, Object> claims,
                                   long ttlMillis) {
        // 生成JWT的时间，即签发时间 2021-10-30 10:02:00 -> 30 10:32:00

        long nowMillis = System.currentTimeMillis();

        //链式语法：
        // 下面就是在为payload添加各种标准声明和私有声明了
        // 这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                // 可以在未登陆前作为身份标识使用
                .setId(UUID.randomUUID().toString().replace("-", ""))
                // iss(Issuser)签发者，写死
                .setIssuer("zking")
                // iat: jwt的签发时间
                .setIssuedAt(new Date(nowMillis))
                // 代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可放数据{"uid":"zs"}。此处没放
                // .setSubject("{}")
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(SIGNATURE_ALGORITHM, JWT_KEY)
                // 设置JWT的过期时间
                .setExpiration(new Date(nowMillis + ttlMillis));
        return builder.compact();
    }
    /**
     * 复制jwt，并重新设置签发时间(为当前时间)和失效时间
     *
     * @param jwt
     *            被复制的jwt令牌
     * @param ttlMillis
     *            jwt的有效时间(单位毫秒)，当前时间+有效时间=过期时间
     * @return
     */
    public static String copyJwt(String jwt, Long ttlMillis) {
        //解密JWT，获取所有的声明（私有和标准）
        //old
        Claims claims = parseJwt(jwt);
        // 生成JWT的时间，即签发时间
        long nowMillis = System.currentTimeMillis();
        // 下面就是在为payload添加各种标准声明和私有声明了
        // 这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                // 可以在未登陆前作为身份标识使用
                //.setId(UUID.randomUUID().toString().replace("-", ""))
                // iss(Issuser)签发者，写死
                // .setIssuer("zking")
                // iat: jwt的签发时间
                .setIssuedAt(new Date(nowMillis))
                // 代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可放数据{"uid":"zs"}。此处没放
                // .setSubject("{}")
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(SIGNATURE_ALGORITHM, JWT_KEY)
                // 设置JWT的过期时间
                .setExpiration(new Date(nowMillis + ttlMillis));
        return builder.compact();
    }
}