package com.gk.springtest.util;

import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;

/**
 * Created by padmaka on 8/1/16.
 */
public class ResponsesGenerator {
    private static final Logger LOGGER = Logger.getLogger(ResponsesGenerator.class);

    public static String generateAuthResponse(String code, String ra, String secret) {

        String response = "";

        if ("ACCEPT".equals(code)) {

            String newRa = null;

            try {
                newRa = RAGenerator.generateRA(code, ra, secret);
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error("", e);
            }

            response = "\"CODE\" \"" + code + "\"\n" +
                    "\"RA\" \"" + newRa + "\"\n" +
                    "\"SECONDS\" \"300\"\n" +
                    "\"DOWNLOAD\" \"2000\"\n" +
                    "\"UPLOAD\" \"800\"";

        } else if ("REJECT".equals(code)) {

            String newRa = null;

            try {
                newRa = RAGenerator.generateRA(code, ra, secret);
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error("", e);
            }

            response = "\"CODE\" \"" + code + "\"\n" +
                    "\"RA\" \"" + newRa + "\"\n" +
                    "\"BLOCKED_MSG\" \"Invalid%20username%20or%20password\"";

        } else if ("OK".equals(code)) {

            String newRa = null;

            try {
                newRa = RAGenerator.generateRA(code, ra, secret);
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error("", e);
            }

            response = "\"CODE\" \"" + code + "\"\n" +
                    "\"RA\" \"" + newRa + "\"";

        }

        return response;
    }
}
