

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
                            <h2 class="text-primary">Welcome to Log In</h2>
                            <p class="text-white">Is a new member?</p>
                            <a href="<c:url value="/user/signup.do"/>" class="btn btn-white btn-outline-white">Sign Up</a>
                        </div>
                    </div>
                    <div class="login-wrap p-4 p-lg-5">
                        <div class="d-flex">
                            <div class="w-100">
                                <h3 class="mb-4">Sign In</h3>
                            </div> 
                        </div>

                        <form action="<c:url value="/user/login_handler.do" />" class="signin-form" method ="post">
                            <div class="p-4">
                                <div class="form-group mb-3">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-person-vcard-fill"></i></div>
                                    <label class="label" style="color: #7b7d83;" for="email">EMAIL</label>
                                    <input type="email" class="form-control" name="email" placeholder="Email" value="${cookie.cEmail.value}" required>
                                </div>

                                <div class="form-group mb-3">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-key-fill"></i></div>
                                    <label class="label" style="color: #7b7d83;" for="password">PASSWORD</label>
                                    <input type="password" class="form-control" name="password" placeholder="Password" value="${cookie.cPass.value}" required>
                                </div>

                                <p style="color:red">${message}</p>
                                
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" name="rem" id="flexCheckDefault" ${cookie.cRem.value!=null?"checked":""}>
                                    <label class="form-check-label" for="flexCheckDefault">Remember Me </label>
                                </div>
                                    
                                <button class="btn btn-primary text-center mt-2" name="op" value="submit_login" type="submit">
                                    Log In
                                </button>
                                <p class="text-center mb-5">
                                    <label for="forgot-pass">Forgot Password?</label>
                                    <a class="forgot-pass-link" href="<c:url value="/reset-pass/reset-pass.do"/>" style="text-decoration: none">Reset Password</a>
                                </p>

                            </div>
                        </form>
                    </div>
                </div>
                                
            </div>
        </div>
    </div>
</section>
