<%-- 
    Document   : reset-pass
    Created on : May 29, 2023, 9:07:06 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container h-100" style="margin-bottom: 20%">
    <div class="row d-flex justify-content-center align-items-center h-100 ">
        <div class="col mb-5 text-center">
            <div class = "fs-1">Please enter your email</div>
            <div class = "mt-5">
                <form action="<c:url value="/reset-pass/reset-pass-handler.do"/>">
                    <input style="width: 300px;height: 50px" type="text" name="email"/>
                    <br/>
                    ${message}
                    <br/>
                    <button type="submit" class="btn btn-outline-primary mt-5" name="btAction" value="reset-pass-handler">Send Email</button>
                </form>
            </div>
        </div>
    </div>
</div>