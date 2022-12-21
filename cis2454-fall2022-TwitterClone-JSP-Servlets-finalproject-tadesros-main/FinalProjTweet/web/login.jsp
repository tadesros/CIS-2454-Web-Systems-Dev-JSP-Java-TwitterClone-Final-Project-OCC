<%-- 
    Document   : login
    Created on : Dec 6, 2022, 8:56:06 PM
    Author     : TomD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter Login Page</title>
        
        
        
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
        
      
            form {
                /* Center the form on the page */
             /*    margin: 0 auto; */
                width: 400px;
                /* Form outline */
                padding: 1em;
                border: 1px solid #CCC;
                border-radius: 1em;
        }
      
      
      
      
        
        </style>
        
        
        
        
    </head>
    <body>      
        
        <br>
        
        <h1>Login or Create a User</h1>
        
    
        
         <!--Form for Login-->
        <h2>Login</h2>
        <form action="loginCtrl" method="post">

            <label>User Name</label>

            <input type="text" name="username"/><br>
            <br>

            <label>Password</label>
            <input type="password" name="password"/><br>
            <input type="hidden" name="action" value="login"/>
            <br>
            <input type="submit" value="Login"/>

        </form>
        
        
        <!--Register Form-->
        <h2>Create / Register a new user account</h2>
        <form action="loginCtrl" method="post">

            <label>User Name</label>
 
            <input type="text" name="username"/><br>
            <br>

            <label>Password</label>
            <input type="password" name="password"/><br>
            <input type="hidden" name="action" value="register"/>
            <br>
            <input type="submit" value="Create New Account"/>

        </form>
                
    </body>
</html>
