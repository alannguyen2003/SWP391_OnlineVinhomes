<%-- 
    Document   : reset-pass-handler
    Created on : May 29, 2023, 10:28:03 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container h-100" style="margin-bottom: 20%">
    <div class="row d-flex justify-content-center align-items-center h-100 ">
        <div class="col mb-5 text-center">
            <div class = "fs-1">Reset Password Page</div>
            <div class = "mt-5">
                <form action = "<c:url value="/reset-pass/reset-pass-handler-commit.do"/>">
                    <input type ="text" hidden name="code" value="${code}"/>
                    <input type="text" hidden name="email" value="${email}"/>
                    <input class = "mt-3" style="width: 300px;height: 50px" placeholder = "Enter your new password" type="password" name="newPassword"/>
                    <br/>
                    <input class = "mt-3" style="width: 300px;height: 50px" type="password" placeholder = "Re-enter your new password" name="reEnter"/>
                    <br/>
                    ${message}
                    <br/>
                    <button type="submit" class="btn btn-outline-primary mt-5"  name="op" value="changeCommit">Reset Password</button>
                </form>
            </div>
        </div>
    </div>
</div>
