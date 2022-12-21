
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        
        <style>
            
        input[type=submit] {
              border: 1px solid #777;
            background: #6e9e2d;
            color: #fff;
            font: bold 11px 'Trebuchet MS';
            padding: 4px;
            cursor: pointer;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
      } 
            
            
            
        </style>
        
    </head>
    <body>
         <a href="mainMenuCtrl">Main Menu</a>
        
        
        <h2>Welcome ${username}</h2>
           
        <c:if test="${(filename != null)}">
            <img src="GetImage?username=${username}" width="240" height="300"/>
        </c:if>
        <h3>Click Choose File to Upload a Profile Photo</h3>
        <form action="Upload" method="post" enctype="multipart/form-data">
            <div id="data">
                <input type="file" accept="image/*" name="file">
            </div>
            
            <br>
            
            <div id="buttons">
                <label>&nbsp;</label>
                <input type="submit" value="Upload to your Profile"><br>
            </div>
            
            
        </form>
    </body>
</html>

