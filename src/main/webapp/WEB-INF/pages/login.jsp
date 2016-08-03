<%--
  Created by IntelliJ IDEA.
  User: padmaka
  Date: 7/25/16
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Facebook WiFi Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

    <!-- jQuery library -->
    <script src="<c:url value="/resources/js/jquery-3.1.0.min.js"/>"></script>

    <!-- Latest compiled JavaScript -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

</head>
<body>
<script>
    // This is called with the results from from FB.getLoginStatus().
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else if (response.status === 'not_authorized') {
            // The person is logged into Facebook, but not your app.
            document.getElementById('status').innerHTML = 'Please log ' +
                    'into this app.';
        } else {
            // The person is not logged into Facebook, so we're not sure if
            // they are logged into this app or not.
            document.getElementById('status').innerHTML = 'Please log ' +
                    'in to Facebook.';
        }
    }

    // This function is called when someone finishes with the Login
    // Button.  See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }

    window.fbAsyncInit = function() {
        FB.init({
            appId      : '1776655492571621',
            cookie     : true,  // enable cookies to allow the server to access
                                // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.7' // use graph api version 2.5
        });

        // Now that we've initialized the JavaScript SDK, we call
        // FB.getLoginStatus().  This function gets the state of the
        // person visiting this page and can return one of three states to
        // the callback you provide.  They can be:
        //
        // 1. Logged into your app ('connected')
        // 2. Logged into Facebook, but not your app ('not_authorized')
        // 3. Not logged into Facebook and can't tell if they are logged into
        //    your app or not.
        //
        // These three cases are handled in the callback function.

        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });

    };

    // Load the SDK asynchronously
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    function postInfo(data) {

        $.ajax({
            url: "http://plat1-apis.leapset.com:8060/wifi/profile",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (result) {
                console.log("done");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.error("Failed to post");
            }
        });
    }

    // Here we run a very simple test of the Graph API after login is
    // successful.  See statusChangeCallback() for when this call is made.
    function testAPI() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me?fields=id,email,name,first_name,last_name,age_range,link,gender,locale,picture.type(large),timezone,updated_time,verified', function(response) {
            console.log('Successful login for: ' + response.name);
            //document.getElementById('status').innerHTML = JSON.stringify(response);
            console.log("${res}");
            postInfo(response);
            window.location = "http://${uamip}:${uamport}/logon?username=toxic&password=87BC4E314689b55d89B&ra=949689087314689b55d89b1980aeff3f";
        });
    }
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
<div class="container" style="padding: 10px">

    <div class="col-md-4"></div>
    <div class="col-md-4" style="background-color: lightgray; border-radius: 10px;">
        <img src="<c:url value="/resources/img/cake-logo.png"/>">
        <h2>Login with Facebook to get free WiFi</h2><br><br>

        <div id="status"></div>

        <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
        </fb:login-button>
        <br><br>
    </div>
    <div class="col-md-4"></div>

</div>

</body>
</html>
