<%-- 
    Document   : login
    Created on : May 27, 2023, 9:19:06 PM
    Author     : vsngh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="cookie" value="${pageContext.request.cookies}"/>
<div class="container-fluid" style="margin-top:10px; margin-bottom: 10px">
            <div class="" style="margin-top:10px; margin-bottom: 10px">
                <div class="rounded d-flex justify-content-center">
                    <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
                        <div class="text-center">
                            <h3 class="text-primary">Log In</h3>
                        </div>
                        <form action="<c:url value="/user/login_handler.do" />">
                            <div class="p-4">
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-person-plus-fill text-white"></i></span>
                                    <input type="text" class="form-control" name="email" placeholder="Email" value="${cookie.cEmail.value}">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-key-fill text-white"></i></span>
                                    <input type="password" class="form-control" name="password" placeholder="password" value="${cookie.cPass.value}">
                                </div>
                                <p style="color:red">${message}</p>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" name="rem" id="flexCheckDefault" ${cookie.cRem.value!=null?"checked":""}>
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Remember Me
                                    </label>
                                </div>
                                <button class="btn btn-primary text-center mt-2" name="op" value="submit_login" type="submit">
                                    Log In
                                </button>
                                <p class="text-center mt-5">Don't have an account?
                                    <a href="<c:url value="/user/signup.do"/>" style="text-decoration: none">Sign Up</a>
                                </p>
                                
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>