<%-- 
    Document   : resident
    Created on : May 27, 2023, 9:18:55 PM
    Author     : vsngh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!--Page Header Start-->
<section class="page-header">
    <div class="page-header-bg" style="background-image: url(assets/images/backgrounds/page-header-bg.jpg)">
    </div>
    <div class="page-header-bubble"><img src="assets/images/shapes/page-header-bubble.png" alt=""></div>
    <div class="container">
        <div class="page-header__inner">
            <ul class="thm-breadcrumb list-unstyled">
                <li><a href="index.html">Home</a></li>
                <li><span>/</span></li>
                <li>Portfolio</li>
            </ul>
            <h2>Portfolio</h2>
        </div>
    </div>
</section>
<!--Page Header End-->

<section class="portfolio-page">
    <div class="container-fluid" style="margin-top:10px; margin-bottom: 10px">
        <div class="" style="margin-top:10px; margin-bottom: 10px">
            <div class="rounded d-flex justify-content-center">
                <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light" style="width: 50%">
                    <div class="text-center">
                        <h3 class="text-primary">Profile</h3>
                    </div>
                    <table>
                        <div class="p-4">
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-primary"><i class="bi bi-person-circle text-white"></i></span>
                                <a style="display: flex; justify-content: flex-start; margin: auto 10px 10px">${user.name}</a>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-primary"><i class="bi bi-telephone-fill text-white"></i></span>
                                <a style="display: flex; justify-content: flex-start; margin: auto 10px 10px ">${user.phone}</a>
                            </div>
                                
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-primary"><i class="bi bi-geo-alt-fill text-white"></i></span>
                                <a style="display: flex; justify-content: flex-start; margin: auto 10px 10px">${user.room}</a>
                            </div>
                            <a class="btn btn-primary mt-2 signup"  href="<c:url value ="/user/edit.do"/>" >
                                Edit
                            </a>
                            <a class="btn btn-primary mt-2 signup"  href="<c:url value ="/user/change_pass.do"/>" >
                                Change Password
                            </a>

                            <!--<p class="text-center text-primary">Forgot your password?</p>-->
                        </div>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>