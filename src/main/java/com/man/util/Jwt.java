package com.man.util;

import com.man.common.enums.TokenState;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Jwt {
    // TODO 暂时写死
    // 角色key
    public static String roleKey  = "i9ow7er%^&a_w3e";
    // tokenkey
    public static String tokenKey =  "&sdrhyuwe3r";
    // 过期时间key
    public static String tokenExtKey = "rg82w73%^&m*#";
    // 生成时间key
    public static String tokenIatKey = "e%&^*wq2g4rg3";
    // 加密secret
    public static String secret = "j`ybtu#re7cv^uyerazsk_ef[aop(e7ry23n;kltjbhnvas@ilwqutnzl)rb&qw3p941v3wr65s78reg3waerioue%^$RGBNM<erg1e563>fR#EIV@W&*Eraw!#)&*Brtbn.klabswefg";
    /**
     * 认证主体
     */
    public static String subjectKey = "rg73%^&m*#*;gr;fffgfsasdf";
    // 固定的过期时间
    public static int tokenValidSecond = 144;
    private static final JWSHeader HEADER = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);
    /**
     * 生成token
     *
     * @param payload
     * @return
     * @Description: TODO
     * @author: buwanli
     * @date: 2019年1月5日 下午9:35:20
     * @version: V1.0
     * @return: String
     */
    public static String createToken(Map<String, Object> payload) {
        payload.put(tokenIatKey,System.currentTimeMillis());// token生成时间戳
        String tokenString = null;
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(HEADER, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(secret.getBytes()));
            tokenString = jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("签名失败");
            e.printStackTrace();
        }
        return tokenString;
    }
    /**
     * 校验token
     * @param jo
     * @return
     * @Description: TODO
     * @author: buwanli
     * @date: 2019年1月5日 下午9:35:20
     * @version: V1.0
     * @return: Map<String       ,       Object>
     */
    public static Map<String, Object> valid(JSONObject jo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("state", TokenState.INVALID);// 默认无效的token
        try {
            // 若payload包含规定的key
            if (jo.containsKey(tokenExtKey) && jo.containsKey(tokenKey) && jo.containsKey(roleKey) && jo.containsKey(tokenIatKey)) {
                long extTime = Long.valueOf(jo.get(tokenExtKey).toString());
                long curTime = System.currentTimeMillis();
                if (extTime  >= curTime ) {
                    resultMap.put("state", TokenState.VALID);
                }else{// 过期
                    resultMap.clear();
                    resultMap.put("state", TokenState.EXPIRED);
                }
            }
            resultMap.put("data", jo);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return resultMap;
    }

    public static JSONObject getJo(String token){
        try{
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(secret.getBytes());
            if (jwsObject.verify(verifier)) {
                return  payload.toJSONObject();
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return null;
    }
    /**
     * 失效时间规则
     */
    public static long extTime() {
    	return DateUtils.todayAddOf(1,3,0,0);// 明天三点过期
    }
}