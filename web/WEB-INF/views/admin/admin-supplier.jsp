<%-- 
    Document   : resident-tables
    Created on : Jun 2, 2023, 9:53:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<div class="pagetitle">
    <h1>Suppliers Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">Suppliers Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->

<!-- Supplier Table -->
<div class="col-12">
    <div class="card recent-sales overflow-auto">

        

        <div class="card-body">
            <h5 class="card-title">Supplier Tables</h5>

            <table class="table table-borderless datatable">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Email</th>
                        <th scope="col">Address</th>
                        <th scope="col">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${list}" var ="s">
                        <tr>
                            <th scope="row">${s.id}</a></th>
                            <td>${s.name}</td>
                            <td>${s.phone}</td>
                            <td>${s.email}</td>
                            <td>${s.address}</td>
                            <td><a class="btn btn-outline-primary" href="<c:url value="/admin/supplier-detail.do?SID=${s.id}"/>">View <i class="bi bi-gear"></i></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div><!-- End Supplier Table -->


