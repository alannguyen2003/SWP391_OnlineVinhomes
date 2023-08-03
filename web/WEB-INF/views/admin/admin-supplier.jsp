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
<%--<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Suppliers Tables</h6>
            </div>
            <div class="row">
                <div class="card-header py-3 col-md-6">
                    <form>
                        <div class="form-group pb-2">
                            <label  class="p-1" for="exampleInputEmail1">Search</label>
                            <input type="text" class="form-control" id="exampleInputEmail1" name = "txtSearch" value="${searchValue}" aria-describedby="emailHelp" placeholder="Enter supplier to search">
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary" name = "op" value="search">Search</button>
                    </form>

                </div>
            </div>
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Phone</th>
                                    <th>Email</th>
                                    <th>Address</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <c:forEach var="s" items="${list}" varStatus="loop">
                                <tbody>
                                    <tr>
                                        <td>${s.id}</td>
                                        <td>${s.name}</td>
                                        <td>${s.phone}</td>
                                        <td>${s.email}</td>
                                        <td>${s.address}</td>
                                        <td><a class="btn btn-outline-primary" href="<c:url value="/admin/supplier-detail.do?AID=${s.id}"/>">View <i class="bi bi-gear"></i></a></td>
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

<nav aria-label="Page navigation example" class="col-lg-12" style="display: flex; justify-content: center">
    <ul class="pagination">

        <c:if test="${currentPage > 1}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/resident-tables.do?&page=${currentPage - 1}&op=${op}&txtSearch=${searchValue}&optionBlock=${optionBlock}" />">Previous</a></li>
            </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <li class="page-item active"><a class="page-link" href="#"><c:out value="${i}" /></a></li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${i <= 3 || i >= totalPages - 2 || (i >= currentPage - 1 && i <= currentPage + 1)}">
                        <li class="page-item"><a class="page-link" href="<c:url value='/admin/resident-tables.do?page=${i}&op=${op}&txtSearch=${searchValue}&optionBlock=${optionBlock}' />"><c:out value="${i}" /></a></li>
                        </c:if>
                        <c:if test="${i == 4 && currentPage > 5}">
                        <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                        </c:if>
                        <c:if test="${i == totalPages - 3 && currentPage < totalPages - 4}">
                        <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/resident-tables.do?page=${currentPage + 1}&op=${op}&txtSearch=${searchValue}&optionBlock=${optionBlock}" />">Next</a></li>
            </c:if>
    </ul>
</nav>--%>


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


