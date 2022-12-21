
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
       
        <!--Form for add user-->
        <h2>Add a New User</h2>
        <form action="Twitter" method="post">

            <label>User Name</label>

            <input type="text" name="username"/>
            <br>
            <br>
            <label>Password</label>
          
            <input type="password" name="password"/>
            <br>
            <input type="hidden" name="action" value="createUser"/>
                    <br>
            <input type="submit" value="Add User"/>

        </form>

        <!--Form for updating a user-->
        <h2>Update User Properties</h2>
        <form action="Twitter" method="post">
            <label>ID</label>
            <input type="text" name="id"/><br>
            <br>
            <label>Username</label>
            <input type="text" name="username"/><br>
            <br>
            <label>Password</label>
            <input type="password" name="password"/><br>
             <br>
            <input type="hidden" name="action" value="updateUser"/>

            <input type="submit" value="Update User"/>

        </form>

        <!--Form for delete user-->
        <h2>Delete a User Record</h2>
        <form action="Twitter" method="post">

            <label>ID</label>
            <input type="text" name="id"/><br>        
            
            <input type="hidden" name="action" value="deleteUser"/>
             <br>
            <input type="submit" value="Delete User"/>

        </form>

    </body>
</html>
