<%-- 
    Document   : cart-contact
    Created on : Jun 1, 2023, 7:27:35 AM
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
            <h2>Cart Contact</h2>
        </div>
    </div>
</section>
<!--Page Header End-->
<!-- Page Section Start 
--><section class="mt-50 mb-150">
    <div class="container">
        <!--Checkout Forms-->
        <form action="#" class="checkout-form">
            <div class="row row-50 mbn-40">
                <div class="col-lg-7">
                    <!--Billing Address -->
                    <div id="billing-form" class="mb-20">
                        <h4 class="checkout-title">Billing Address</h4>
                        <div class="row">
                            <div class="col-12 mb-5">
                                <label>Full Name*</label>
                                <input type="text" placeholder="Full Name" value="${sessionScope.user.name}">
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Email Address*</label>
                                <input type="email" placeholder="Email Address" value="${sessionScope.user.email}">
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Phone*</label>
                                <input type="text" placeholder="Phone number" value="${sessionScope.user.phone}">
                            </div>
                            <div class="col-md-12 col-12 mb-5">
                                <label>Address*</label>
                                <input type="text" placeholder="Address line 1" value="${sessionScope.user.room}">
                            </div>
                            <div class="col-md-12 col-12 mb-5">
                                <label>Note</label>
                                <input type="text" placeholder="Resident Note">
                            </div>
                        </div>
                    </div>
                    <!--Shipping Address -->
                    <div id="shipping-form" class="mb-20">
                        <h4 class="checkout-title">Shipping Address</h4>
                        <div class="row">
                            <div class="col-12 mb-5">
                                <label>Full Name*</label>
                                <input type="text" placeholder="Full Name">
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Email Address*</label>
                                <input type="email" placeholder="Email Address">
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Phone no*</label>
                                <input type="text" placeholder="Phone number">
                            </div>
                            <div class="col-12 mb-5">
                                <label>Company Name</label>
                                <input type="text" placeholder="Company Name">
                            </div>
                            <div class="col-12 mb-5">
                                <label>Address*</label>
                                <input type="text" placeholder="Address line 1">
                                <input type="text" placeholder="Address line 2">
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Country*</label>
                                <select class="nice-select">
                                    <option>Bangladesh</option>
                                    <option>China</option>
                                    <option>country</option>
                                    <option>India</option>
                                    <option>Japan</option>
                                </select>
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Town/City*</label>
                                <input type="text" placeholder="Town/City">
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>State*</label>
                                <input type="text" placeholder="State">
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Zip Code*</label>
                                <input type="text" placeholder="Zip Code">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="row">
                        <!--Cart Total -->
                        <c:forEach var="item" items="${sessionScope.cart.items}">
                        <div class="col-12 mb-40">
                            <h4 class="checkout-title">Cart Total</h4>
                            <div class="checkout-cart-total">
                                <h4>Product <span>Total</span></h4>
                                <ul>
                                    <li>${item.service.name}<span>$${item.service.getLowerPrice()}</span></li>
                                </ul>
                                <p>Sub Total <span>$<fmt:formatNumber value="${cart.getTotalMoney()}" pattern="##.#"/></span></p>
                                <p>Shipping Fee <span>$00.0</span></p>
                                <h4>Grand Total <span>$<fmt:formatNumber value="${cart.getTotalMoney()}" pattern="##.#"/></span></h4>
                            </div>
                        </div>
                        </c:forEach>
                        <div class="col-6 mb-40" style="display: flex; justify-content: flex-start">
                            <a href="<c:url value="/cart/cart.do" />" class="btn btn-primary mt-10">Cancel</a>
                        </div>
                        <div class="col-6 mb-40" style="display: flex; justify-content: flex-end">
                            <a class="btn btn-primary mt-10">Place order</a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section> 
<!--Page Section End -->