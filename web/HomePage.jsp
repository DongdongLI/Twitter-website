<%-- 
    Document   : HomePage
    Created on : Feb 27, 2015, 11:32:30 AM
    Author     : lee
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="twi" uri="/WEB-INF/twitter.tld" %>
<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <%
            //System.out.println("in home");
            User user=(User)request.getAttribute("user");
            //System.out.println("user:"+user.toString());
            
        %>
        <h1>Hello ${user.firstName} !</h1>
        <p>Today is <twi:currentDate/>.</p>
        <form action="UserList" method="post">
            <textarea name="tweet" rows="4" cols="50">
                How are you write now?...
            </textarea>
            <input type="submit" value="submit" >
            <input type="hidden" name="action" value="new_tweet">
        </form>
        <h1>All tweets:</h1><br/>
        <table>
            <tr>
                <td>Tweet ID</td>
                <td>Tweet</td>
                <td>User ID</td>
                <td>Delete</td>
            </tr>
            <twi:tweets>
                <tr>
                    <td><%= pageContext.getAttribute("twi_id")%></td>
                    <td><%= pageContext.getAttribute("twi_tweet")%></td>
                    <td><%= pageContext.getAttribute("twi_userid")%></td>
                    <td>
                        <c:if test="${pageContext.getAttribute('twi_userid')==pageContext.getAttribute('userid')}">
                            <form action="UserList" method="post">
                                <input type="submit" value="delete"/>
                                <input type="hidden" name="action" value="tweet_delete">
                                <input type="hidden" name="tweet_code" value="${twi_id}">
                            </form>
                        </c:if>
                    </td>
                </tr>
                </twi:tweets>
        </table>
    </body>
</html>
