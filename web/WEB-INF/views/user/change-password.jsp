<%-- 
    Document   : info
    Created on : Feb 28, 2023, 3:14:40 PM
    Author     : Beyond Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Page Header Start-->
<section class="page-header">
    <div class="page-header-bg" style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/page-header-bg.jpg)">
    </div>
    <div class="page-header-bubble"><img src="${pageContext.request.contextPath}/assets/images/shapes/page-header-bubble.png" alt=""></div>
    <div class="container">
        <div class="page-header__inner">
            <ul class="thm-breadcrumb list-unstyled">
                <li><a href="<c:url value="/home/index.do"/>">Home</a></li>
                <li><span>/</span></li>
                <li>Profile</li>
            </ul>
            <h2>Security</h2>
        </div>
    </div>
</section>
<!--Page Header End-->

<div class="container-xl p-5">
    <!-- Tab bar navigation-->
    <ul class="sub-nav" style="margin-bottom: -1px" activeindex="2">
        <li class="sub-nav__item" label="Billing"><a href="<c:url value="/user/profile.do" />"><i class="fa fa-info"></i><span>Information</span></a></li>
        <li class="sub-nav__item" label="Billing"><a href="<c:url value="/user/change-password.do" />" class="active"><i class="fa fa-lock"></i><span>Security</span></a></li>
    </ul>
    <!-- Divider-->
    <hr class="mt-0 mb-5">
    <!-- Profile content row-->
    <div class="row gx-5">
      
        <div class="col-8 offset-2">
            <!-- Account details card-->
            <div class="card card-raised mb-5">
                <div class="card-body p-5">
                    <div class="card-title">Change Password</div>
                    <form action="<c:url value="/user/change-password.do" />" method="post">
                        <div class="mb-4 form-group">
                            <label class="label">Current password</label>
                            <input type="password" name="password" class="w-100 form-control">
                        </div>
                        <div class="mb-4 form-group">
                            <label class="label">New password</label>
                            <input type="password" name="newPassword" class="w-100 form-control">
                        </div>
                        <div class="mb-4 form-group">
                            <label class="label">Confirmation</label>
                            <input type="password" name="confirmPassword" class="w-100 form-control">
                        </div>
                        <p class ="text-danger"> ${message} </p>
                        <!-- Save changes button-->
                        <div class="text-end"><button class="btn btn-primary" type="submit">Reset password</button></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>