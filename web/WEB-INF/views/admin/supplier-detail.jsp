<%-- 
    Document   : resident-detail
    Created on : Jun 4, 2023, 6:39:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>User Detail</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Table</li>
            <li class="breadcrumb-item active">User Detail</li>
        </ol>
    </nav>
</div>

<div class="col-xl-8">
    <div class="card shadow mb-4">
        <div class="card-body mt-4">
            <div class="card-header py-3 mb-3">
                <h6 class="m-0 font-weight-bold text-primary">User Detail</h6>
            </div>
            <!-- Account details card-->

            <form action="#>">
                <div class="row mb-3">
                    <label for="company" class="col-md-4 col-lg-3 col-form-label">ID</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="AID" type="hidden" class="form-control" id="AID" value="${u.id}">
                        <input name="AID" type="text" class="form-control" id="AID" value="${u.id}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Supplier Name</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="" type="hidden" class="form-control" id="name" value="${u.name}">
                        <input name="" type="text" class="form-control" id="name" value="${u.name}" disabled="">
                    </div>
                </div>
                    
                <div class="row mb-3">
                    <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="" type="hidden" class="form-control" id="Email" value="${u.email}">
                        <input name="" type="email" class="form-control" id="Email" value="${u.email}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Phone</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="" type="hidden" class="form-control" id="Phone" value="${u.phone}">
                        <input name="" type="text" class="form-control" id="Phone" value="${u.phone}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Address" class="col-md-4 col-lg-3 col-form-label">Address</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="room" type="text" class="form-control" id="Address" value="${u.address}">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6" style="color: green;">${message}</div>
                    <div class="col-md-6 d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#updateResourceModal">Save change</button>
                        <input id="updateResident" type="submit" name="op" value="" hidden>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to update this resident information?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Update" below if you are ready to update this resident.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a id="updateResidentLink" onclick="updateUser()" class="btn btn-primary"/>Update</a>
            </div>
        </div>
    </div>
</div>   
<script>
    function updateUser() {
        var form = document.getElementById("updateUserForm");
        var submitOp = document.getElementById("updateUser");
        submitOp.value = 'update';
        submitOp.click();
    }
</script>