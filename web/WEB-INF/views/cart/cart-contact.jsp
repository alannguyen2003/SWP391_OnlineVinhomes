<%-- 
    Document   : cart-contact
    Created on : Jun 1, 2023, 7:27:35 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
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
        <!--Checkout Form s-->
        <form action="#" class="checkout-form">
            <div class="row row-50 mbn-40">
                <div class="col-lg-7">
                    <!--                     Billing Address -->
                    <div id="billing-form" class="mb-20">
                        <h4 class="checkout-title">Billing Address</h4>
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
                            <div class="col-md-6 col-12 mb-5">
                                <label>Address*</label>
                                <input type="text" placeholder="Address line 1">
                                <input type="text" placeholder="Address line 2">
                            </div>
                        </div>
                    </div>
                    <!--                     Shipping Address -->
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
                        <!--                         Cart Total -->
                        <div class="col-12 mb-40">
                            <h4 class="checkout-title">Cart Total</h4>
                            <div class="checkout-cart-total">
                                <h4>Product <span>Total</span></h4>
                                <ul>
                                    <li>Samsome Notebook Pro 5 X 01 <span>$295.00</span></li>
                                    <li>Aquet Drone  D 420 X 02 <span>$550.00</span></li>
                                    <li>Play Station X 22 X 01 <span>$295.00</span></li>
                                    <li>Roxxe Headphone Z 75 X 01 <span>$110.00</span></li>
                                </ul>
                                <p>Sub Total <span>$1250.00</span></p>
                                <p>Shipping Fee <span>$00.00</span></p>
                                <h4>Grand Total <span>$1250.00</span></h4>
                            </div>
                        </div>
                        <div class="col-12 mb-40" style="display: flex; justify-content: flex-end">
                            <button class="btn-primary mt-10 ">Place order</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section> 
<!--Page Section End -->