package org.starrism.mall.common.enums;

/**
 * <p>加密类型</p>
 *
 * @author hedwing
 * @since 2022/8/29
 **/
public enum SecretType {
    /**
     * Message Digest Algorithm
     */
    MD5,
    /**
     * SHA(Secure Hash Algorithm，安全散列算法)
     */
    SHA,
    /**
     *（Advanced Encryption Standard） 加密/解密
     */
    AES,
    /**
     * 非对称加密算法
     */
    RSA;

}
