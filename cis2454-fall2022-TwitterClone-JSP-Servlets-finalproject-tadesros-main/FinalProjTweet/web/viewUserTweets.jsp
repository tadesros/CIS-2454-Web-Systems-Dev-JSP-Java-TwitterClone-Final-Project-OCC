<%-- 
    Document   : viewUserTweets
    Created on : Dec 18, 2022, 2:03:41 PM
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
        <!--Top Navigation-->
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
        
        <!--Enter the User id to View Tweets-->
        <h2>Enter the user ID of the user you want to view tweets for</h2>
        <form action="viewAUserTweetsCtrl" method="post">
            <label>User Id</label>
            <input type="integer" name="user_id"/>
            <br>
            <br>
            <input type="hidden" name="action" value="listTweets"/>
                   <br>
            <input type="submit" value="Get Tweets"/>            
        </form>
        
        <!--List all of the user tweets-->
         <h2> Username Tweets</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>Text</th>                
                <th>Date</th> 
                <th>Like Count</th>
            </tr>
            <c:forEach var="tweet" items="${tweets}">
                <tr>
                    <td><c:out value="${tweet.id}"/></td>
                    <td><c:out value="${tweet.text}"/></td>
                    <td><c:out value="${tweet.timestamp}"/></td>
                    <td><c:out value="${tweet.like_count}"/></td>
                </tr>
            </c:forEach>            
        </table>      
    </body>
</html>
