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
                    <form action="<c:url value="serviceCreate.do"/>" method="post">
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label">Service Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="name" value="">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Description</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="description" value = "" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Lower Price</label>
                            <div class="col-sm-10">
                                <input type="number" step="0.5" class="form-control" name="lowerPrice" value = "" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label">Upper Price</label>
                            <div class="col-sm-10">
                                <input type="number" step="0.5" class="form-control" name="upperPrice" value = "" >
                            </div>
                        </div>
<!--                        <div class="row mb-3">
                            <label for="inputNumber" class="col-sm-2 col-form-label">Supplier ID</label>
                            <div class="col-sm-10">
                                <select class="form-select" aria-label="Default select example" name="supplierID" id="filterSupplierValue">
                                     Các option của combobox supplier 
                                    <c:forEach var="sl" items="${supplierList}">
                                        <option value="${sl.id}" ${sl.id == filterValue ? "selected" : ""}>${sl.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>-->
                        <div class="row mb-3">
                            <label for="inputNumber" class="col-sm-2 col-form-label">Category ID</label>
                            <div class="col-sm-10">
                                <select class="form-select" aria-label="Default select example" name="categoryID" id="filterCategoryValue">
                                    <!-- Các option của combobox category -->
                                    <c:forEach var="cl" items="${categoryList}">
                                        <option value="${cl.id}">${cl.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <input type="number" hidden="" class="form-control" name="rated" value = "0">
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
