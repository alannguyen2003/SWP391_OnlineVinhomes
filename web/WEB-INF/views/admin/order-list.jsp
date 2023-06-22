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
                <div class="card-body" style="margin-bottom: 0px;">
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
                                    <th>Price</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <c:forEach var="o" items="${list}" varStatus="loop">
                                <tbody>
                                    <tr>
                                        <td>${o.id}</td>
                                        <td>${o.residentName}</td>
                                        <td>${o.employeeName}</td>
                                        <td>${o.date}</td>
                                        <c:if test="${o.status == 'Pending'}">
                                            <td><span class="bage bage-warning">${o.status}</span></td>
                                            </c:if>
                                            <c:if test="${o.status != 'Pending'}">
                                            <td><span class="bage bage-${o.status == "Completed" ? "success" : "danger"}">${o.status}</span></td>
                                            </c:if>
                                        <td>${o.note}</td>
                                        <td>${o.total}$</td>
                                        <td><a class="btn btn-outline-primary" href="<c:url value="/admin/add-employee-order.do?OID=${o.id}"/>">View <i class="bi bi-gear"></i></a></td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
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
                <c:param name="filterOption" value="${filterOption}" />
                <c:param name="filterValue1" value="${filterValue1}" />
                <c:param name="filterValue2" value="${filterValue2}" />
                <c:param name="searchOption" value="${searchOption}" />
                <c:param name="searchValue" value="${searchValue}" />
                <c:param name="sortOption" value="${sortOption}" />
                <c:param name="sortType" value="${sortType}" />
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
                            <c:param name="filterOption" value="${filterOption}" />
                            <c:param name="filterValue1" value="${filterValue1}" />
                            <c:param name="filterValue2" value="${filterValue2}" />
                            <c:param name="searchOption" value="${searchOption}" />
                            <c:param name="searchValue" value="${searchValue}" />
                            <c:param name="sortOption" value="${sortOption}" />
                            <c:param name="sortType" value="${sortType}" />
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
            <c:url var="nextPageUrl" value="/admin/order-list.do">
                <c:param name="page" value="${currentPage + 1}" />
                <c:param name="op" value="${op}" />
                <c:param name="filterOption" value="${filterOption}" />
                <c:param name="filterValue1" value="${filterValue1}" />
                <c:param name="filterValue2" value="${filterValue2}" />
                <c:param name="searchOption" value="${searchOption}" />
                <c:param name="searchValue" value="${searchValue}" />
                <c:param name="sortOption" value="${sortOption}" />
                <c:param name="sortType" value="${sortType}" />
            </c:url>
            <li class="page-item">
                <a class="page-link" href="${nextPageUrl}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>




