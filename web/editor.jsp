<%-- 
    Document   : editor
    Created on : Sep 30, 2016, 2:42:33 PM
    Author     : md
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Email Entry Editor</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
        <h1>Register User</h1>
    <body>
    <c:forEach var="guest" items="${guestList}">
        <p>${guest.id} ${guest.firstName} ${guest.lastName} ${guest.emailAddress}</p>
    </c:forEach>
        <form method="POST" action="EmailServletDB">
            <h1>Please Register Email</h1>
            <table>
                <tr><td>ID: </td><td><input type="text" name="guestID" /></td></tr>
                <tr><td>First Name: </td><td><input type="text" name="firstName" /></td></tr>
                <tr><td>Last Name: </td><td><input type="text" name="lastName" /></td></tr>
                <tr><td>Email: </td><td><input type="text" name="emailAddress" /></td></tr>
                <tr><td><input type="submit" value="Submit" name="action"/></td></tr>
            </table>
        </form>
        <form method="POST" action="EmailServletDB">
            <h1>Search an Email Record by Last Name </h1>
            <table>
                <tr><td>Last Name: </td><td><input type="text" name="lastname" /></td></tr>
                <tr><td><input type="submit" value="Search" name="action"/></td></tr>
            </table>
        </form>
        <form method="POST" action="EmailServletDB">
            <h1>Show All Email Records</h1>
            <table>
                <tr><td><input type="submit" value="Show All" name="action" /></td></tr>
            </table>
        </form>
        <form method="POST" action="EmailServletDB">
            <h1>Edit an Email Record</h1>
            <table>
                <tr><td>ID: </td><td><input type="text" name="guestID" /></td></tr>
                <tr><td>First Name: </td><td><input type="text" name="firstName" /></td></tr>
                <tr><td>Last Name: </td><td><input type="text" name="lastName" /></td></tr>
                <tr><td>Email: </td><td><input type="text" name="emailAddress" /></td></tr>
                <tr><td><input type="submit" value="Edit" name="action"/></td></tr>
                <tr><td>Response from Server: ${message1}</td></tr>
            </table>
        </form>
        <form method="POST" action="EmailServletDB">
            <h1>Delete an Email Record by ID</h1>
            <table>
                <tr><td>ID: </td><td><input type="text" name="guestID" /></td></tr>
                <tr><td><input type="submit" value="Delete" name="action" /></td></tr>
                <tr><td>Response from Server: ${message2}</td></tr>
            </table>
        </form>
        <form method="POST" action="EmailServletDB">
            <h1>Show Number of Email Records in the Database</h1>
            <table>
                <tr><td><input type="submit" value="Total Records" name="action" /></td></tr>
                <tr><td>Total number of records is: ${message3}</td></tr>
            </table>
        </form>
    </body>
</html>
