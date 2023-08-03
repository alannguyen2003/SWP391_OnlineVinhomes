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


<div class="col-xl-12">
    <div class="card shadow mb-4">
        <div class="card-body mt-4">
            <ul class="sub-nav" style="margin-bottom: 2rem;" activeindex="2">
                <li class="sub-nav__item" label="Billing"><a href="<c:url value="/admin/coordinator-order-detail.do?OID=${OID}" />" class="${activation == 'coordinator-order-detail'} ? 'active' : ''"><i class="bi bi-person-fill-add"></i><span>Coordinator Order</span></a></li>
                <li class="sub-nav__item" label="Billing"><a href="<c:url value="/admin/add-price-order.do?OID=${OID}" />" class="${activation == 'add-price-order'} ? 'active' : ''"><i class="bi bi-cash-coin"></i><span>Add Price</span></a></li>
            </ul>
            <!-- Account details card-->

            <form id="updateOrderForm" action="<c:url value="/admin/updatePrice.do" />" method="post">
                <input type="hidden" name="OID" value="${OID}">
                <div id="${OID}">
                    <hr />
                    <div class="custom-table">
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Expected costs</th>
                                    <th>Price</th>
                                    <th>Supplier</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Nội dung OrderDetail -->
                                <c:forEach var="od" items="${list}" varStatus="loop">
                                    <tr>
                                        <td class="cart__product__item">
                                            <input name="id" type="hidden" class="form-control cart__product__item__title" id="AID" value="${loop.count}" size="3">
                                            <input name="id" type="text" class="form-control cart__product__item__title" id="AID" value="${loop.count}" size="3" disabled>
                                        </td>
                                        <td>
                                            <div class="cart__product__item__title">
                                                <h6>${od.name}</h6>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cart__product__item__title">
                                                <h6>${od.minPrice} - ${od.maxPrice}</h6>
                                            </div>
                                        </td>
                                        <td>
                                            <input name="price_${od.id}" type="number" class="form-control" id="price" value="${od.price}" placeholder="Input price here">
                                        </td>
                                        <td>
                                            <select name="supplier_${od.id}" class="form-control"> 
                                                <option value="">-- Select Coordinator --</option>
                                                <c:forEach var="supplier" items="${listSupplier}">
                                                    <option value="${supplier.id}" ${supplier.name == od.supplier? 'selected' : ''}>${supplier.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
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



