


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Tweet</title>
        
        
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
 
         

        <!--Form for add Tweet-->
        <h2>Add a New Tweet</h2>
        <form action="createTweetCtrl" method="post">            
                        
            <label>Tweet Content:</label>
            <br>
            <input type="text" name="tweetText"/>
            <br>
            <input type="hidden" name="action" value="createTweet"/>            
            <br>
            <input type="submit" value="Add Tweet"/>

        </form>  
        
        
        <!--List all of my tweets-->
         <h2> ${username} Tweets</h2>
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
