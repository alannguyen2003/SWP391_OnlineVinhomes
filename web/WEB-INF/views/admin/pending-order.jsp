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
<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Orders Tables</h6>
            </div>
            <div class="row">
                <div class="card-header py-3">


                </div>
            </div>
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Resident</th>
                                    <th>Employee</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Note</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <c:forEach var="o" items="${list}" varStatus="loop">
                                <c:if test="${o.eid == '' && o.status == 'Pending'}">
                                        <tbody>
                                        <tr>
                                            <td>${o.id}</td>
                                            <td>${o.residentName}</td>
                                            <td>${o.employeeName}</td>
                                            <td>${o.date}</td>
                                            <td>${o.status}</td>
                                            <td>${o.note}</td>
                                            <td><a class="btn btn-outline-primary" href="<c:url value="/admin/order-detail.do?OID=${o.id}"/>">View <i class="bi bi-gear"></i></a></td>
                                        </tr>
                                    </tbody> 
                                </c:if>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


