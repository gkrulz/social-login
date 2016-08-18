package com.gk.springtest.controller;

import com.gk.springtest.beans.Profile;
import com.gk.springtest.util.ResponsesGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Padmaka on 7/18/16.
 */

@Controller
@EnableWebMvc
@Api(value = "/" , description = "Operations that can be triggered on cake Wifi")
public class Responder {
    private static final Logger LOGGER = Logger.getLogger(Responder.class);
    private static HashMap<String, Timestamp> clients;
    private static Profile profile;

    static {
        clients = new HashMap<>();
        profile = new Profile();
    }

    @ApiOperation(
            value = "The endpoint to send cutomer info",
            notes = "The endpoint will save the cutomer details captured while the Facebook WiFi login")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Success"
            ),
            @ApiResponse(
                    code = 401,
                    message = "Unauthorized"
            ),
            @ApiResponse(
                    code = 403,
                    message = "Forbidden"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Not found"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Invalid parameter provided for Profile </br>" +
                            "Unable to find account"
            )
    })
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ResponseEntity<String> setProfile(@RequestBody Profile data) {

        profile = data;
        LOGGER.info(data.toString());

        return new ResponseEntity<String>(data.toString(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(ModelMap map) {

        map.addAttribute("data", profile);
        return "profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> loginPing(@RequestBody String msg) {

        LOGGER.info("login POST - " + msg);
        return new ResponseEntity<String>(msg, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<String> getClients(){

        LOGGER.info(clients.toString());

        return new ResponseEntity<String>(clients.toString(), HttpStatus.CREATED);
    }

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
        } else if (res.equals("failed")){
            return "error";
        } else if (res.equals("logout")) {
            return "error";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAuth(ModelMap map, @RequestParam("type") String type, @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password, @RequestParam("mac") String mac,
                          @RequestParam(value = "node", required = false) String node, @RequestParam("ra") String ra,
                          @RequestParam(value = "session", required = false) String session, @RequestParam(value = "seconds" ,required = false) String seconds)
            throws UnsupportedEncodingException {

        String msg = "";
        String secret = "cake1234";
        String code = "";

        if ("login".equals(type)){

            if (username != null && username.equals("toxic")) {

                code = "ACCEPT";
                msg = ResponsesGenerator.generateAuthResponse(code, ra, secret);
                Date date = new Date();
                clients.put(mac, new Timestamp(date.getTime()));

            } else {

                code = "REJECT";
                msg = ResponsesGenerator.generateAuthResponse(code, ra, secret);

            }

        } else if ("status".equals(type)) {

            if (clients.get(mac) == null) {

                code = "REJECT";
                msg = ResponsesGenerator.generateAuthResponse(code, ra, secret);

            } else {

                code = "ACCEPT";
                msg = ResponsesGenerator.generateAuthResponse(code, ra, secret);

            }

        } else if ("acct".equals(type)){

            code = "OK";
            msg = ResponsesGenerator.generateAuthResponse(code, ra, secret);

        } else if ("logout".equals(type)) {

            code = "REJECT";
            msg = ResponsesGenerator.generateAuthResponse(code, ra, secret);
            mac = java.net.URLDecoder.decode(mac, "UTF-8");
            mac = mac.split(",")[0].trim();
            LOGGER.info("Removed client - " + mac);
            clients.remove(mac);

        }

        LOGGER.info("old ra - " + ra);
        LOGGER.info("Auth message - " + msg);

        return new ResponseEntity<String>(msg, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authPing(@RequestBody String msg) throws UnsupportedEncodingException {

        LOGGER.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
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
