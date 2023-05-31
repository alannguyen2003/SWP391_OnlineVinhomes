
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">


    <!-- Mirrored from layerdrops.com/brote/main-html/index2.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 26 May 2023 07:21:57 GMT -->
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title> Cleaning Service </title>
        <!-- favicons Icons -->
        <link rel="apple-touch-icon" sizes="180x180" href="assets/images/favicons/apple-touch-icon.png" />
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/assets/images/favicons/favicon-32x32.png" />
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicons/favicon-16x16.png" />
        <link rel="manifest" href="assets/images/favicons/site.html" />
        <meta name="description" content="Cleaning Services HTML template " />

        <!-- fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com/">

        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>

        <link
            href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,wght@0,400;0,500;0,700;1,400;1,500;1,700&amp;display=swap"
            rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/animate/animate.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/animate/custom-animate.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/fontawesome/css/all.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/jarallax/jarallax.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/jquery-magnific-popup/jquery.magnific-popup.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/nouislider/nouislider.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/nouislider/nouislider.pips.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/odometer/odometer.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/swiper/swiper.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/brote-icons/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/tiny-slider/tiny-slider.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/reey-font/stylesheet.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/owl-carousel/owl.carousel.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/owl-carousel/owl.theme.default.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/bxslider/jquery.bxslider.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/bootstrap-select/css/bootstrap-select.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/vegas/vegas.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/timepicker/timePicker.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/order.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

        <!-- template styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/brote.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/brote-responsive.css" />

    </head>
    <body>

        <div class="custom-cursor__cursor"></div>
        <div class="custom-cursor__cursor-two"></div>
        <div class="preloader">
            <div class="preloader__image"></div>
        </div>
        <!-- /.preloader -->

        <div class="page-wrapper">
            <header class="main-header clearfix">
                <div class="main-header__top">
                    <div class="main-header__top-social-box">
                        <div class="container">
                            <div class="main-header__top-social-box-inner">
                                <p class="main-header__top-social-text">Welcome to our Brote Cleaning Services!</p>
                                <div class="main-header__top-social">
                                    <a href="#"><i class="fab fa-twitter"></i></a>
                                    <a href="#"><i class="fab fa-facebook"></i></a>
                                    <a href="#"><i class="fab fa-pinterest-p"></i></a>
                                    <a href="#"><i class="fab fa-instagram"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="main-header__top-details">
                        <div class="container">
                            <div class="main-header__top-details-inner">
                                <div class="main-header__logo">
                                    <a href="<c:url value="/home/index.do" />"><img src="assets/images/resources/logo-1.png" alt=""></a>
                                </div>
                                <ul class="list-unstyled main-header__top-details-list">
                                    <li>
                                        <div class="icon">
                                            <span class="icon-message"></span>
                                        </div>
                                        <div class="text">
                                            <h5><a href="mailto:brote@company.com">brote@company.com</a></h5>
                                            <p>Send mail</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="icon">
                                            <span class="icon-time"></span>
                                        </div>
                                        <div class="text">
                                            <h5>Mon to Sat</h5>
                                            <p>08am - 09pm</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="icon">
                                            <span class="icon-phone-call"></span>
                                        </div>
                                        <div class="text">
                                            <h5>Call Anytime</h5>
                                            <p><a href="tel:+2300068603">+23 (000) 68 603</a></p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="icon">
                                            <span class="icon-location"></span>
                                        </div>
                                        <div class="text">
                                            <h5>88 Kilda Broklyn Road</h5>
                                            <p>New York, USA</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <nav class="main-menu clearfix">
                    <div class="main-menu__wrapper clearfix">
                        <div class="container">
                            <div class="main-menu__wrapper-inner clearfix">
                                <div class="main-menu__left">
                                    <div class="main-menu__main-menu-box">
                                        <a href="#" class="mobile-nav__toggler"><i class="fa fa-bars"></i></a>
                                        <ul class="main-menu__list">
                                            <li>
                                                <a href="<c:url value="/home/index.do" />">Home </a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/home/aboutus.do" />">About </a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/service/service.do" />">Services </a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/home/contact.do" />">Contact </a>
                                            </li>
                                            <c:if test="${user == null}">
                                                <!-- Neu user chua login -->
                                                <li style="margin-left: 30rem">
                                                    <a href="<c:url value="/user/login.do" />">Sign In</a>
                                                </li>
                                            </c:if>

                                            <c:if test="${user != null}">
                                                <!-- Neu user da login -->
                                                <li style="margin-left: 18rem">
                                                    <a href="<c:url value="/user/profile.do" />">Hello, ${user.email}</a>
                                                </li>

                                                <li class="dropdown">
                                                    <a href="#"><i class="bi bi-person-fill"></i></a>
                                                    <ul>
                                                        <li><a href="<c:url value="/home/index.do"/>">View profile</a></li>
                                                        <li><a href="<c:url value="/order/myorder.do?aid=${user.AID}"/>">My Orders</a></li>
                                                        <li><a href="<c:url value="/user/logout.do" />">Log Out</a></li>
                                                    </ul>
                                                </li>
                                            </c:if>  
                                            <li>
                                                <a href="#"><i style="color: #b1c2f5;" class="bi bi-cart"></i></a>
                                            </li>

                                            <li>
                                                <a href="/vsos/cart/cart.do"><i style="color: #b1c2f5;" class="bi bi-search"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </header>


            <div class="stricky-header stricked-menu main-menu">
                <div class="sticky-header__content"></div><!-- /.sticky-header__content -->
            </div><!-- /.stricky-header -->

            <!-- View -->

            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />

            <!-- End View -->


            <!--Information Start-->
            <section class="information">
                <div class="container">
                    <div class="information__inner">
                        <div class="information__logo-box">
                            <div class="information__border-1"></div>
                            <div class="information__border-2"></div>
                            <a href="index.html"><img src="${pageContext.request.contextPath}/assets/images/resources/information-logo.png" alt=""></a>
                        </div>
                        <ul class="list-unstyled information__list">
                            <li>
                                <div class="information__icon">
                                    <span class="icon-phone"></span>
                                </div>
                                <div class="information__content">
                                    <p class="information__sub-title">Call anytime</p>
                                    <h5 class="information__number">
                                        <a href="tel:2300068603">+23 (000) 68 603</a>
                                    </h5>
                                </div>
                            </li>
                            <li>
                                <div class="information__icon">
                                    <span class="icon-envelope"></span>
                                </div>
                                <div class="information__content">
                                    <p class="information__sub-title">Send email</p>
                                    <h5 class="information__number">
                                        <a href="mailto:brote@company.com">brote@company.com</a>
                                    </h5>
                                </div>
                            </li>
                            <li>
                                <div class="information__icon">
                                    <span class="icon-location-1"></span>
                                </div>
                                <div class="information__content">
                                    <p class="information__sub-title">Visit office</p>
                                    <h5 class="information__number">88 Kilda Broklyn Road</h5>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </section>
            <!--Information End-->
            <!--Site Footer Start-->
            <footer class="site-footer">
                <div class="site-footer-shape-1"
                     style="background-image: url(assets/images/shapes/site-footer-shape-1.png);">
                </div>
                <div class="site-footer-shape-two">
                    <img src="${pageContext.request.contextPath}/assets/images/shapes/site-footer-shape-2.png" alt="">
                </div>
                <div class="site-footer__top">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-3 col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="100ms">
                                <div class="footer-widget__column footer-widget__about">
                                    <h3 class="footer-widget__title">Explore</h3>
                                    <div class="footer-widget__about-text-box">
                                        <p class="footer-widget__about-text">Tincidunt elit magnis nulla facilisis sagittis
                                            maecenas. Sapien nunced amet dolores.</p>
                                    </div>
                                    <div class="site-footer__social">
                                        <a href="#"><i class="fab fa-twitter"></i></a>
                                        <a href="#"><i class="fab fa-facebook"></i></a>
                                        <a href="#"><i class="fab fa-pinterest-p"></i></a>
                                        <a href="#"><i class="fab fa-instagram"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-2 col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="200ms">
                                <div class="footer-widget__column footer-widget__links clearfix">
                                    <h3 class="footer-widget__title">Links</h3>
                                    <ul class="footer-widget__links-list list-unstyled clearfix">
                                        <li><a href="about.html">About</a></li>
                                        <li><a href="team.html">Meet Our Team</a></li>
                                        <li><a href="about.html">What We Do</a></li>
                                        <li><a href="contact-page-1.html">Our Pricing</a></li>
                                        <li><a href="contact-page-2.html">Contact</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="300ms">
                                <div class="footer-widget__column footer-widget__articles clearfix">
                                    <h3 class="footer-widget__title">Articles</h3>
                                    <ul class="footer-widget__articles-list list-unstyled clearfix">
                                        <li>
                                            <div class="footer-widget__articles-img">
                                                <img src="${pageContext.request.contextPath}/assets/images/resources/footer-widget-articles-img-1.jpg" alt="">
                                                <a href="blog-details.html"><span class="fa fa-link"></span></a>
                                            </div>
                                            <div class="footer-widget__articles-content">
                                                <p class="footer-widget__articles-date">26 Jan, 2022</p>
                                                <h5 class="footer-widget__articles-sub-title"><a href="blog-details.html">6
                                                        Cleaning Hacks
                                                        that will Blow your Mind!</a></h5>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="footer-widget__articles-img">
                                                <img src="${pageContext.request.contextPath}/assets/images/resources/footer-widget-articles-img-2.jpg" alt="">
                                                <a href="blog-details.html"><span class="fa fa-link"></span></a>
                                            </div>
                                            <div class="footer-widget__articles-content">
                                                <p class="footer-widget__articles-date">26 Jan, 2022</p>
                                                <h5 class="footer-widget__articles-sub-title"><a href="blog-details.html">6
                                                        Cleaning Hacks
                                                        that will Blow your Mind!</a></h5>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="400ms">
                                <div class="footer-widget__column footer-widget__newsletter">
                                    <h3 class="footer-widget__title">Newsletter</h3>
                                    <p class="footer-widget__newsletter-text">Subscribe our newsletter to get <br> our
                                        latest update & news</p>
                                    <form class="footer-widget__newsletter-form">
                                        <div class="footer-widget__newsletter-input-box">
                                            <input type="email" placeholder="Email address" name="email">
                                            <button type="submit" class="footer-widget__newsletter-btn"><i
                                                    class="far fa-paper-plane"></i></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="site-footer__bottom">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="site-footer__bottom-inner">
                                    <p class="site-footer__bottom-text">© Copyright 2022 by <a href="#">brote.com</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
            <!--Site Footer End-->


        </div><!-- /.page-wrapper -->


        <div class="mobile-nav__wrapper">
            <div class="mobile-nav__overlay mobile-nav__toggler"></div>
            <!-- /.mobile-nav__overlay -->
            <div class="mobile-nav__content">
                <span class="mobile-nav__close mobile-nav__toggler"><i class="fa fa-times"></i></span>

                <div class="logo-box">
                    <a href="index.html" aria-label="logo image"><img src="${pageContext.request.contextPath}/assets/images/resources/logo-1.png" width="89"
                                                                      alt="" /></a>
                </div>
                <!-- /.logo-box -->
                <div class="mobile-nav__container"></div>
                <!-- /.mobile-nav__container -->

                <ul class="mobile-nav__contact list-unstyled">
                    <li>
                        <i class="fa fa-envelope"></i>
                        <a href="mailto:needhelp@packageName__.com">needhelp@brote.com</a>
                    </li>
                    <li>
                        <i class="fa fa-phone-alt"></i>
                        <a href="tel:666-888-0000">666 888 0000</a>
                    </li>
                </ul><!-- /.mobile-nav__contact -->
                <div class="mobile-nav__top">
                    <div class="mobile-nav__social">
                        <a href="#" class="fab fa-twitter"></a>
                        <a href="#" class="fab fa-facebook-square"></a>
                        <a href="#" class="fab fa-pinterest-p"></a>
                        <a href="#" class="fab fa-instagram"></a>
                    </div><!-- /.mobile-nav__social -->
                </div><!-- /.mobile-nav__top -->



            </div>
            <!-- /.mobile-nav__content -->
        </div>
        <!-- /.mobile-nav__wrapper -->

        <div class="search-popup">
            <div class="search-popup__overlay search-toggler"></div>
            <!-- /.search-popup__overlay -->
            <div class="search-popup__content">
                <form action="#">
                    <label for="search" class="sr-only">search here</label><!-- /.sr-only -->
                    <input type="text" id="search" placeholder="Search Here..." />
                    <button type="submit" aria-label="search submit" class="thm-btn">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div>
            <!-- /.search-popup__content -->
        </div>
        <!-- /.search-popup -->

        <a href="#" data-target="html" class="scroll-to-target scroll-to-top"><i class="fa fa-angle-up"></i></a>

        <!-- Js Plugins -->
        <script src="${pageContext.request.contextPath}/assets/vendors/jquery/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/jarallax/jarallax.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/jquery-ajaxchimp/jquery.ajaxchimp.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/jquery-appear/jquery.appear.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/jquery-circle-progress/jquery.circle-progress.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/jquery-magnific-popup/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/jquery-validate/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/nouislider/nouislider.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/odometer/odometer.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/swiper/swiper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/tiny-slider/tiny-slider.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/wnumb/wNumb.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/wow/wow.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/isotope/isotope.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/countdown/countdown.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/bxslider/jquery.bxslider.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/bootstrap-select/js/bootstrap-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/vegas/vegas.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/jquery-ui/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/timepicker/timePicker.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/circleType/jquery.circleType.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/circleType/jquery.lettering.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/order/order.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendors/home/home.js"></script>
        <!-- template js -->
        <script src="${pageContext.request.contextPath}/assets/js/brote.js"></script>
    </body>
</html>
