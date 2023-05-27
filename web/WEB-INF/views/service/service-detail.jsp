<%-- 
    Document   : service-detail
    Created on : May 26, 2023, 8:54:04 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Detail -->
        <!--Service Details Start-->
        <section class="service-details">
            <div class="container">
                <div class="row">
                    <div class="col-xl-4 col-lg-5">
                        <div class="service-details__left">
                            <div class="service-details__category">
                                <h4 class="service-details__category-title">Services</h4>
                                <ul class="service-details__category-list list-unstyled">

                                    <li><a href="plumbing-services.html">Plumbing Service <span
                                                class="fa fa-angle-right"></span></a></li>

                                    <li><a href="office-cleaning.html">Office Cleaning <span
                                                class="fa fa-angle-right"></span></a></li>

                                    <li><a href="laundry-services.html">Laundry Service <span
                                                class="fa fa-angle-right"></span></a></li>

                                    <li class="active"><a href="kitchen-cleaning.html">Kitchen Cleaning <span
                                                class="fa fa-angle-right"></span></a></li>

                                    <li><a href="window-cleaning.html">Window Cleaning <span
                                                class="fa fa-angle-right"></span></a></li>

                                    <li><a href="toilet-cleaning.html">Toilet Cleaning <span
                                                class="fa fa-angle-right"></span></a></li>

                                </ul>
                            </div>
                            <div class="service-details__need-help">
                                <div class="service-details__need-help-bg"
                                     style="background-image: url(<c:url value="/assets/images/backgrounds/service-details-need-help-bg.jpg" />)">
                                </div>
                                <div class="service-details__need-help-icon">
                                    <span class="icon-phone-call"></span>
                                </div>
                                <h2 class="service-details__need-help-title">Best Solution <br> to Your House <br> &
                                    Office Cleaning</h2>
                                <div class="service-details__need-help-contact">
                                    <p>Call anytime</p>
                                    <a href="tel:2300068603"> +23 (000) 68 603</a>
                                </div>
                            </div>
                            <div class="service-details__download">
                                <a href="contact-page-1.html" class="thm-btn service-details__btn">Book an appointment
                                    <i class="fa fa-angle-right"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8 col-lg-7">
                        <div class="service-details__right">
                            <div class="service-details__img">
                                <img src="<c:url value="/assets/images/services/services-details-img-4.jpg" />" alt="">
                            </div>
                            <div class="service-details__content">
                                <h3 class="service-details__content-title">Kitchen Cleaning</h3>
                                <p class="service-details__text-1">Lorem ipsum is simply free text used by copytyping
                                    refreshing. Neque porro est qui dolorem ipsum quia quaed inventore veritatis et
                                    quasi architecto beatae vitae dicta sunt explicabo. Aelltes port lacus quis enim var
                                    sed efficitur turpis gilla sed sit amet finibus eros. Lorem Ipsum is simply dummy
                                    text of the printing and typesetting industry. Lorem Ipsum has been the ndustry
                                    standard dummy text ever since the 1500s, when an unknown printer took a galley of
                                    type and scrambled it to make a type specimen book.</p>
                                <p class="service-details__text-2">When an unknown printer took a simply free galley of
                                    type and scrambled it to make a type specimen book.</p>
                                <p class="service-details__text-3">It has survived not only five centuries. Lorem Ipsum
                                    is simply dummy text of the new design printng and type setting Ipsum take a look at
                                    our round. When an unknown printer took a galley of type and scrambled it to make a
                                    type specimen book. It has survived not only five centuries, but also the leap into
                                    electronic typesetting.</p>
                            </div>
                            <div class="service-details__points-box">
                                <ul class="list-unstyled service-details__points">
                                    <li>
                                        <div class="service-details__points-icon">
                                            <span class="icon-household"></span>
                                        </div>
                                        <div class="service-details__points-content">
                                            <p>Duis aute irure dolor in <br> reprehenderit in voluptate <br> velit esse
                                                cillum.</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="service-details__points-icon">
                                            <span class="icon-broom"></span>
                                        </div>
                                        <div class="service-details__points-content">
                                            <p>Duis aute irure dolor in <br> reprehenderit in voluptate <br> velit esse
                                                cillum.</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="service-details__benefits">
                                <div class="row">
                                    <hr>
                                    <c:forEach var= "cmt" items="${comments}">
                                        <div class="card mb-3">
                                            <div class="card-body">
                                                <div class="d-flex flex-start">
                                                    <img class="rounded-circle shadow-1-strong me-3"
                                                         src="<c:url value="/img/user.png"/>" alt="avatar" width="40"
                                                         height="40" />
                                                    <div class="w-100">
                                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                                            <h6 class="text-primary fw-bold mb-0">
                                                                ${cmt.username}
                                                                <span class="text-dark ms-2">${cmt.content}</span>
                                                            </h6>
                                                        </div>
                                                        <div class="d-flex justify-content-between align-items-center">
                                                            <p class="small mb-0" style="color: #aaa;">
                                                                <a href="#!" class="link-grey">${cmt.date}</a>
                                                            </p>
                                                            <div class="d-flex flex-row">
                                                                <i class="fas fa-star text-warning me-2"></i>
                                                                <i class="far fa-check-circle" style="color: greenyellow;"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div> 
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--Service Details End-->
        <!-- Feedback -->
    </body>
</html>
