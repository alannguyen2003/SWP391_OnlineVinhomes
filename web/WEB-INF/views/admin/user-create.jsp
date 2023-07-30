<%-- 
    Document   : create-serivce
    Created on : Jun 8, 2023, 9:44:58 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<h1>Create Service</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Forms</li>
            <li class="breadcrumb-item active">Create Service</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Create Service</h5>
                    <!-- General Form Elements -->
                    <form action="<c:url value="/admin/create.do" />" class="signin-form" method="post">
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
                        </form><!-- End General Form Elements -->

                </div>
            </div>

        </div>

    </div>
</section>
