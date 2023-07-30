<%-- 
    Document   : account-create
    Created on : Jun 4, 2023, 1:51:00 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
    <h1>Create Manager Account</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Forms</li>
            <li class="breadcrumb-item active">Create</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Create Manager Account</h5>
                    <!-- General Form Elements -->
                    <form action="<c:url value="/admin/accountCreate.do"/>" method="post">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="name" value="">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Email</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="email" value = "" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Password</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password" value = "" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="phone" value = "" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputNumber" class="col-sm-2 col-form-label">Block ID</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="blockId" value = "">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputNumber" class="col-sm-2 col-form-label">Role ID</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="roleId" value = "">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-12 d-flex justify-content-end">
                                <button name="op" value="create" type="submit" class="btn btn-primary" style="margin-right: 10px">Create</button>
                                <button name="op" value="cancel" type="submit" class="btn btn-primary">Cancel</button>
                            </div>
                        </div>

                    </form><!-- End General Form Elements -->

                </div>
            </div>

        </div>

    </div>
</section>
