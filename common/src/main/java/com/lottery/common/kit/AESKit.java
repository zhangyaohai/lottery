package com.lottery.common.kit;

import com.lottery.common.exception.EncryptionException;
import com.lottery.common.exception.InvalidTokenException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AESKit {
	
    private static final Logger logger = LoggerFactory.getLogger(AESKit.class);
    public static final long DEFAULT_TOKEN_EXPIRE_TIME = 86400000L * 7;// 7 days
    public static final long THIRTY_MINUTES_TOKEN_EXPIRE = 1800000L;// 30 min
    public static final long TOKEN_4_HOUR_EXPIRE = 14400000L;// 4 hours
    public static final long TOKEN_40_DAYS_EXPIRE_TIME = 86400000L * 40;// 7 days
    public static final long TOKEN_30_DAYS_EXPIRE_TIME = 86400000L * 30;// 7 days

    private AES aes;

    public AESKit(String iv, String key) {
        aes = new AES(iv, key);
    }
    
    public static AESKit me(){
        String env = System.getenv("spring.profiles.active");
        if (StringUtils.isBlank(env)) {
            env = System.getProperty("spring.profiles.active");
        }
        if (StringUtils.isBlank(env) || "prod".equals(env)) {
            return new AESKit("hdgsftahtgfjdhgr","ikjhdbsncgtrhdjg");
        } else {
            return new AESKit("hdgsftahtgfjddev","ikjhdbsncgtrhdev");
        }
    }
    public static AESKit invite() {
        return new AESKit("uYEtvixf52jn2JPt", "2vBwmkpLHxVQkjfl");
    }

    public static AESKit orderRedpack() {
        return new AESKit("uYEtPixr5ejndJPt", "2vBwmkpLaxVcketl");
    }

    public String encrypt(String plainText) throws EncryptionException {
        try {
            return Base64.encodeBase64String(aes.encrypt(plainText));
        } catch (EncryptionException e) {
            logger.warn("error encrypt plainText:{}", e.getMessage());
            throw e;
        }
    }

    public String decrypt(String cipherText) throws EncryptionException {
        try {
            return new String(aes.decrypt(Base64.decodeBase64(cipherText)));
        } catch (EncryptionException e) {
            logger.warn("error decrypt cipherText:{}" + cipherText);
            throw e;
        }
    }

    public String generateToken(Object input, long expireTime) {
        return encrypt(input + "_" + (System.currentTimeMillis() + expireTime));
    }

    public String generateToken(Object input) {
        return generateToken(input, DEFAULT_TOKEN_EXPIRE_TIME);
    }

    public String parseToken(String token) throws InvalidTokenException {
        if (token == null) {
            throw new InvalidTokenException(token);
        }
        String decrypted;
        try {
            decrypted = decrypt(token);
        } catch (EncryptionException e) {
            throw new InvalidTokenException(token);
        }
        String[] pair = decrypted.split("_");
        if (pair.length != 2) {
            throw new InvalidTokenException(token);
        }
        try {
            Pair<String, Long> result = Pair.of(pair[0], Long.parseLong(pair[1]));
            if (result.getRight() < System.currentTimeMillis()) {
                throw new TokenExpiredException();
            }
            return result.getLeft();
        } catch (NumberFormatException e) {
            throw new InvalidTokenException(token);
        }

    }
    
    public String parseTokenIgnoreTime(String token) throws InvalidTokenException {
        if (token == null) {
            throw new InvalidTokenException(token);
        }
        String decrypted;
        try {
            decrypted = decrypt(token);
        } catch (EncryptionException e) {
            throw new InvalidTokenException(token);
        }
        String[] pair = decrypted.split("_");
        if (pair.length != 2) {
            throw new InvalidTokenException(token);
        }
        try {
            Pair<String, Long> result = Pair.of(pair[0], Long.parseLong(pair[1]));
            return result.getLeft();
        } catch (NumberFormatException e) {
            throw new InvalidTokenException(token);
        }
    }

    public static final void main(String[] args) throws InterruptedException {
        System.out.println(new AESKit("hdgsftahtgfjddev","ikjhdbsncgtrhdev").generateToken("sfsdfsd"));
    }

}
