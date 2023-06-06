<%-- 
    Document   : user-tables
    Created on : Jun 4, 2023, 5:41:28 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>Resident Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">User Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">User Tables</h6>
            </div>
            <div class="row">
                <div class="card-header py-3 col-md-6">
                    <form>
                        <div class="form-group pb-2">
                            <label  class="p-1" for="exampleInputEmail1">Search</label>
                            <input type="text" class="form-control" id="exampleInputEmail1" name = "txtSearch" value="${searchValue}" aria-describedby="emailHelp" placeholder="Enter user name">
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary" name = "op" value="search">Search</button>
                    </form>

                </div>
                <div class="card-header py-3 col-md-6">
                    <form>
                        <div class="form-group pb-2">
                            <label  class="p-1" for="exampleInputEmail1">Sort</label>
                            <select class="form-select" aria-label="Default select example" name="option">
                                <option ${option=="blockAsc"?"selected":""} value="blockAsc">Order by block ascending</option>
                                <option ${option=="blockDesc"?"selected":""} value="blockDesc">Order by block descending</option>
                                <option ${option=="roleAsc"?"selected":""} value="roleAsc">Order by role ascending</option>
                                <option ${option=="rolekDesc"?"selected":""} value="roleDesc">Order by role descending</option>
                                <option ${optionBlock=="statusAsc"?"selected":""} value="statusAsc">Order by status ascending</option>
                                <option ${optionBlock=="statusDesc"?"selected":""} value="statusDesc">Order by status descending</option>
                            </select>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary" name = "op" value="sort">Sort</button>
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
                                    <th>Gender</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Room</th>
                                    <th>Block</th>
                                    <th>Role</th>
                                    <th>Status</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <c:forEach var="r" items="${list}" varStatus="loop">
                                <tbody>
                                    <tr>
                                        <td>${r.AID}</td>
                                        <td>${r.name}</td>
                                        <td>${r.gender}</td>
                                        <td>${r.email}</td>
                                        <td>${r.phone}</td>
                                        <td>${r.room}</td>
                                        <td>${r.BID}</td>
                                        <td>${r.roleID}</td>
                                        <td>${r.status}</td>
                                        <td>

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

<nav aria-label="Page navigation example" class="col-lg-12" style="display: flex; justify-content: center">
    <ul class="pagination">

        <c:if test="${currentPage > 1}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/user-tables.do?&page=${currentPage - 1}&op=${op}&txtSearch=${searchValue}&option=${option}" />">Previous</a></li>
            </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <li class="page-item active"><a class="page-link" href="#"><c:out value="${i}" /></a></li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${i <= 3 || i >= totalPages - 2 || (i >= currentPage - 1 && i <= currentPage + 1)}">
                        <li class="page-item"><a class="page-link" href="<c:url value='/admin/user-tables.do?page=${i}&op=${op}&txtSearch=${searchValue}&option=${option}' />"><c:out value="${i}" /></a></li>
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
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/user-tables.do?page=${currentPage + 1}&op=${op}&txtSearch=${searchValue}&option=${option}" />">Next</a></li>
            </c:if>
    </ul>
</nav>
