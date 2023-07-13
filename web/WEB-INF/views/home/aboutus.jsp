<%-- 
    Document   : aboutus
    Created on : May 26, 2023, 7:24:45 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!--Get To Know Start-->
<section class="get-to-know">
    <div class="get-to-know-bubble float-bob-x">
        <img src="${pageContext.request.contextPath}/assets/images/shapes/get-to-know-bubble.png" alt="">
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xl-6">
                <div class="get-to-know__left">
                    <div class="get-to-know__img wow slideInLeft" data-wow-delay="100ms"
                         data-wow-duration="2500ms">
                        <img src="${pageContext.request.contextPath}/assets/images/resources/get-to-know-img-1.jpg" alt="">
                        <div class="get-to-know-shape-1"></div>
                        <div class="get-to-know-shape-2"></div>
                    </div>
                </div>
            </div>
            <div class="col-xl-6">
                <div class="get-to-know__right">
                    <div class="section-title text-left">
                        <span class="section-title__tagline">Company Benefits</span>
                        <h2 class="section-title__title">Get to Know About Our Brote Company</h2>
                    </div>
                    <p class="get-to-know__text">Vinhomes is the number 1 real estate development and management company in Vietnam, 
                        recognized for its outstanding scale, fast execution speed and high quality of service. 
                        We provide cleaning and repair services for Vinhomes Residential Area. 
                        In addition, we also provide other services such as laundry, tree planting, water equipment repair and replacement.
                        If you need any other information, feel free to contact us.</p>
                    <div class="get-to-know__points-box">
                        <ul class="list-unstyled get-to-know__points">
                            <li>
                                <div class="icon">
                                    <span class="icon-tick"></span>
                                </div>
                                <div class="text">
                                    <p>We are Committed</p>
                                </div>
                            </li>
                            <li>
                                <div class="icon">
                                    <span class="icon-tick"></span>
                                </div>
                                <div class="text">
                                    <p>Highly Rated Cleaning</p>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-unstyled get-to-know__points get-to-know__points--two">
                            <li>
                                <div class="icon">
                                    <span class="icon-tick"></span>
                                </div>
                                <div class="text">
                                    <p>Good price</p>
                                </div>
                            </li>
                            <li>
                                <div class="icon">
                                    <span class="icon-tick"></span>
                                </div>
                                <div class="text">
                                    <p>Friendly Attitude</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <a href="<c:url value="/home/aboutus.do"/>" class="thm-btn get-to-know__btn">Discover more <i
                            class="fa fa-angle-right"></i></a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--Get To Know End-->

<!--Counter One Start-->
<section class="counter-two">
    <div class="container">
        <div class="counter-two__inner">
            <ul class="list-unstyled counter-two__list">
                <li>
                    <div class="counter-two__icon">
                        <span class="icon-laundry-1"></span>
                    </div>
                    <div class="counter-two__count-box">
                        <div class="counter-two__count-box-inner">
                            <h3 class="odometer" data-count="2562">00</h3>
                            <span class="counter-two__plus">+</span>
                        </div>
                        <p class="counter-two__text">Satisfied Clients</p>
                    </div>
                </li>
                <li>
                    <div class="counter-two__icon">
                        <span class="icon-wipe"></span>
                    </div>
                    <div class="counter-two__count-box">
                        <div class="counter-two__count-box-inner">
                            <h3 class="odometer" data-count="562">00</h3>
                            <span class="counter-two__plus">+</span>
                        </div>
                        <p class="counter-two__text">Active Project</p>
                    </div>
                </li>
                <li>
                    <div class="counter-two__icon">
                        <span class="icon-trophy"></span>
                    </div>
                    <div class="counter-two__count-box">
                        <div class="counter-two__count-box-inner">
                            <h3 class="odometer" data-count="33">00</h3>
                            <span class="counter-two__plus">+</span>
                        </div>
                        <p class="counter-two__text">Winning Award</p>
                    </div>
                </li>
                <li>
                    <div class="counter-two__icon">
                        <span class="icon-teamwork"></span>
                    </div>
                    <div class="counter-two__count-box">
                        <div class="counter-two__count-box-inner">
                            <h3 class="odometer" data-count="552">00</h3>
                            <span class="counter-two__plus">+</span>
                        </div>
                        <p class="counter-two__text">Expert Teams</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</section>
<!--Counter One End-->

<!--CTA One Start-->
<section class="cta-one">
    <div class="cta-one-bg-bg">
        <div class="cta-one-bg jarallax" data-jarallax data-speed="0.2" data-imgPosition="50% 0%"
             style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/cta-one-bg.jpg);"></div>
    </div>
             <div class="cta-one-shape" style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/cta-one-shape.png);"></div>
    <div class="container">
        <div class="cta-one__inner">
            <h2 class="cta-one__title">Get professional & affortable <br> house cleaner <span>today</span></h2>
            <div class="cta-one__btn-box">
                <a href="about.html" class="thm-btn cta-one__btn-1">Discover more <i
                        class="fa fa-angle-right"></i></a>
                <a href="contact-page-1.html" class="thm-btn cta-one__btn-2">Get a free quote <i
                        class="fa fa-angle-right"></i></a>
            </div>
        </div>
    </div>
</section>
<!--CTA One End-->

<!--Brand One Start-->
<section class="brand-one brand-three">
    <div class="container">
        <div class="brand-one__inner">
            <div class="row">
                <div class="col-xl-12">
                    <div class="brand-one__main-content">
                        <div class="thm-swiper__slider swiper-container" data-swiper-options='{"spaceBetween": 100, "slidesPerView": 5, "autoplay": { "delay": 5000 }, "breakpoints": {
                             "0": {
                             "spaceBetween": 30,
                             "slidesPerView": 2
                             },
                             "375": {
                             "spaceBetween": 30,
                             "slidesPerView": 2
                             },
                             "575": {
                             "spaceBetween": 30,
                             "slidesPerView": 3
                             },
                             "767": {
                             "spaceBetween": 50,
                             "slidesPerView": 4
                             },
                             "991": {
                             "spaceBetween": 50,
                             "slidesPerView": 5
                             },
                             "1199": {
                             "spaceBetween": 100,
                             "slidesPerView": 5
                             }
                             }}'>
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-1.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-2.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-3.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-4.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-5.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-1.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-2.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-3.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-4.png" alt="">
                                </div><!-- /.swiper-slide -->
                                <div class="swiper-slide">
                                    <img src="${pageContext.request.contextPath}/assets/images/brand/brand-1-5.png" alt="">
                                </div><!-- /.swiper-slide -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--Brand One End-->
