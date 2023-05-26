<%-- 
    Document   : index
    Created on : May 25, 2023, 7:05:53 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!--Main Slider Start-->
<section class="main-slider-two clearfix">
    <div class="swiper-container thm-swiper__slider" data-swiper-options='{"slidesPerView": 1, "loop": true,
         "effect": "fade",
         "pagination": {
         "el": "#main-slider-pagination",
         "type": "bullets",
         "clickable": true
         },
         "navigation": {
         "nextEl": "#main-slider__swiper-button-next",
         "prevEl": "#main-slider__swiper-button-prev"
         },
         "autoplay": {
         "delay": 5000
         }}'>
        <div class="swiper-wrapper">

            <div class="swiper-slide">
                <div class="main-slider-two-bg-box">
                    <div class="main-slider-two-image-layer"
                         style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/main-slider-2-1.jpg);"></div>

                    <div class="main-slider-two-shape-box">
                        <div class="main-slider-two-shape-1"
                             style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-shape-1.png);">
                        </div>
                        <div class="main-slider-two-shape-2 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-shape-2.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-1 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-1.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-2 float-bob-y">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-2.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-3 zoominout">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-3.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-4 zoom-fade">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-4.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-5 zoominout-2">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-5.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-6 float-bob-y">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-6.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-7 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-7.png" alt="">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="main-slider-two__content">
                                <p class="main-slider-two__sub-title">We’re Best Cleaning Company</p>
                                <h2 class="main-slider-two__title">Providing Best <br> Cleaning Services</h2>
                                <p class="main-slider-two__text">Lorem ipsum is simply free text dolor sit am
                                    adipi we help <br> simply free text in the you ensure everyone. </p>
                                <div class="main-slider-two__btn-box">
                                    <a href="${pageContext.request.contextPath}/home/aboutus.do"" class="thm-btn main-slider-two__btn">Discover more <i
                                            class="fa fa-angle-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="swiper-slide">
                <div class="main-slider-two-bg-box">
                    <div class="main-slider-two-image-layer"
                         style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/main-slider-2-2.jpg);"></div>

                    <div class="main-slider-two-shape-box">
                        <div class="main-slider-two-shape-1"
                             style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-shape-1.png);">
                        </div>
                        <div class="main-slider-two-shape-2 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-shape-2.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-1 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-1.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-2 float-bob-y">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-2.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-3 zoominout">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-3.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-4 zoom-fade">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-4.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-5 zoominout-2">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-5.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-6 float-bob-y">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-6.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-7 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-7.png" alt="">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="main-slider-two__content">
                                <p class="main-slider-two__sub-title">We’re Best Cleaning Company</p>
                                <h2 class="main-slider-two__title">Providing Best <br> Cleaning Services</h2>
                                <p class="main-slider-two__text">Lorem ipsum is simply free text dolor sit am
                                    adipi we help <br> simply free text in the you ensure everyone. </p>
                                <div class="main-slider-two__btn-box">
                                    <a href="about.html" class="thm-btn main-slider-two__btn">Discover more <i
                                            class="fa fa-angle-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="swiper-slide">
                <div class="main-slider-two-bg-box">
                    <div class="main-slider-two-image-layer"
                         style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/main-slider-2-3.jpg);"></div>

                    <div class="main-slider-two-shape-box">
                        <div class="main-slider-two-shape-1"
                             style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-shape-1.png);">
                        </div>
                        <div class="main-slider-two-shape-2 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-shape-2.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-1 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-1.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-2 float-bob-y">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-2.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-3 zoominout">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-3.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-4 zoom-fade">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-4.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-5 zoominout-2">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-5.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-6 float-bob-y">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-6.png" alt="">
                        </div>
                        <div class="main-slider-two-bubble-7 float-bob-x">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/main-slider-two-bubble-7.png" alt="">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="main-slider-two__content">
                                <p class="main-slider-two__sub-title">We’re Best Cleaning Company</p>
                                <h2 class="main-slider-two__title">Providing Best <br> Cleaning Services</h2>
                                <p class="main-slider-two__text">Lorem ipsum is simply free text dolor sit am
                                    adipi we help <br> simply free text in the you ensure everyone. </p>
                                <div class="main-slider-two__btn-box">
                                    <a href="${pageContext.request.contextPath}/home/aboutus.do" class="thm-btn main-slider-two__btn">Discover more <i
                                            class="fa fa-angle-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- If we need navigation buttons -->

    </div>
</section>
<!--Main Slider End-->

<!--Feature Two Start-->
<section class="feature-two">
    <div class="container">
        <div class="row">
            <!--Feature Two Single Start-->
            <div class="col-xl-4 wow fadeInLeft" data-wow-delay="100ms">
                <div class="feature-two__single">
                    <div class="feature-two__icon">
                        <img src="${pageContext.request.contextPath}/assets/images/icon/feature-one-icon-1.png" alt="">
                    </div>
                    <div class="feature-two__content">
                        <h3 class="feature-two__title"><a href="${pageContext.request.contextPath}/about.html">Outdoor Service</a></h3>
                        <p class="feature-two__text">Lorem ipsum dolor sit amet, etur adipisicing elit.</p>
                    </div>
                    <div class="feature-two__star-1">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-1.png" alt="">
                    </div>
                    <div class="feature-two__star-2">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-2.png" alt="">
                    </div>
                    <div class="feature-two__star-3">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-3.png" alt="">
                    </div>
                </div>
            </div>
            <!--Feature Two Single End-->
            <!--Feature Two Single Start-->
            <div class="col-xl-4 wow fadeInLeft" data-wow-delay="200ms">
                <div class="feature-two__single">
                    <div class="feature-two__icon">
                        <img src="${pageContext.request.contextPath}/assets/images/icon/feature-one-icon-2.png" alt="">
                    </div>
                    <div class="feature-two__content">
                        <h3 class="feature-two__title"><a href="${pageContext.request.contextPath}/about.html">House Cleaning</a></h3>
                        <p class="feature-two__text">Lorem ipsum dolor sit amet, etur adipisicing elit.</p>
                    </div>
                    <div class="feature-two__star-1">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-1.png" alt="">
                    </div>
                    <div class="feature-two__star-2">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-2.png" alt="">
                    </div>
                    <div class="feature-two__star-3">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-3.png" alt="">
                    </div>
                </div>
            </div>
            <!--Feature Two Single End-->
            <!--Feature Two Single Start-->
            <div class="col-xl-4 wow fadeInLeft" data-wow-delay="300ms">
                <div class="feature-two__single">
                    <div class="feature-two__icon">
                        <img src="${pageContext.request.contextPath}/assets/images/icon/feature-one-icon-3.png" alt="">
                    </div>
                    <div class="feature-two__content">
                        <h3 class="feature-two__title"><a href="${pageContext.request.contextPath}/plumbing-services.html">Plumber Service</a></h3>
                        <p class="feature-two__text">Lorem ipsum dolor sit amet, etur adipisicing elit.</p>
                    </div>
                    <div class="feature-two__star-1">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-1.png" alt="">
                    </div>
                    <div class="feature-two__star-2">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-2.png" alt="">
                    </div>
                    <div class="feature-two__star-3">
                        <img src="${pageContext.request.contextPath}/assets/images/shapes/feature-two-star-3.png" alt="">
                    </div>
                </div>
            </div>
            <!--Feature Two Single End-->
        </div>
    </div>
</section>
<!--Feature Two End-->

<!--We Cleaning Start-->
<section class="we-cleaning">
    <div class="container">
        <div class="row">
            <div class="col-xl-6">
                <div class="we-cleaning__left">
                    <div class="we-cleaning__img-box wow slideInLeft" data-wow-delay="100ms"
                         data-wow-duration="2500ms">
                        <div class="we-cleaning__img">
                            <img src="${pageContext.request.contextPath}/assets/images/resources/we-cleaning-img-1.jpg" alt="">
                        </div>
                        <div class="we-cleaning-line">
                            <img src="${pageContext.request.contextPath}/assets/images/shapes/we-cleaning-line.png" alt="">
                        </div>
                        <div class="we-cleaning__small-img">
                            <img src="${pageContext.request.contextPath}/assets/images/resources/we-cleaning-small-img.jpg" alt="">
                        </div>
                        <div class="we-cleaning__video-link">
                            <a href="https://www.youtube.com/watch?v=Get7rqXYrbQ" class="video-popup">
                                <div class="we-cleaning__video-icon">
                                    <span class="fa fa-play"></span>
                                    <i class="ripple"></i>
                                </div>
                            </a>
                        </div>
                        <div class="we-cleaning__shape-1"></div>
                        <div class="we-cleaning__shape-2"></div>
                        <div class="we-cleaning__shape-3"></div>
                        <div class="we-cleaning__shape-4"></div>
                        <div class="we-cleaning__shape-5"></div>
                    </div>
                </div>
            </div>
            <div class="col-xl-6">
                <div class="we-cleaning__right">
                    <div class="section-title text-left">
                        <span class="section-title__tagline">Best Experts in Cleaning</span>
                        <h2 class="section-title__title">We’re Cleaning for you Since 1987</h2>
                    </div>
                    <p class="we-cleaning__text-1">Lorem ipsum is simply free text dolor sit am adipi we help
                        you ensure everyone. Tincidunt elit magnis nulla facilisis sagittis maecenas. Sapien
                        nunced amet dolores sit ipsum velit purus aliq massa fringilla leo.</p>
                    <div class="we-cleaning__points-box">
                        <ul class="list-unstyled we-cleaning__points clearfix">
                            <li>
                                <div class="we-cleaning__icon">
                                    <span class="icon-house-cleaning"></span>
                                </div>
                                <div class="we-cleaning__text-box">
                                    <h4 class="we-cleaning__title">Residential Cleaning <br> Services</h4>
                                    <p class="we-cleaning__text-2">Tincidunt elit magnis nulla sit <br>
                                        facilisis sagittis maecenas.</p>
                                </div>
                            </li>
                            <li>
                                <div class="we-cleaning__icon">
                                    <span class="icon-buildings"></span>
                                </div>
                                <div class="we-cleaning__text-box">
                                    <h4 class="we-cleaning__title">Commercial Cleaning <br> Services</h4>
                                    <p class="we-cleaning__text-2">Tincidunt elit magnis nulla sit <br>
                                        facilisis sagittis maecenas.</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <a href="about.html" class="thm-btn we-cleaning__btn">Discover more <i
                            class="fa fa-angle-right"></i></a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--We Cleaning End-->
<!--Services Two Start-->
<section class="services-two">
    <div class="services-two-bubble float-bob-x"
         style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/services-two-bubble.png);"></div>
    <div class="services-two-dot-1 float-bob-y">
        <img src="${pageContext.request.contextPath}/assets/images/shapes/services-two-dot-1.png" alt="">
    </div>
    <div class="services-two-dot-2 float-bob-y">
        <img src="${pageContext.request.contextPath}/assets/images/shapes/services-two-dot-2.png" alt="">
    </div>
    <div class="container">
        <div class="section-title text-center">
            <span class="section-title__tagline">What We’re Offering</span>
            <h2 class="section-title__title">Providing the Best Services <br> for Our Customers</h2>
        </div>
        <div class="row">
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 wow fadeInUp" data-wow-delay="100ms">
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
                        <h3 class="services-two__title"><a href="${pageContext.request.contextPath}/plumbing-services.html">Plumbing Services</a>
                        </h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="${pageContext.request.contextPath}/plumbing-services.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 wow fadeInUp" data-wow-delay="200ms">
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
                        <h3 class="services-two__title"><a href="${pageContext.request.contextPath}/laundry-services.html">Laundry Services</a>
                        </h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="${pageContext.request.contextPath}/laundry-services.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
            <!--Services Two single Start-->
            <div class="col-xl-4 col-lg-4 wow fadeInUp" data-wow-delay="300ms">
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
                        <h3 class="services-two__title"><a href="${pageContext.request.contextPath}/kitchen-cleaning.html">Kitchen Cleaning</a>
                        </h3>
                        <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                            you ensure everyone.</p>
                        <a href="${pageContext.request.contextPath}/kitchen-cleaning.html" class="services-two__btn">read more</a>
                    </div>
                </div>
            </div>
            <!--Services Two single End-->
        </div>
    </div>
</section>
<!--Services Two End-->

<!--Brand One Start-->
<section class="brand-one brand-two">
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

<!--FAQ One Start-->
<section class="faq-one">
    <div class="faq-one-bg" style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/faq-one-bg.jpg);"></div>
    <div class="faq-one-shape-1 float-bob-x"
         style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/faq-one-shape-1.png);"></div>
    <div class="container">
        <div class="row">
            <div class="col-xl-8 col-lg-9">
                <div class="faq-one__left">
                    <div class="section-title text-left">
                        <span class="section-title__tagline">Our Company FAQs</span>
                        <h2 class="section-title__title">Frequently Asked Question from Our Clients</h2>
                    </div>
                    <div class="accrodion-grp" data-grp-name="faq-one-accrodion">
                        <div class="accrodion active">
                            <div class="accrodion-title">
                                <h4>Nulla eu purus scelerisque, dignissim diam.</h4>
                            </div>
                            <div class="accrodion-content">
                                <div class="inner">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                        veniam.</p>
                                </div><!-- /.inner -->
                            </div>
                        </div>
                        <div class="accrodion">
                            <div class="accrodion-title">
                                <h4>Proin imperdiet mi id urna egestas dictum.</h4>
                            </div>
                            <div class="accrodion-content">
                                <div class="inner">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                        veniam.</p>
                                </div><!-- /.inner -->
                            </div>
                        </div>
                        <div class="accrodion last-chiled">
                            <div class="accrodion-title">
                                <h4>Cras ultrices elit eget ex pulvinar, ac imperdiet leo lacinia.</h4>
                            </div>
                            <div class="accrodion-content">
                                <div class="inner">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                        veniam.</p>
                                </div><!-- /.inner -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--FAQ One End-->

<!--Project One Start-->
<section class="project-one">
    <div class="project-one__top">
        <div class="container">
            <div class="row">
                <div class="col-xl-6 col-lg-6">
                    <div class="project-one__left">
                        <div class="section-title text-left">
                            <span class="section-title__tagline">Complete Projects</span>
                            <h2 class="section-title__title">Keep Eye on Our New Projects</h2>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-6">
                    <div class="project-one__right">
                        <p class="project-one__text-1">Lorem ipsum dolor sit amet elit, sed do eiusmod tempor
                            incidut labore et dolore magna for aliqua. Cum sociis natoque penatibus et mages.
                            Aene an massa.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="project-one__bottom">
        <div class="project-one__bottom-container">
            <div class="owl-carousel owl-theme thm-owl__carousel project-one__carousel" data-owl-options='{
                 "loop": true,
                 "autoplay": true,
                 "margin": 30,
                 "nav": false,
                 "dots": true,
                 "smartSpeed": 500,
                 "autoplayTimeout": 10000,
                 "navText": ["<span class=\"fa fa-angle-left\"></span>","<span class=\"fa fa-angle-right\"></span>"],
                 "responsive": {
                 "0": {
                 "items": 1
                 },
                 "768": {
                 "items": 2
                 },
                 "992": {
                 "items": 2
                 },
                 "1200": {
                 "items": 4
                 }
                 }
                 }'>
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-1.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">House Cleaning</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-1.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-2.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Outdoor Service</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-2.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-3.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Plumber Service</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-3.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-4.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Window Cleaning</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-4.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-1.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">House Cleaning</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-1.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-2.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Outdoor Service</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-2.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-3.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Plumber Service</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-3.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-4.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Window Cleaning</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-4.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-1.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">House Cleaning</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-1.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-2.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Outdoor Service</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-2.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-3.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Plumber Service</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-3.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
                <!--Project One Single Start-->
                <div class="project-one__single">
                    <div class="project-one__img">
                        <img src="${pageContext.request.contextPath}/assets/images/project/project-1-4.jpg" alt="">
                        <div class="project-one__content">
                            <h4 class="project-one__title"><a href="portfolio-details.html">Window Cleaning</a>
                            </h4>
                            <p class="project-one__sub-title">Cleaning Project</p>
                        </div>
                        <div class="project-one__arrow">
                            <a class="img-popup" href="${pageContext.request.contextPath}/assets/images/project/project-1-4.jpg"><i
                                    class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
                <!--Project One Single End-->
            </div>
        </div>
    </div>
</section>
<!--Project One End-->

<!--Process Start-->
<section class="process">
    <div class="container">
        <div class="section-title text-center">
            <span class="section-title__tagline">Simple 3 Steps Clean</span>
            <h2 class="section-title__title">Easy & Quick See How <br> it Works</h2>
        </div>
        <div class="process__inner">
            <div class="process-line">
                <img src="${pageContext.request.contextPath}/assets/images/shapes/process-line.png" alt="">
            </div>
            <div class="row">
                <!--Process Single Start-->
                <div class="col-xl-4 col-lg-4">
                    <div class="process__single">
                        <div class="process__icon-box">
                            <div class="process__icon">
                                <span class="icon-find-my-friend"></span>
                                <div class="process-icon-shape"
                                     style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/process-icon-shape.png);">
                                </div>
                            </div>
                            <div class="process__count"></div>
                        </div>
                        <div class="process__content">
                            <h3 class="process__title"><a href="team.html">Look for Our <br> Experts</a></h3>
                            <p class="process__text">Lorem ipsum is simply free text dolor sit am adipi we help
                                you ensure everyone.</p>
                        </div>
                    </div>
                </div>
                <!--Process Single End-->
                <!--Process Single Start-->
                <div class="col-xl-4 col-lg-4">
                    <div class="process__single process__single-two">
                        <div class="process__icon-box">
                            <div class="process__icon">
                                <span class="icon-choose"></span>
                                <div class="process-icon-shape"
                                     style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/process-icon-shape.png);">
                                </div>
                            </div>
                            <div class="process__count"></div>
                        </div>
                        <div class="process__content">
                            <h3 class="process__title"><a href="services-page-1.html">Choose Our <br>
                                    Services</a></h3>
                            <p class="process__text">Lorem ipsum is simply free text dolor sit am adipi we help
                                you ensure everyone.</p>
                        </div>
                    </div>
                </div>
                <!--Process Single End-->
                <!--Process Single Start-->
                <div class="col-xl-4 col-lg-4">
                    <div class="process__single">
                        <div class="process__icon-box">
                            <div class="process__icon">
                                <span class="icon-tick-mark"></span>
                                <div class="process-icon-shape"
                                     style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/process-icon-shape.png);">
                                </div>
                            </div>
                            <div class="process__count"></div>
                        </div>
                        <div class="process__content">
                            <h3 class="process__title"><a href="contact-page-1.html">Book an <br>
                                    Appointment</a></h3>
                            <p class="process__text">Lorem ipsum is simply free text dolor sit am adipi we help
                                you ensure everyone.</p>
                        </div>
                    </div>
                </div>
                <!--Process Single End-->
            </div>
        </div>
    </div>
</section>
<!--Process End-->

<!--Call One Start-->
<section class="call-one">
    <div class="call-one-shape-1" style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/call-one-shape-1.png);">
    </div>
    <div class="call-one-shape-2" style="background-image: url(${pageContext.request.contextPath}/assets/images/shapes/call-one-shape-2.png);">
    </div>
    <div class="container">
        <div class="call-one__inner">
            <div class="call-one__text-box">
                <p class="call-one__text">Call us to Take an Extraordinary Service!</p>
            </div>
            <div class="call-one__call-number">
                <a href="tel:2300068603"> <i class="fas fa-phone-alt"></i> +23 (000) 68 603</a>
            </div>
        </div>
    </div>
</section>
<!--Call One End-->

<!--Why Choose One Start-->
<section class="why-choose-one">
    <div class="why-choose-one-shape-1"
         style="background-image: url(assets/images/shapes/why-choose-one-shape-1.png);"></div>
    <div class="why-choose-one__img">
        <img src="${pageContext.request.contextPath}/assets/images/resources/why-choose-one-img-1.png" alt="">
    </div>
    <div class="why-choose-one-bubble-1">
        <img src="${pageContext.request.contextPath}/assets/images/shapes/why-choose-bubble-1.png" alt="">
    </div>
    <div class="why-choose-one-bubble-2">
        <img src="${pageContext.request.contextPath}/assets/images/shapes/why-choose-bubble-2.png" alt="">
    </div>
    <div class="why-choose-one-bubble-3">
        <img src="${pageContext.request.contextPath}/assets/images/shapes/why-choose-bubble-3.png" alt="">
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xl-6">
                <div class="why-choosee-one__left">
                    <div class="why-choose-one__img-2">
                        <img src="${pageContext.request.contextPath}/assets/images/resources/why-choose-one-img-2.jpg" alt="">
                    </div>
                    <div class="why-choose-one__img-3">
                        <img src="${pageContext.request.contextPath}/assets/images/resources/why-choose-one-img-3.jpg" alt="">
                    </div>
                </div>
            </div>
            <div class="col-xl-6">
                <div class="why-choose-one__right">
                    <div class="section-title text-left">
                        <span class="section-title__tagline">Why Should Choose Us</span>
                        <h2 class="section-title__title">Our First Priority is Your Happiness with Our Services
                        </h2>
                    </div>
                    <ul class="list-unstyled why-choose-one__points">
                        <li>
                            <div class="icon">
                                <span class="icon-checked"></span>
                            </div>
                            <div class="text">
                                <h4>100% Your Satisfaction</h4>
                                <p>Lorem ipsum is simply free text dolor sit am adipi we help you ensure
                                    everyone. Tincidunt elit magnis nulla facilisis sagittis maecenas massa
                                    fringilla leo.</p>
                            </div>
                        </li>
                        <li>
                            <div class="icon">
                                <span class="icon-checked"></span>
                            </div>
                            <div class="text">
                                <h4>Cost Effective Services</h4>
                                <p>Lorem ipsum is simply free text dolor sit am adipi we help you ensure
                                    everyone. Tincidunt elit magnis nulla facilisis sagittis maecenas massa
                                    fringilla leo.</p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!--Why Choose One End-->




