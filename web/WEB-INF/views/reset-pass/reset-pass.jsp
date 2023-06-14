<%-- 
    Document   : reset-pass
    Created on : May 29, 2023, 9:07:06 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<section class="ftco-section">
    <div class="container h-100 mt-50"  >
        <div class="row d-flex justify-content-center align-items-center h-100 ">
            <div class="col mb-5 text-center">
                <div class = "fs-1 text-primary">Please enter your email</div>
                <div class = "mt-50">
                    <form action="<c:url value="/reset-pass/reset-pass-handler.do"/>" class="">
                        <input class="form-control" style="width: 300px;height: 50px" type="text" name="email"/>
                        <div class="mt-5 mb-5">
                            ${message}
                        </div>
                        <button type="submit" class="btn btn-outline-primary mt-5" name="btAction" value="reset-pass-handler">Send Email</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>