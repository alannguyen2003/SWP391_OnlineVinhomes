<%-- 
    Document   : resident-detail
    Created on : Jun 4, 2023, 6:39:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>Resident Detail</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Detail</li>
            <li class="breadcrumb-item active">Resident Detail</li>
        </ol>
    </nav>
</div>

<div class="col-xl-8">
    <div class="card shadow mb-4">
        <div class="card-body mt-4">
            <div class="card-header py-3 mb-3">
                <h6 class="m-0 font-weight-bold text-primary">Resident Detail</h6>
            </div>
            <!-- Account details card-->

            <form action="<c:url value="/admin/updateResident.do" />">
                <div class="row mb-3">
                    <label for="company" class="col-md-4 col-lg-3 col-form-label">ID</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="AID" type="hidden" class="form-control" id="AID" value="${u.AID}">
                        <input name="AID" type="text" class="form-control" id="AID" value="${u.AID}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Full Name</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="name" type="hidden" class="form-control" id="name" value="${u.name}">
                        <input name="name" type="text" class="form-control" id="name" value="${u.name}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="email" type="hidden" class="form-control" id="Email" value="${u.email}">
                        <input name="email" type="email" class="form-control" id="Email" value="${u.email}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Phone</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="phone" type="hidden" class="form-control" id="Phone" value="${u.phone}">
                        <input name="phone" type="text" class="form-control" id="Phone" value="${u.phone}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Address" class="col-md-4 col-lg-3 col-form-label">Room</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="room" type="text" class="form-control" id="Address" value="${u.room}">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Address" class="col-md-4 col-lg-3 col-form-label">Block ID</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="BID" type="text" class="form-control" id="Address" value="${u.BID}">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Address" class="col-md-4 col-lg-3 col-form-label">Status</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="status" type="text" class="form-control" id="Address" value="${u.status}">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6" style="color: green;">${message}</div>
                    <div class="col-md-6 d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary">Save change</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
