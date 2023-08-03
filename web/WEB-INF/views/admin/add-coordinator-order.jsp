<%-- 
    Document   : resident-detail
    Created on : Jun 4, 2023, 6:39:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>Order Details</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Detail</li>
            <li class="breadcrumb-item active">Order Details</li>
        </ol>
    </nav>
</div>

<div class="col-xl-8">
    <div class="card shadow mb-4">
        <div class="card-body mt-4" style="margin-bottom: 2rem">
            <ul class="sub-nav" style="margin-bottom: 2rem;" activeindex="2">
                <li class="sub-nav__item" label="Billing"><a href="<c:url value='/admin/add-employee-order.do?OID=${OID}' />" class="${activation == 'add-coordinator-order' ? 'active' : ''}"><i class="bi bi-person-fill-add"></i><span>Add Coordinator</span></a></li>
            </ul>


            <form id="updateOrderForm" action="<c:url value="/admin/updateOrderPending.do" />" method="post">
                <div class="row mb-3">
                    <label for="OID" class="col-md-4 col-lg-3 col-form-label">Order ID</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="OID" type="hidden" class="form-control" id="AID" value="${oh.id}">
                        <input name="OID" type="text" class="form-control" id="AID" value="${oh.id}" disabled>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Date" class="col-md-4 col-lg-3 col-form-label">Date</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="date" type="hidden" class="form-control" id="date" value="${oh.date}">
                        <input name="date" type="text" class="form-control" id="date" value="${oh.date}" disabled="">
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="ResidentID" class="col-md-4 col-lg-3 col-form-label">Resident ID</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="residentId" type="hidden" class="form-control" id="Phone" value="${oh.residentId}">
                        <input name="residentId" type="text" class="form-control" id="Phone" value="${oh.residentId}" disabled="">
                    </div>
                </div>


                <div class="row mb-3">
                    <label for="Status" class="col-md-4 col-lg-3 col-form-label">Status</label>
                    <div class="col-md-8 col-lg-9">
                        <select name="status" class="w-100 form-control"> 
                            <c:forEach var="status" items="${statusList}">
                                <option value="${status}" ${status == oh.status ? "selected" : ""}>${status}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Coordinator" class="col-md-4 col-lg-3 col-form-label">Coordinator</label>
                    <div class="col-md-8 col-lg-9">
                        <select name="coorId" class="w-100 form-control">
                            <option value="">-- Select Coordinator --</option>
                            <c:forEach var="coorList" items="${coorList}">
                                <option name="coorId" value="${coorList.CID}">${coorList.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="Note" class="col-md-4 col-lg-3 col-form-label">Note</label>
                    <div class="col-md-8 col-lg-9">
                        <input name="note" type="text" class="form-control" id="Note" value="${oh.note}">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6" style="color: green;">${message}</div>
                    <div class="col-md-6 d-flex justify-content-end">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateOrderModal">Save change</button>
                        <input id="updateOrder" type="submit" name="op" value="update" hidden>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="updateOrderModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to update this Order information?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Update" below if you are ready to update this Order.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a id="updateOrderLink" onclick="updateOrder()" class="btn btn-primary">Update</a>
            </div>
        </div>
    </div>
</div>   
<script>
    function updateOrder() {
        var form = document.getElementById("updateORderForm");
        var submitOp = document.getElementById("updateOrder");
        submitOp.value = 'update';
        submitOp.click();
    }
</script> 
