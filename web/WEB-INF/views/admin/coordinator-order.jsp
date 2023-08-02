<%-- 
    Document   : employee-order
    Created on : Jun 18, 2023, 1:44:20 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>Coordinator Order Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">Coordinator Order Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->

<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="card shadow mb-4">
                <div class="card-body" style="margin-top: 20px">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Resident</th>
                                    <th scope="col">Coordinator</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Delivery Time</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Block</th>
                                    <th scope="col">Room</th>
                                    <th scope="col">Note</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Operation</th>
                                </tr>
                            </thead>
                            <c:forEach var="cor" items="${corOrderList}" varStatus="loop">
                                <tbody>
                                    <tr>
                                        <th scope="row">${loop.count}</a></th>
                                        <td>${cor.residentName}</td>
                                        <td>${cor.coordinatorName}</td>
                                        <td>${cor.date}</td>
                                        <td>${cor.delivery_time}</td>
                                        <c:if test="${cor.status == 'Pending'}">
                                            <td><span class="bage bage-warning">${cor.status}</span></td>
                                            </c:if>
                                            <c:if test="${cor.status == 'Completed'}">
                                            <td><span class="bage bage-success">${cor.status}</span></td>
                                            </c:if>
                                            <c:if test="${cor.status == 'Failed'}">
                                            <td><span class="bage bage-danger">${cor.status}</span></td>
                                            </c:if>
                                            <c:if test="${cor.status == 'Cancel'}">
                                            <td><span class="bage bage-cancel">${cor.status}</span></td>
                                            </c:if>
                                        <td>${cor.block}</td>
                                        <td>${cor.room}</td>
                                        <td>${cor.note}</td>
                                        <td>${cor.total == 0? "" : cor.total}</td>
                                        <td>
                                            <a class="btn btn-outline-primary" href="<c:url value="/admin/coordinator-order-detail.do?OID=${cor.id}"/>">View <i class="bi bi-gear"></i></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<nav>
    <ul class="pagination justify-content-center">
        <c:if test="${currentPage > 1}">
            <c:url var="previousPageUrl" value="/admin/resident-tables.do">
                <c:param name="page" value="${currentPage - 1}" />
                <c:param name="op" value="${op}" />

            </c:url>
            <li class="page-item">
                <a class="page-link" href="${previousPageUrl}">Previous</a>
            </li>
        </c:if>
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <li class="page-item active">
                        <a class="page-link" href="#"><c:out value="${i}" /></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:if test="${i <= 3 || i >= totalPages - 2 || (i >= currentPage - 1 && i <= currentPage + 1)}">
                        <c:url var="pageUrl" value="/admin/resident-tables.do">
                            <c:param name="page" value="${i}" />
                            <c:param name="op" value="${op}" />

                        </c:url>
                        <li class="page-item">
                            <a class="page-link" href="${pageUrl}"><c:out value="${i}" /></a>
                        </li>
                    </c:if>
                    <c:if test="${i == 4 && currentPage > 5}">
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                    </c:if>
                    <c:if test="${i == totalPages - 3 && currentPage < totalPages - 4}">
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <c:url var="nextPageUrl" value="/admin/resident-tables.do">
                <c:param name="page" value="${currentPage + 1}" />
                <c:param name="op" value="${op}" />

            </c:url>
            <li class="page-item">
                <a class="page-link" href="${nextPageUrl}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>