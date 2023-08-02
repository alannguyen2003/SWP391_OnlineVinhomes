<%-- 
    Document   : resident-tables
    Created on : Jun 2, 2023, 9:53:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<div class="pagetitle">
    <h1>Orders Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">Orders Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->

<!-- Coordinator Table -->
<div class="col-12">
    <div class="card recent-sales overflow-auto">
        <div class="card-body">
            <h5 class="card-title">Coordinator Tables</h5>
            <div class="filter">
                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-funnel-fill"></i></a>
                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                        <h6>Filter</h6>
                    </li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/order-list.do?op=getall" />">All Orders</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/order-list.do?op=getall&filterOption=status" />">Status</a></li>
                </ul>
            </div>


            <table id="myTable" class="table table-borderless datatable">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Available</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Email</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${list}" var ="c" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.count}</a></th>
                            <td>${c.name}</td>
                            <c:if test="${c.available == 'true'}">
                                <td>Available</td>
                            </c:if>
                            <c:if test="${c.available == 'false'}">
                                <td>Unavailable</td>
                            </c:if>
                            <td>${c.phone}</td>
                            <td>${c.email}</td>
                            <td>${c.gender}</td>
                            <td><a class="btn btn-outline-primary" href="<c:url value="/admin/coordinator-detail.do?CID=${c.CID}"/>">View <i class="bi bi-gear"></i></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

