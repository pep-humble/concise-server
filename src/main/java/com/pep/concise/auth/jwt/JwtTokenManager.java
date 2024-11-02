package com.pep.concise.auth.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.pep.concise.auth.token.TokenManager;
import com.pep.concise.auth.token.TokenResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * JWT形式令牌生成器
 *
 * @author gang.liu
 */
@Slf4j
@AllArgsConstructor
public class JwtTokenManager implements TokenManager {

    /**
     * 权限
     */
    private static final String AUTHORITIES = "authorities";

    /**
     * 当前应用
     */
    private final String issuer;

    /**
     * jwt形式令牌配置
     */
    private final JwtTokenProperty tokenProperty;

    @Override
    public TokenResult generator(Authentication authentication) throws JOSEException {
        // 唯一id,可用来做退出黑名单,这里将访问令牌和刷新令牌的jwtID保持一致,便于刷新令牌时,将访问令牌加入黑名单
        String jwtId = UUID.randomUUID().toString();
        return new TokenResult()
                .setAccessToken(createJwtValue(authentication, tokenProperty.getAccessTokenTtl(), jwtId))
                .setRefreshToken(createJwtValue(authentication, tokenProperty.getRefreshTokenTtl(), jwtId));
    }

    /**
     * 创建令牌
     *
     * @param authentication 认证结果
     * @param ttl            存活时间
     * @param jwtId          jwt唯一编号
     * @return 令牌
     * @throws JOSEException 可能抛出的异常
     */
    protected String createJwtValue(Authentication authentication, String ttl, String jwtId) throws JOSEException {
        // 创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                // 颁发者
                .issuer(issuer)
                // 颁发时间
                .issueTime(new Date())
                // 主角
                .subject(authentication.getName())
                // 接收者
                .audience(issuer)
                // 过期时间
                .expirationTime(Date.from(Instant.now().plus(DurationStyle.detectAndParse(ttl))))
                // 生效时间
                .notBeforeTime(new Date())
                // 唯一id,可用来做退出黑名单
                .jwtID(jwtId)
                // 自定义扩展
                .claim(AUTHORITIES, Arrays.asList("read", "ROLE_write"))
                .build();
        // 创建JWS对象
        JWSObject jwsObject = new SignedJWT(jwsHeader, claimsSet);
        // 创建HMAC签名器
        JWSSigner jwsSigner = new MACSigner(tokenProperty.getSignature());
        // 签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    @Override
    public Authentication parse(String token) {
        try {
            // 从token中解析JWS对象
            JWSObject jwsObject = JWSObject.parse(token);
            // 创建HMAC验证器
            JWSVerifier jwsVerifier = new MACVerifier(tokenProperty.getSignature());
            // 合法结果,防止被破坏
            boolean verify = jwsObject.verify(jwsVerifier);
            if (BooleanUtils.isFalse(verify)) {
                // 畸形的jwt令牌
                return null;
            }
            Payload payload = jwsObject.getPayload();
            JWTClaimsSet parse = JWTClaimsSet.parse(payload.toJSONObject());
            // 判断时间
            Date expirationTime = parse.getExpirationTime();

            String[] authorities = parse.getStringArrayClaim(AUTHORITIES);
            return new UsernamePasswordAuthenticationToken(
                    parse.getSubject(),
                    "N/A",
                    AuthorityUtils.createAuthorityList(authorities)
            );
        } catch (ParseException | JOSEException e) {
            log.error("", e);
            return null;
        }

    }

    @Override
    public TokenResult refresh(String refreshToken) {
        return null;
    }

    @Override
    public void revoke(String accessToken) {

    }
}
