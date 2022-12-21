<%-- 
    Document   : viewUserTweet
    Created on : Dec 17, 2022, 4:47:52 PM
    Author     : TomD
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
            
            
             table {
        width: 100%;
        border-collapse: collapse;
      }
      table,
      th,
      td {
        border: 1px solid black;
      }
      thead {
        background-color: #1c87c9;
        color: #ffffff;
      }
      th {
        text-align: center;
        height: 50px;
      }
      tbody tr:nth-child(odd) {
        background: #ffffff;
      }
      tbody tr:nth-child(even) {
        background: #f4f4f4;
      }
                    
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
         <a href="mainMenuCtrl">Main Menu</a>
        
               
        <!--List all of the Users-->
        <h2>All Users</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>Username</th>                
                <th>Password Hash</th>           
            </tr>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.password}"/></td>
                </tr>
            </c:forEach>            
        </table>        
        
         <!--List the Users you follow-->
        <h2>Users ${username} is following</h2>
        <table>
            <tr>
                <th>Follow Record Id</th>
                <th>Id of User You are Following</th>                 
            </tr>

            <c:forEach var="follow" items="${follows}">
                <tr>
                    <td><c:out value="${follow.id}"/></td>
                    <td><c:out value="${follow.following_user_id}"/></td>
                </tr>
            </c:forEach>            
        </table>
        
        <!--Enter User id to follow-->
        <h2>Enter the user ID to follow</h2>
        <form action="followUnfollowTweetsCtrl" method="post">

            <label>User ID</label>

            <input type="integer" name="user_id"/>
            <br>
            <br>
            <input type="hidden" name="action" value="followUser"/>
            <br>
            <input type="submit" value="Follow User"/>    
        </form>
        
        <!--Enter the User id to Unfollow-->
        <h2>Enter record Id of The User/Record to unfollow</h2>
        <form action="followUnfollowTweetsCtrl" method="post">

            <label>Follow Record Id</label>

            <input type="integer" name="record_id"/>
            <br>
            <br>
            <input type="hidden" name="action" value="unfollowUser"/>          
            <br>
            <input type="submit" value="Unfollow User"/>    
        </form>
        
    </body>
</html>
