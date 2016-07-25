package com.gk.springtest.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map, @RequestParam("res") String res, @RequestParam("uamip") String uamip,
                        @RequestParam("uamport") String uamport, @RequestParam("mac") String mac,
                        @RequestParam("called") String called, @RequestParam("ssid") String ssid,
                        @RequestParam("userurl") String userurl, @RequestParam("challenge") String challenge)
            throws UnsupportedEncodingException {

        LOGGER.info("Sending post to login.jsp. res - " + res);
        map.addAttribute("res", res);
        map.addAttribute("uamip", uamip);
        map.addAttribute("uamport", uamport);
        map.addAttribute("mac", map);
        map.addAttribute("called", called);
        map.addAttribute("ssid", ssid);
        map.addAttribute("userurl", userurl);
        map.addAttribute("challenge", challenge);

        if (res.equals("success")){
            return "redirect";
        } else if (res.equals("notyet")){
            return "login";
        } else {
            return "unknown";
        }
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAuth(@RequestParam("type") String type, @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password, @RequestParam("mac") String mac,
                          @RequestParam(value = "node", required = false) String node, @RequestParam("ra") String ra,
                                          @RequestParam(value = "session", required = false) String session)
            throws UnsupportedEncodingException {

        LOGGER.info("Sending post to login.jsp. type - " + type + " mac - " + mac);

        String msg = "\"CODE\" \"REJECT\"\n" +
                "\"RA\" \"" + ra + "\"\n" +
                "\"BLOCKED_MSG\" \"Invalid%20username%20or%20password\"";

        return new ResponseEntity<String>(msg, HttpStatus.CREATED);
    }

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
