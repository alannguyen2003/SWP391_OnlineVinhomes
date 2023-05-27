<%-- 
    Document   : service
    Created on : May 26, 2023, 7:50:46 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!--Page Header Start-->
<div>
    <section class="page-header">
        <div class="page-header-bg" style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/page-header-bg.jpg)">
        </div>
        <div class="page-header-bubble"><img src="${pageContext.request.contextPath}/assets/images/shapes/page-header-bubble.png" alt=""></div>
        <div class="container">
            <div class="page-header__inner">
                <ul class="thm-breadcrumb list-unstyled">
                    <li>Services</li>
                </ul>
                <h2>Services Page</h2>
            </div>
        </div>
    </section>
</div>
<!--Page Header End-->
<!--Services Page 1 Start-->
<section class="services-page-1">
    <div class="container">
        <div class="row">
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="100ms">
                <div class="services-two__single">
                    <div class="services-two__img-box">
                        <div class="services-two__img">
                            <img src="${pageContext.request.contextPath}/assets/images/services/services-2-1.jpg" alt="">
                        </div>
                        <div class="services-two__icon">
                            <span class="icon-plumbing"></span>
                        </div>
                    </div>
                    <div class="services-two__content">
                        <h3 class="services-two__title"><a href="plumbing-services.html">Plumbing Services</a>
                        </h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="<c:url value="/service/service-list.do" />" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="200ms">
                <div class="services-two__single">
                    <div class="services-two__img-box">
                        <div class="services-two__img">
                            <img src="${pageContext.request.contextPath}/assets/images/services/services-2-2.jpg" alt="">
                        </div>
                        <div class="services-two__icon">
                            <span class="icon-laundry"></span>
                        </div>
                    </div>
                    <div class="services-two__content">
                        <h3 class="services-two__title"><a href="laundry-services.html">Laundry Services</a>
                        </h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="laundry-services.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="300ms">
                <div class="services-two__single">
                    <div class="services-two__img-box">
                        <div class="services-two__img">
                            <img src="${pageContext.request.contextPath}/assets/images/services/services-2-3.jpg" alt="">
                        </div>
                        <div class="services-two__icon">
                            <span class="icon-washing-plate"></span>
                        </div>
                    </div>
                    <div class="services-two__content">
                        <h3 class="services-two__title"><a href="kitchen-cleaning.html">Kitchen Cleaning</a>
                        </h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="kitchen-cleaning.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="400ms">
                <div class="services-two__single">
                    <div class="services-two__img-box">
                        <div class="services-two__img">
                            <img src="${pageContext.request.contextPath}/assets/images/services/services-2-4.jpg" alt="">
                        </div>
                        <div class="services-two__icon">
                            <span class="icon-window-cleaner"></span>
                        </div>
                    </div>
                    <div class="services-two__content">
                        <h3 class="services-two__title"><a href="window-cleaning.html">Window Cleaning</a></h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="window-cleaning.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="500ms">
                <div class="services-two__single">
                    <div class="services-two__img-box">
                        <div class="services-two__img">
                            <img src="${pageContext.request.contextPath}/assets/images/services/services-2-5.jpg" alt="">
                        </div>
                        <div class="services-two__icon">
                            <span class="icon-worker"></span>
                        </div>
                    </div>
                    <div class="services-two__content">
                        <h3 class="services-two__title"><a href="office-cleaning.html">Office Cleaning</a></h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="office-cleaning.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="600ms">
                <div class="services-two__single">
                    <div class="services-two__img-box">
                        <div class="services-two__img">
                            <img src="${pageContext.request.contextPath}/assets/images/services/services-2-6.jpg" alt="">
                        </div>
                        <div class="services-two__icon">
                            <span class="icon-sanitary"></span>
                        </div>
                    </div>
                    <div class="services-two__content">
                        <h3 class="services-two__title"><a href="toilet-cleaning.html">Toilet Cleaning</a></h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="toilet-cleaning.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
        </div>
    </div>
</section>
<!--Services Page 1 End-->
