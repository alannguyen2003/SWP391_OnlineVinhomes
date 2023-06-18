

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="cookie" value="${pageContext.request.cookies}"/>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                <div class="wrap d-md-flex">
                    <div class="text-wrap p-4 p-lg-5 text-center d-flex align-items-center order-md-last">
                        <div class ="text w-100" >
                            <h2 class="text-primary">Welcome to Brote Cleaning Service</h2>
                            <p class="text-white">Already have an account?</p>
                            <a href="<c:url value="/user/login.do"/>" class="btn btn-white btn-outline-white">Sign In</a>
                        </div>
                    </div>
                    <div class="login-wrap p-4 p-lg-5">
                        <div class="d-flex">
                            <div class="w-100">
                                <h3 class="mb-4">Sign Up</h3>
                            </div> 
                        </div>

                        <form action="<c:url value="/user/signup_handler.do" />" class="signin-form">
                            <div class="p-4">
                                <div class="form-group mb-3">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-envelope-fill"></i></div>
                                    <label class="label" style="color: #7b7d83;" for="email">EMAIL</label>
                                    <input type="email" class="form-control" name="email" placeholder="Email" value="" required>
                                </div>

                                <div class="form-group mb-3">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-key-fill"></i></div>
                                    <label class="label" style="color: #7b7d83;" for="password">PASSWORD</label>
                                    <input type="password" class="form-control" name="password" placeholder="Password" value="" required>
                                </div>
                                                  
                                <div class="form-group mb-3">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-person-vcard-fill"></i></div>
                                    <label class="label" style="color: #7b7d83;" for="name">NAME</label>
                                    <input type="text" class="form-control" name="name" placeholder="Name" value="" required>
                                </div>
                                
                                <div class="form-group mb-3">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-telephone-fill"></i></div>
                                    <label class="label" style="color: #7b7d83;" for="phone">PHONE</label>
                                    <input type="number" minlength="10" class="form-control" name="phone" placeholder="Phone" value="" required>
                                </div>
                                
                                <div class="form-group mb-3">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-building-fill"></i></div>
                                    <label class="label" style="color: #7b7d83;" for="bid">BLOCK ID</label>
                                    <br/>
                                    <select name="bid" class="w-100 form-control">
                                        <c:forEach var="bl" items="${blockList}">
                                            <option value="${bl.BID}">${bl.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                

                                <p style="color:red">${message}</p>
                                    
                                <button class="btn btn-primary text-center mt-2" name="op" value="submit_signup" type="submit">
                                    Sign Up
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                                
            </div>
        </div>
    </div>
</section>
