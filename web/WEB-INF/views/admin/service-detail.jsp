<%-- 
    Document   : service-detail
    Created on : Jun 7, 2023, 9:23:50 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>Service Detail</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Detail</li>
            <li class="breadcrumb-item active">Service Detail</li>
        </ol>
    </nav>
</div>

<div class="col-xl-8">
    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="pagetitle">
                <h2 class="ml-3 mt-3 mb-4">Service Detail</h2>
            </div>
            <!-- Services details card-->

            <form id="updateServiceForm" action="<c:url value="/admin/updateService.do" />" method="">
                <div class="row mb-3">
                    <label for="company" class="col-md-4 col-lg-3 col-form-label">ServiceID</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="service_id" type="hidden" class="form-control" id="service_id" value="${se.serviceID}">
                        <input name="service_id" type="text" class="form-control" id="service_id" value="${se.serviceID}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Service Name</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="name" type="text" class="form-control" id="name" value="${se.name}">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="description" class="col-md-4 col-lg-3 col-form-label">Description</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="description" type="text" class="form-control" id="description" value="${se.description}">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="lowerPrice" class="col-md-4 col-lg-3 col-form-label">Lower Price</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="lowerPrice" type="number" step="0.1" class="form-control" id="lowerPrice" value="${se.lowerPrice}">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="upperPrice" class="col-md-4 col-lg-3 col-form-label">Upper Price</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="upperPrice" type="number" step="0.1" class="form-control" id="upperPrice" value="${se.upperPrice}">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="rated" class="col-md-4 col-lg-3 col-form-label">Rated Star</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="rated" type="text" class="form-control" id="rated" value="${se.rated}">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="categoryName" class="col-md-4 col-lg-3 col-form-label">Category Name</label>
                    <div class="col-md-8 col-lg-9">
                        <select name="categoryID" class="w-100 form-control">
                            <c:forEach var="cl" items="${categoryList}">
                                <option value="${cl.id}" ${categoryId == cl.id ? 'selected' : ''}>${cl.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
<!--                <input name="categoryID" type="number" class="form-control" id="categoryId" value="${se.categoryID}" hidden>-->
                <div class="row mb-3">
                    <div class="col-md-6" style="color: green;">${message}</div>
                    <div class="col-md-6 d-flex justify-content-end">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateServiceModal">Save change</button>
                        <input id="updateService" type="submit" name="op" value="" hidden>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="updateServiceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to update this service information?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Update" below if you are ready to update this service.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a id="updateServiceLink" onclick="updateService()" class="btn btn-primary"/>Update</a>
            </div>
        </div>
    </div>
</div>   
<script>
    function updateService() {
        var form = document.getElementById("updateServiceForm");
        var submitOp = document.getElementById("updateService");
        submitOp.value = 'update';
        submitOp.click();
    }
</script>
