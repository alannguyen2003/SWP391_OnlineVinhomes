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
                                <input type="text" placeholder="Full Name" value="${sessionScope.user.name}" disabled>
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Email Address*</label>
                                <input type="email" placeholder="Email Address" value="${sessionScope.user.email}" disabled>
                            </div>
                            <div class="col-md-6 col-12 mb-5">
                                <label>Phone*</label>
                                <input type="text" placeholder="Phone number" value="${sessionScope.user.phone}" disabled>
                            </div>
                            <div class="col-md-12 col-12 mb-5">
                                <label>Address*</label>
                                <input type="text" placeholder="Address line 1" value="${sessionScope.user.room}" disabled>
                            </div>
                            <div class="col-md-12 col-12 mb-5">
                                <label>Delivery time*</label>
                                <input type="datetime-local" placeholder="Address line 1">
                            </div>
                            <div class="col-md-12 col-12 mb-5">
                                <label>Note</label>
                                <input type="text" name="note" placeholder="Resident Note">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="row">
                        <!--Cart Total -->
                        <div class="col-12 mb-40">
                            <h4 class="checkout-title">Cart Total</h4>
                            <div class="checkout-cart-total">
                                <h4>Product <span>Total (estimate)</span></h4>
                                <c:forEach var="item" items="${sessionScope.cart.items}">
                                    <ul>
                                        <li style="padding-top: 15px">${item.service.name}<span>$<fmt:formatNumber value="${(item.service.getLowerPrice() + item.service.getUpperPrice()) / 2}" pattern="##.#"/></span></li>
                                    </ul>
                                </c:forEach>
                                <p>Sub Total <span>$<fmt:formatNumber value="${cart.getTotalMoney()}" pattern="##.#"/></span></p>
                                <h4>Grand Total <span>$<fmt:formatNumber value="${cart.getTotalMoney()}" pattern="##.#"/></span></h4>
                            </div>
                        </div>
                        <div class="col-6 mb-40" style="display: flex; justify-content: flex-start">
                            <a href="<c:url value="/cart/cart.do" />" class="btn btn-primary mt-10">Cancel</a>
                        </div>
                        <div class="col-6 mb-40" style="display: flex; justify-content: flex-end">
                            <a class="btn btn-primary mt-10" data-toggle="modal" data-target="#cartCompletion"/>Place order</a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section> 
<!--Page Section End -->

<div class="modal fade" id="cartCompletion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to order?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Yes" below if you are ready to place your order.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">No</button>
                <a class="btn btn-success" href="<c:url value="/cart/cart-completion.do"/>">Yes</a>
            </div>
        </div>
    </div>
</div>   