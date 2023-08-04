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


<div class="col-xl-12">
    <div class="card shadow mb-4">
        <div class="card-body mt-4">
            <ul class="sub-nav" style="margin-bottom: 2rem;" activeindex="2">
                <li class="sub-nav__item" label="Billing"><a href="<c:url value="/admin/coordinator-order-detail.do?OID=${OID}" />" class="${activation == 'coordinator-order-detail'} ? 'active' : ''"><i class="bi bi-person-fill-add"></i><span>Coordinator Order</span></a></li>
                <li class="sub-nav__item" label="Billing"><a href="<c:url value="/admin/add-price-order.do?OID=${OID}" />" class="${activation == 'add-price-order'} ? 'active' : ''"><i class="bi bi-cash-coin"></i><span>Add Price</span></a></li>
                <li class="sub-nav__item" label="Billing"><a href="<c:url value="/admin/add-service-order.do?OID=${OID}" />" class="${activation == 'add-price-order'} ? 'active' : ''"><i class="bi bi-patch-plus"></i><span>Add Service    </span></a></li>
            </ul>
            <!-- Account details card-->

            <form id="updateOrderForm" action="<c:url value="/admin/add-service-order-handler.do" />" method="post">
                <input type="hidden" name="OID" value="${OID}">
                <div id="${OID}">
                    <hr/>
                    <select name="sId" class="form-control mb-3"> 
                        <option value="">-- Select Service --</option>
                        <c:forEach var="service" items="${serviceList}">
                            <option value="${service.serviceID}">${service.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6 text-success">${message}</div>
                    <div class="col-md-6 d-flex justify-content-end">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateOrderModal">Save change</button>
                        <input id="updateOrder" type="submit" hidden>
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




<!--CSS tại chỗ-->
<style>
    .custom-table table {
        border-collapse: collapse;
        width: 100%;
        border: 1px solid #ddd;
    }

    .custom-table table tr {
        border-bottom: 1px solid #ddd;
    }

    .custom-table table th,
    .custom-table table td {
        border-right: 1px solid #ddd;
    }

    .custom-table table {
        background-color: #fff;
    }

    .custom-table table th,
    .custom-table table td {
        text-align: center;
    }
</style>




