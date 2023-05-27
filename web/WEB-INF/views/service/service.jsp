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
            <c:forEach items="${requestScope.list}" var="o">
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
                            <h3 class="services-two__title"><a href="<c:url value="/service/service-list.do" />?id=${o.id}">${o.name}</a>
                            </h3>
                            <p class="services-two__text">Vinhome services.</p>
                            <a href="<c:url value="/service/service-list.do" />?id=${o.id}" class="services-two__btn">read more</a>
                        </div>
                    </div>
                </div>
                <!--Services Two single End-->
            </c:forEach>
        </div>
    </div>
</section>
<!--Services Page 1 End-->
