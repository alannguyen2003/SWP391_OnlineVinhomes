<%-- 
    Document   : cart
    Created on : May 28, 2023, 10:20:33 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var= "size" value ="${sessionScope.size}"/>
<div class="container h-100" style="margin-bottom: 20%">
    <div class="row d-flex justify-content-center align-items-center h-100 ">
        <div class="col">
            <p class="" style="margin-bottom: 10px"><span class="h2">Shopping Cart </span><span class="h4">(${size} item in your cart)</span></p>
            <div class="card mb-4">
                <div class="card-body p-4 ">
                    <c:forEach var="item" items="${sessionScope.cart.items}" varStatus="loop">
                        <div class="row align-items-center">
                            <div  style="animation-delay: 0.5s"class="col-md-2">
                                <img src="" class="img-fluid " alt="Generic placeholder image">
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Name</p>
                                    <p class="lead fw-normal mb-0">${item.service.name}</p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div style="padding-top: 10px">
                                    <p class="lead fw-normal mb-0">
                                        <a href="<c:url value="/cart/subtractFromCart.do?id=${item.service.DID}"/>">-</a>
                                        |
                                        <a href="<c:url value="/cart/addFromCart.do?id=${item.service.DID}"/>"> +</a>
                                    </p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div >
                                    <p class="small text-muted mb-4 pb-2">Price</p>
                                    <p class="lead fw-normal mb-0">${item.service.getLowerPrice()}</p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Total</p>
                                    <p class="lead fw-normal mb-0">$<fmt:formatNumber value="${item.product.getLowerPrice()}" pattern="##.#"/></p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div>
                                    <a class="text-body" href="<c:url value="/cart/removeFromCart.do?id=${item.service.DID}"/>">Remove</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="card mb-5">
                <div class="card-body p-4">

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
                <a href="<c:url value="/service/service.do"/>" class="btn btn-light btn-lg me-2" role="button">Continue shopping</a>
                <button class="btn btn-primary" type='button' data-toggle="modal" data-target="#checkOutModal">Check Out</button>
            </div>
            <p style="color:red">${noItem}</p>
            <p style="color:green">${msg}</p>   
        </div>
    </div>
</div>