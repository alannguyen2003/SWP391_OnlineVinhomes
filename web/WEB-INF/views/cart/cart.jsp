<%-- 
    Document   : cart
    Created on : May 28, 2023, 10:20:33 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
                <li>Cart</li>
            </ul>
            <h2>Service Cart</h2>
        </div>
    </div>
</section>
<!--Page Header End-->

<c:set var= "size" value ="${sessionScope.size}"/>

<div class="container h-100" style="margin-bottom: 10%; margin-top: 5%">
    <div class="row d-flex justify-content-center align-items-center h-100 ">
        <div class="col">
            <p class="" style="margin-bottom: 10px"><h2 class="section-title__title">Service Cart </h2><h4 class="text mb-2 mt-1">(${size} item in your cart)</h4></p>
            <div class="card mb-4">
                <div class="card-body" style="margin-bottom: 0px;">
                    <c:forEach var="item" items="${sessionScope.cart.items}" varStatus="loop">
                        <div class="row align-items-center">
                            <div  style="animation-delay: 0.5s"class="col-md-2">
                                <img src="${pageContext.request.contextPath}/assets/images/services/services-${item.service.categoryID}-${item.service.serviceID}.jpg" class="img-fluid " alt="" style="width: 165px; height: 110px;">
                            </div>
                            <div class="col-md-2" style="display: flex; justify-content: flex-start">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Name</p>
                                    <p class="lead fw-normal mb-0">${item.service.name}</p>
                                </div>
                            </div>
                            <div class="col-md-2" style="display: flex; justify-content: flex-end">
                                <div >
                                    <p class="small text-muted mb-4 pb-2 d-flex justify-content-end">Min Price</p>
                                    <p class="lead fw-normal mb-0">$<fmt:formatNumber value="${item.service.getLowerPrice()}" pattern="##.#"/></p>
                                </div>
                            </div>
                            <div class="col-md-2" style="display: flex; justify-content: flex-end">
                                <div >
                                    <p class="small text-muted mb-4 pb-2 d-flex justify-content-end">Max Price</p>
                                    <p class="lead fw-normal mb-0">$<fmt:formatNumber value="${item.service.getUpperPrice()}" pattern="##.#"/></p>
                                </div>
                            </div>
                            <div class="col-md-2" style="display: flex; justify-content: flex-end">
                                <div>
                                    <p class="small text-muted mb-4 pb-2 d-flex justify-content-end"> Estimate</p>
                                    <p class="lead fw-normal mb-0">$<fmt:formatNumber value="${(item.service.getLowerPrice() + item.service.getUpperPrice()) / 2}" pattern="##.#"/></p>
                                </div>
                            </div>
                            <div class="col-md-2" style="display: flex; justify-content: flex-start">
                                <div>
                                    <a class="text-body" href="<c:url value="/cart/removeFromCart.do?id=${item.service.serviceID}"/>">Remove</a>
                                </div>
                            </div>
                        </div>
                        <hr/>
                    </c:forEach>
                    <c:if test="${sessionScope.size == null}">
                        <div style="display: flex; justify-content: center">
                            <p class="lead fw-normal mb-0"> There is no item in your cart :( </p>
                        </div>
                    </c:if>
                </div>
            </div>

            <div class="card mb-5">
                <div class="card-body p-4" style="margin-bottom: 0px;">

                    <div class="float-end">
                        <p class="mb-0 me-5 d-flex align-items-center">
                            <span class="small text-muted me-2">Order total:</span> <span
                                class="lead fw-normal">$<fmt:formatNumber value="${cart.getTotalMoney()}" pattern="##.#"/></span>
                        </p>
                    </div>

                </div>
            </div>

            <div class="d-flex justify-content-end">
                <!--<button type="button" class="btn btn-light btn-lg me-2">Continue shopping</button>-->
                <div class="col-6 mb-40 d-flex justify-content-end  ">
                    <a href="<c:url value="/service/service.do"/>" class="btn btn-primary btn-lg me-2" role="button">Continue shopping</a>

                    <c:if test="${cart.items == null || user == null}"><a class="btn btn-primary btn-lg me-2">Check Out</a></c:if>
                    <c:if test="${cart.items != null && user != null}"><a href="<c:url value="/cart/cart-contact.do"/>" class="btn btn-primary btn-lg me-2">Check Out</a></c:if>
                    </div>
                </div>
                <p style="color:red">${noItem}</p>
            <p style="color:green">${msg}</p>   
        </div>
    </div>
</div>