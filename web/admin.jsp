<%-- 
    Document   : admin
    Created on : Feb 27, 2015, 4:35:53 PM
    Author     : lee
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="twi" uri="/WEB-INF/twitter.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>All user here!(if any)</h1>
        
        
        <table>
            <tr>
                <td>User id</td>
                <td>User First Name</td>
                <td>User Last Name</td>
                <td>User email</td>
                <td>Delete user</td>
            </tr>
                
            <twi:users>
            <tr>
                <td><%= pageContext.getAttribute("userid") %></td>
                <td class="right"><%= pageContext.getAttribute("firstname") %></td>
                <td><%= pageContext.getAttribute("lastname") %></td>
                <td><%= pageContext.getAttribute("email") %></td>
                        <td><form action="UserList" method="post">
                                <input type="submit" value="delete">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="deletecode" value=${userid}>
                    </form></td>
            </tr>
            </twi:users>
        </table>
        <p><form action="login.html"><input type="submit" value="Log out"></form></p>
    </body>
</html>
