package com.gk.springtest.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.UnsupportedEncodingException;

/**
 * Created by Padmaka on 7/18/16.
 */

@Controller
@EnableWebMvc
public class Responder {
    private static final Logger LOGGER = Logger.getLogger(Responder.class);

    @RequestMapping(value = "/fbnotify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getNLog(@RequestBody String msg) throws UnsupportedEncodingException {

        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/fbnotify", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ping(@RequestParam(value = "hub.challenge", defaultValue = "fbnotify web service test ping")
                                                   String challenge) throws UnsupportedEncodingException {


        LOGGER.info("challenge - " + challenge);

        return new ResponseEntity<>(challenge, HttpStatus.CREATED);
    }
}
