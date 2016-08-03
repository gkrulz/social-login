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
    <title>Customer Profile</title>
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

    function printData() {
        var data = ${data};

        $('.picture').html("<img src=" + data.picture.data.url + ">");
        $('.name').html(data.name);
        $('.email').html(data.email);
        $('.fid').html(data.id);
        $('.age').html(data.age_range.min);
        $('.link').html("<a target='_blank' href='" + data.link + "' >" + data.link + "</a>");
        $('.gender').html(data.gender);
        $('.locale').html(data.locale);
        $('.timezone').html(data.timezone);
        $('.utime').html(data.updated_time);
        $('.verified').html(data.verified);
    }

    $( document ).ready(function() {
        printData();
    });

</script>

<div class="container" style="padding: 10px">

    <div class="col-md-2"></div>
    <div class="col-md-8" style="background-color: lightgray; border-radius: 10px;">
        <img src="<c:url value="/resources/img/cake-logo.png"/>">
        <h2>Customer profile</h2><br><br>

        <div id="profile">
            <table class="table" style="table-layout: fixed; word-wrap: break-word;">
                <tbody>
                    <tr>
                        <td class="picture"></td>
                    </tr>
                    <tr>
                        <td>Name</td><td class="name data"></td>
                    </tr>
                    <tr>
                        <td>Email</td><td class="email data"></td>
                    </tr>
                    <tr>
                        <td>Facebook ID</td><td class="fid data"></td>
                    </tr>
                    <tr>
                        <td>Age range</td><td class="age data"></td>
                    </tr>
                    <tr>
                        <td>Link</td><td class="link data"></td>
                    </tr>
                    <tr>
                        <td>Gender</td><td class="gender data"></td>
                    </tr>
                    <tr>
                        <td>Locale</td><td class="locale data"></td>
                    </tr>
                    <tr>
                        <td>timezone</td><td class="timezone data"></td>
                    </tr>
                    <tr>
                        <td>Updated Time</td><td class="utime data"></td>
                    </tr>
                    <tr>
                        <td>Verified</td><td class="verified data"></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <br><br>
    </div>
    <div class="col-md-2"></div>

</div>

</body>
</html>