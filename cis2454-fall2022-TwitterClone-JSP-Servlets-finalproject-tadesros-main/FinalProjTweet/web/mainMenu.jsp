<%-- 
    Document   : mainMenu
    Created on : Dec 11, 2022, 7:05:32 PM
    Author     : TomD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Menu</title>
    </head>
    <body>
        <h1>Main Menu</h1>
        
         <h2>Welcome ${username}!</h2>
         
         <br>
         <a href="homePageCtrl">Home Page</a>
         <br>
         
         <br>
         <a href="createTweetCtrl">Create a Tweet</a>
         <br>

         <br>
         <a href="viewAUserTweetsCtrl">View User Tweets</a>
         <br>        
         
         <br>
         <a href="followUnfollowTweetsCtrl">Follow / Unfollow Users</a>
         <br>   
         
         <br>
         <a href="profileCtrl">Upload / Change Your Profile Photo</a>
         <br>
         
         
         <br>     
         <a href="Twitter">Manage Users</a>
         <br>     
         
         
    </body>
</html>
