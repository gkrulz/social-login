package com.gk.springtest.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

/**
 * Created by Padmaka on 7/26/16.
 */
public class RAGenerator {
    private static final Logger LOGGER = Logger.getLogger(RAGenerator.class);

    public static String generateRA(String code, String oldRa, String secret) throws NoSuchAlgorithmException {

        String newRa = "";

        byte[] decodedRA = null;

        try {
            decodedRA = Hex.decodeHex(oldRa.toCharArray());
        } catch (DecoderException e) {
            LOGGER.error("", e);
        }

        try {
            newRa = getHash(code + new String(decodedRA, "ISO-8859-15") + secret);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return newRa;
    }

    public static String getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("md5");
        md.update(password.getBytes("ISO-8859-15"));

        byte byteData[] = md.digest();

        //Convert "byteData" to hex String:
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
