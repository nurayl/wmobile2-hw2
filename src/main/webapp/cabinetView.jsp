<%@ page import="beans.Course" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<jsp:useBean id="user" beanName="beans.User" type="beans.User" scope="request" />
<jsp:setProperty name="user" property="*" />
<%
    List<Course> styles = (List<Course>) request.getAttribute("styles");
%>
<div class="main">
    <div class="login">
        <h1>User Data</h1>
        <form method="post" action="cabinet">
            <input type="text" name="firstname" placeholder="Firstname" required="required" value="${user.firstname}"/>
            <input type="text" name="surname" placeholder="Lastname" required="required" value="${user.surname}"/>
            <input type="text" name="age" placeholder="Age" required="required" value="${user.age}"/>
            <input type="text" name="gender" placeholder="Gender" required="required"  value="${user.gender}" />
            <input type="submit" name="button" value="Done">
        </form>
    </div>
    <div class="table">
        <h1 style="color: #ffffff">Selectable Courses</h1>
        <table>
            <thead>
            <tr>
                <th>Course Name</th>
                <th>Enrollment</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                      <td>  <c:out value="${course.name}" /></td>
                        <td>
                            <form action="courses" method="post">
                                <input hidden type="text" name="name" id="${course.name}">
                                <input type="submit" value="Enrollment">
                            </form>
                        </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<style>

    * { -webkit-box-sizing:border-box; -moz-box-sizing:border-box; -ms-box-sizing:border-box; -o-box-sizing:border-box; box-sizing:border-box; }

    html { width: 100%; height:100%; overflow:hidden; }

    body {
        width: 100%;
        height:100%;
        font-family: 'Open Sans', sans-serif;
        background: rgb(94, 94, 94);
        overflow-y: scroll

    }
    .main{
        display: flex;
        align-items: center;
        justify-content: space-between;
        flex-direction: column;
        height: 100%;
        width: 90%;
        margin-right: auto;
        margin-left: auto;
    }
    .login {
        /*position: absolute;*/
        /*top: 50%;*/
        /*left: 50%;*/
        /*margin: -150px 0 0 -150px;*/
        /*width:300px;*/
        /*height:300px;*/
        width: 100%;
        background: #225a7d;
        padding: 4px 20px;
        border-radius: 9px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    .table{
        width: 100%;
    }
    .login h1 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }

    input {
        width: 100%;
        background: rgba(0,0,0,0.3);
        border: none;
        outline: none;
        padding: 10px;
        font-size: 13px;
        color: #fff;
        text-shadow: 1px 1px 1px rgba(0,0,0,0.3);
        border: 1px solid rgba(0,0,0,0.3);
        border-radius: 4px;
        box-shadow: inset 0 -5px 45px rgba(100,100,100,0.2), 0 1px 1px rgba(255,255,255,0.2);
        -webkit-transition: box-shadow .5s ease;
        -moz-transition: box-shadow .5s ease;
        -o-transition: box-shadow .5s ease;
        -ms-transition: box-shadow .5s ease;
        transition: box-shadow .5s ease;
        margin-bottom: 24px;
    }
    input:focus { box-shadow: inset 0 -5px 45px rgba(100,100,100,0.4), 0 1px 1px rgba(255,255,255,0.2); }
    .btn {
        line-height: 50px;
        height: 50px;
        text-align: center;
        width: 250px;
        cursor: pointer;
    }

    /*
    ========================
          BUTTON ONE
    ========================
    */
    .btn-one {
        color: #FFF;
        transition: all 0.3s;
        position: relative;
    }
    .btn-one span {
        transition: all 0.3s;
    }
    .btn-one::before {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 1;
        opacity: 0;
        transition: all 0.3s;
        border-top-width: 1px;
        border-bottom-width: 1px;
        border-top-style: solid;
        border-bottom-style: solid;
        border-top-color: rgba(255,255,255,0.5);
        border-bottom-color: rgba(255,255,255,0.5);
        transform: scale(0.1, 1);
    }
    .btn-one:hover span {
        letter-spacing: 2px;
    }
    .btn-one:hover::before {
        opacity: 1;
        transform: scale(1, 1);
    }
    .btn-one::after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 1;
        transition: all 0.3s;
        background-color: rgba(255,255,255,0.1);
    }
    .btn-one:hover::after {
        opacity: 0;
        transform: scale(0.1, 1);
    }



    table {
        width: 100%;
        border-collapse: collapse;
        overflow: hidden;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    th,
    td {
        padding: 15px;
        background-color: rgba(255, 255, 255, 0.2);
        color: #fff;
    }
    th {
        text-align: left;
    }
    thead th {
        background-color: #225a7d;
    }
    tbody tr:hover {
        background-color: rgba(255, 255, 255, 0.3);
    }
    tbody td {
        position: relative;
    }
    tbody td:hover:before {
        content: "";
        position: absolute;
        left: 0;
        right: 0;
        top: -9999px;
        bottom: -9999px;
        background-color: rgba(255, 255, 255, 0.2);
        z-index: -1;
    }

</style>