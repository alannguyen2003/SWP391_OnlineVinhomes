
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="custom-cursor__cursor"></div>
<div class="custom-cursor__cursor-two"></div>

<div class="stricky-header stricked-menu main-menu">
    <div class="sticky-header__content"></div><!-- /.sticky-header__content -->
</div><!-- /.stricky-header -->

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
                <li>Order</li>
            </ul>
            <h2>My Orders</h2>
        </div>
    </div>
</section>
<!--Page Header End-->

<!--Contact Page Two Start-->
<section class="contact-page-two">
    <div class="container">
        <div class="row">
            <div class="col-xl-4 col-lg-5">
                <div class="contact-page-two__left">
                    <div class="section-title text-left">
                        <span class="section-title__tagline">My Orders</span>
                        <h2 class="section-title__title">Order List</h2>
                    </div>
                    <ul class="list-unstyled contact-page-two__info">
                        <li>
                            <div class="icon">
                                <i style="font-size: 30px; color: white;" class="bi bi-person"></i>
                            </div>
                            <div class="text">
                                <h5><a href="<c:url value="/home/index.do"/>">My Profile</a></h5>
                            </div>
                        </li>
                        <li>
                            <div class="icon">
                                <i style="font-size: 30px; color: white;" class="bi bi-clipboard-fill"></i>
                            </div>
                            <div class="text">
                                <h5><a href="<c:url value="/order/myorder.do"/>">Orders</a></h5>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-xl-8 col-lg-7">
                <div class="contact-page-two__right">
                    <div class="col-xl-12">
                        <div class="card-body">
                            <div class="d-flex align-items-center">
                                <div class="col"><strong>ID</strong></div>
                                <div class="col"><strong>Date</strong></div>
                                <div class="col"><strong>Delivery Time</strong></div>
                                <div class="col"><strong>Status</strong></div>
                                <div class="col"><strong>Price</strong></div>
                            </div>
                        </div>
                        <c:forEach var="fo" items="${myOrderlist}" varStatus="loop">
                            <div class="card-body">
                                <!-- OrderHeaders Information -->
                                <div class="order-header">
                                    <div class="d-flex align-items-center">
                                        <div class="col">${loop.count}</div>
                                        <div class="col"><fmt:formatDate value="${fo.oh.date}" pattern="dd/MM/yyyy HH:mm:ss" /></div>
                                        <div class="col"><fmt:formatDate value="${fo.oh.delivery_time}" pattern="dd/MM/yyyy HH:mm:ss" /></div>
                                        <c:if test="${fo.oh.status == 'Pending'}">
                                            <div class="col"><span class="bage bage-warning">${fo.oh.status}</span></div>
                                            </c:if>
                                            <c:if test="${fo.oh.status != 'Pending'}">
                                            <c:if test="${fo.oh.status == 'Completed'}">
                                            <div class="col"><span class="bage bage-success">${fo.oh.status}</span></div>
                                            </c:if>
                                            <c:if test="${fo.oh.status == 'Failed'}">
                                            <div class="col"><span class="bage bage-danger">${fo.oh.status}</span></div>
                                            </c:if>
                                            <c:if test="${fo.oh.status == 'Cancel'}">
                                            <div class="col"><span class="bage bage-cancel">${fo.oh.status}</span></div>
                                            </c:if>
                                            </c:if>
                                            <c:if test="${fo.total != 0}">
                                            <div class="col"><strong><fmt:formatNumber value="${fo.total}" type="currency" /></strong></div>
                                                </c:if>
                                        <div class="toggle-button" data-toggle="collapse1" data-target="#demo-${fo.oh.id}"><i class="fa fa-chevron-down"></i></div>
                                    </div>
                                </div>
                                <!-- OrderHeaders Information -->

                                <!-- OrderDetails Information Table -->
                                <div id="demo-${fo.oh.id}" class="collapse shop__cart__table p-3 mb-0">
                                    <hr class="mt-0 mb-0" />
                                    <table class="table-responsive mb-0">
                                        <thead>
                                            <tr>
                                                <th colspan="1">Service</th>
                                                <th colspan="3" style="padding-left: 25rem">Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- Nội dung OrderDetail -->
                                            <c:forEach var="od" items="${fo.od}">
                                                <tr>
                                                    <td class="cart__product__item">
                                                        <%-- img src="<c:url value="/assets/img/product/product-${od.key.serviceId}_2.jpg" />"  alt="">--%>
                                                        <div class="cart__product__item__title">
                                                            <h6 style="font-size: 19px; height: 100%">${od.value}</h6>
                                                        </div>
                                                    </td>
                                                    <c:if test="${od.key.price != 0}">
                                                        <td style="padding-left: 25rem" class="cart__price"><fmt:formatNumber value="${od.key.price}" type="currency" /></td>
                                                    </c:if>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div style="text-align: right; margin: 10px 10px;">
                                        <a class="btn btn-outline-primary" style="font-weight: bold; background-color: #dc3545; color: #fff; border-color: #fff; border-radius: .5rem;" href="<c:url value="/order/cancel-order.do?oid=${fo.oh.id}&aid=${user.AID}"/>"> Cancel <i class="bi bi-x-square"></i></a>
                                    </div>
                                     <p style="color:red">${message}</p>
                                    <div class="card-footer">Note: ${fo.oh.note} </div>
                                </div>
                                <!-- OrderDetails Information Table -->
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--Contact Page Two End-->

<a href="#" data-target="html" class="scroll-to-target scroll-to-top"><i class="fa fa-angle-up"></i></a>

