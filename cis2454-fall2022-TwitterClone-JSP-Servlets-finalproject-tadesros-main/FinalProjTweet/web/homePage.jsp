<%-- 
    Document   : homePage
    Created on : Dec 18, 2022, 6:21:32 PM
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
        <a href="mainMenuCtrl">Main Menu</a><!-- comment -->
        <br>
        
         <h2>Welcome ${username}!</h2>        
        
        <c:if test="${(filename != null)}">
            <img src="GetImage?username=${username}" width="240" height="300"/>
        </c:if>        
        
       <br>           
            
        <!--List all Tweets that I follow-->
         <h2> ${username} Followed Tweets</h2>
        <table>
            <tr>
                <th>Tweet Id</th>
                <th>User Created Tweet Id</th>
                <th>Text</th>                
                <th>Date</th> 
                <th>Like Count</th>
            </tr>

            <c:forEach var="tweet" items="${tweets}">
                <tr>
                    <td><c:out value="${tweet.id}"/></td>
                    <td><c:out value="${tweet.user_id}"/></td>
                    <td><c:out value="${tweet.text}"/></td>
                    <td><c:out value="${tweet.timestamp}"/></td>
                    <td><c:out value="${tweet.like_count}"/></td>
                </tr>
            </c:forEach>            
        </table>   
        
        
        <!--Form for liking a tweet-->
        
        <br>
     
        <form action="homePageCtrl" method="post">            
                        
            <label>Enter Tweet ID to Like:</label>
            <br>
            <input type="text" name="tweetId"/>
            <br>
            <input type="hidden" name="action" value="likeTweet"/>            
            <br>
            <input type="submit" value="Like Tweet"/>
        </form>               
        
    </body>
</html>
