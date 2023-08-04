<%-- 
    Document   : resident-tables
    Created on : Jun 2, 2023, 9:53:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<div class="pagetitle">
    <h1>Order Details Table</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active"> Orders list / Order Details Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->

<!-- Resident Table -->
<div class="col-12">
    <div class="card recent-sales overflow-auto">
        <div class="card-body">
            <h5 class="card-title">Order Details Tables</h5>
            <table id="myTable" class="table table-border datatable">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Service</th>
                        <th scope="col">Supplier</th>
                        <th scope="col">Price</th>
                        <th scope="col">Note</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${list}" var ="r" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.count}</a></th>
                            <td>${r.service}</td>
                            <td>${r.supplier}</td>
                            <td>${r.price}</td>
                            <td>${r.note}</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

