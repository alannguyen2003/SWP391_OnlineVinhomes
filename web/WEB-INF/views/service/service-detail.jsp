<%-- 
    Document   : service-detail
    Created on : May 26, 2023, 8:54:04 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            .star-rating {
                margin: 25px 0 0px;
                font-size: 0;
                white-space: nowrap;
                display: inline-block;
                width: 100px;
                height: 20px;
                overflow: hidden;
                position: relative;
                background: url('data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMjBweCIgaGVpZ2h0PSIyMHB4IiB2aWV3Qm94PSIwIDAgMjAgMjAiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDIwIDIwIiB4bWw6c3BhY2U9InByZXNlcnZlIj48cG9seWdvbiBmaWxsPSIjREREREREIiBwb2ludHM9IjEwLDAgMTMuMDksNi41ODMgMjAsNy42MzkgMTUsMTIuNzY0IDE2LjE4LDIwIDEwLDE2LjU4MyAzLjgyLDIwIDUsMTIuNzY0IDAsNy42MzkgNi45MSw2LjU4MyAiLz48L3N2Zz4=');
                background-size: contain;
            }
            .star-rating i {
                opacity: 0;
                position: absolute;
                left: 0;
                top: 0;
                height: 100%;
                width: 20%;
                z-index: 1;
                background: url('data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMjBweCIgaGVpZ2h0PSIyMHB4IiB2aWV3Qm94PSIwIDAgMjAgMjAiIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDIwIDIwIiB4bWw6c3BhY2U9InByZXNlcnZlIj48cG9seWdvbiBmaWxsPSIjRkZERjg4IiBwb2ludHM9IjEwLDAgMTMuMDksNi41ODMgMjAsNy42MzkgMTUsMTIuNzY0IDE2LjE4LDIwIDEwLDE2LjU4MyAzLjgyLDIwIDUsMTIuNzY0IDAsNy42MzkgNi45MSw2LjU4MyAiLz48L3N2Zz4=');
                background-size: contain;
            }
            .star-rating input {
                -moz-appearance: none;
                -webkit-appearance: none;
                opacity: 0;
                display: inline-block;
                width: 20%;
                height: 100%;
                margin: 0;
                padding: 0;
                z-index: 2;
                position: relative;
            }
            .star-rating input:hover + i,
            .star-rating input:checked + i {
                opacity: 1;
            }
            .star-rating i ~ i {
                width: 40%;
            }
            .star-rating i ~ i ~ i {
                width: 60%;
            }
            .star-rating i ~ i ~ i ~ i {
                width: 80%;
            }
            .star-rating i ~ i ~ i ~ i ~ i {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <!-- Detail -->
        <!--Service Details Start-->
        <section class="service-details">
            <div class="container">
                <div class="row">
                    <div class="col-xl-4 col-lg-5">
                        <div class="service-details__left">
                            <div class="service-details__category">
                                <h4 class="service-details__category-title">Services</h4>
                                <ul class="service-details__category-list list-unstyled">
                                    <c:if test="${requestScope.service.categoryID == 1}">
                                        <li class="active"><a href="<c:url value="/service/service-list.do?id=1" />">Cleaning<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>
                                            <c:if test="${requestScope.service.categoryID != 1}">
                                        <li><a href="<c:url value="/service/service-list.do?id=1" />">Cleaning<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>
                                            <c:if test="${requestScope.service.categoryID == 2}">
                                        <li class="active"><a href="<c:url value="/service/service-list.do?id=2" />">Maintenance<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>
                                            <c:if test="${requestScope.service.categoryID != 2}">
                                        <li><a href="<c:url value="/service/service-list.do?id=2" />">Maintenance<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>

                                    <c:if test="${requestScope.service.categoryID == 3}">
                                        <li class="active"><a href="<c:url value="/service/service-list.do?id=3" />">Security<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>
                                            <c:if test="${requestScope.service.categoryID != 3}">
                                        <li><a href="<c:url value="/service/service-list.do?id=3" />">Security<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>

                                    <c:if test="${requestScope.service.categoryID == 4}">
                                        <li class="active"><a href="<c:url value="/service/service-list.do?id=4" />">Pest Control<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>
                                            <c:if test="${requestScope.service.categoryID != 4}">
                                        <li><a href="<c:url value="/service/service-list.do?id=4" />">Pest Control<span
                                                    class="fa fa-angle-right"></span></a></li>
                                            </c:if>

                                </ul>
                            </div>
                            <div class="service-details__need-help">
                                <div class="service-details__need-help-bg"
                                     style="background-image: url(<c:url value="/assets/images/backgrounds/service-details-need-help-bg.jpg" />)">
                                </div>
                                <div class="service-details__need-help-icon">
                                    <span class="icon-phone-call"></span>
                                </div>
                                <h2 class="service-details__need-help-title">Best Solution <br> to Your House <br> &
                                    Office Cleaning</h2>
                                <div class="service-details__need-help-contact">
                                    <p>Call anytime</p>
                                    <a href="tel:2300068603"> +23 (000) 68 603</a>
                                </div>
                            </div>
                            <div class="service-details__download">
                                <a href="<c:url value="/cart/addToCart.do?id=${service.serviceID}" />" class="thm-btn service-details__btn">Book a service
                                    <i class="fa fa-angle-right"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8 col-lg-7">
                        <div class="service-details__right">
                            <div class="service-details__img">
                                <img src="<c:url value="/assets/images/services/services-details-img-4.jpg" />" alt="">
                            </div>

                            <div class="service-details__content">
                                <h3 class="service-details__content-title">${service.name}</h3>
                                <p class="service-details__text-2">${service.lowerPrice} - ${service.upperPrice} $</p>
                                <p class="service-details__text-3">${service.description}</p>

                            </div>
                            <div class="service-details__points-box">
                                <ul class="list-unstyled service-details__points">
                                    <li>
                                        <div class="service-details__points-icon">
                                            <span class="icon-household"></span>
                                        </div>
                                        <div class="service-details__points-content">
                                            <p>Mang đến cho bạn dịch vụ tốt nhất</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="service-details__points-icon">
                                            <span class="icon-broom"></span>
                                        </div>
                                        <div class="service-details__points-content">
                                            <p>Tận tâm <br> Tận tụy<br> Tận phòng.</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--Service Details End-->
        <!-- Feedback -->
        <section style="background-color: #f7f6f6;margin-bottom: 20%">
            <div class="container my-5 py-5 text-dark">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-12 col-lg-10 col-xl-8">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <h4 class="text-dark mb-0">Feedbacks (<span>${noFeedbacks}</span>)</h4>                   
                        </div>
                        <form action="<c:url value="/service/addFeedback.do"/>" id = "cmtForm">
                            <input type="number" hidden name="UID" value="${user.AID}">
                            <input type="text" hidden name="name" value="${user.name}">
                            <input type="text" hidden name="contact" value="${user.phone}">
                            <input type="text" hidden name="email" value="${user.email}">
                            <input type="number" hidden name="serviceID" value="${service.serviceID}">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="d-flex flex-start">
                                        <div class="w-100">
                                            <div class="d-flex justify-content-between align-items-center mb-3">                                    
                                                <textarea rows="4" class="w-100" name="message" form="cmtForm" placeholder="  Share your thoughts about the service"></textarea>
                                            </div>
                                            ${message}
                                            <h4 class="text-dark">
                                                Vote from 1 to 5 for this service
                                            </h4>
                                            <span class="star-rating">
                                                <input type="radio" name="rated" value="1"><i></i>
                                                <input type="radio" name="rated" value="2"><i></i>
                                                <input type="radio" name="rated" value="3"><i></i>
                                                <input type="radio" name="rated" value="4"><i></i>
                                                <input type="radio" name="rated" value="5"><i></i>
                                            </span>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <p class="small mb-0" style="color: #1D38A5;">
                                                    <button type="submit" class="btn btn-outline-primary">Post</button>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </form>

                        <hr>
                        <c:forEach var= "fb" items="${feedbacks}">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="d-flex flex-start">
                                        <img class="rounded-circle shadow-1-strong me-3"
                                             src="<c:url value="/img/user.png"/>" alt="avatar" width="40"
                                             height="40" />
                                        <div class="w-100">
                                            <div class="d-flex justify-content-between align-items-center mb-3">
                                                <h6 class="text-primary fw-bold mb-0">
                                                    ${fb.name}
                                                    <span class="text-dark ms-2">${fb.message}</span>
                                                </h6>
                                            </div>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <p class="small mb-0" style="color: #aaa;">
                                                    <a href="#!" class="link-grey">${fb.email}</a>
                                                </p>
                                                <div class="d-flex flex-row">
                                                    <c:forEach begin="1" end="${fb.rated}">
                                                        <i class="fas fa-star text-warning me-2"></i>
                                                    </c:forEach>
                                                    <c:forEach begin="${fb.rated + 1}" end="5">
                                                        <i class="far fa-star text-warning me-2"></i>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </c:forEach>                                
                    </div>
                </div>
            </div>
        </section>               
    </body>
</html>
