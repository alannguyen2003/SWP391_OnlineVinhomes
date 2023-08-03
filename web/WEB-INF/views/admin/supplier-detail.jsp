<%-- 
    Document   : resident-detail
    Created on : Jun 4, 2023, 6:39:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>Supplier Detail</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Table</li>
            <li class="breadcrumb-item active">Supplier Detail</li>
        </ol>
    </nav>
</div>

<div class="col-xl-8">
    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="pagetitle">
                <h2 class="ml-3 mt-3 mb-4">Supplier Detail</h2>
            </div>
            <!-- Account details card-->

            <form id="updateSupplierForm" action="<c:url value="/admin/updateSupplier.do" />" method="post">
                <div class="row mb-3">
                    <label for="company" class="col-md-4 col-lg-3 col-form-label">ID</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="SID" type="hidden" class="form-control" id="SID" value="${u.id}">
                        <input name="SID" type="text" class="form-control" id="SID" value="${u.id}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Supplier Name</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="name" type="hidden" class="form-control" id="name" value="${u.name}">
                        <input name="name" type="text" class="form-control" id="name" value="${u.name}" disabled="">
                    </div>
                </div>
                    
                <div class="row mb-3">
                    <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="email" type="hidden" class="form-control" id="email" value="${u.email}">
                        <input name="email" type="email" class="form-control" id="email" value="${u.email}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Phone</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="phone" type="hidden" class="form-control" id="phone" value="${u.phone}">
                        <input name="phone" type="text" class="form-control" id="phone" value="${u.phone}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Address" class="col-md-4 col-lg-3 col-form-label">Address</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="address" type="text" class="form-control" id="address" value="${u.address}">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6" style="color: green;">${message}</div>
                    <div class="col-md-6 d-flex justify-content-end">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateSupplierModal">Save change</button>
                        <input id="updateSupplier" type="submit" name="op" value="" hidden>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="updateSupplierModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to update this supplier information?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Update" below if you are ready to update this supplier.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a id="updateSupplierLink" onclick="updateSupplier()" class="btn btn-primary">Update</a>
            </div>
        </div>
    </div>
</div>   
<script>
    function updateSupplier() {
        var form = document.getElementById("updateSupplierForm");
        var submitOp = document.getElementById("updateSupplier");
        submitOp.value = 'update';
        form.submit();
    }
</script>
