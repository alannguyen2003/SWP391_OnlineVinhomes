<%-- 
    Document   : resident-tables
    Created on : Jun 2, 2023, 9:53:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<div class="pagetitle">
    <h1>Resident Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">Resident Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="row">
                <div class="card-header py-3">
                    <form>
                        <div class="form-group pb-2 row">
                            <div class="col-md-2">
                                <label class="p-1" for="filterOption">Filter By:</label>
                                <select class="form-select" aria-label="Default select example" name="filterOption" id="filterOption">
                                    <option value="category" ${filterOption=="category" ? "selected" : ""}>Category</option>
                                    <option value="supplier" ${filterOption=="supplier" ? "selected" : ""}>Supplier</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group pb-2" id="filterCategoryDiv">
                                    <label class="p-1" for="filterCategory">Value:</label>
                                    <select class="form-select" aria-label="Default select example" name="filterValue1" id="filterCategory">
                                        <!-- Các option của combobox category -->
                                        <c:forEach var="cl" items="${categoryList}">
                                            <option value="${cl.id}" ${cl.id == filterValue1 ? "selected" : ""}>${cl.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group pb-2" id="filterSupplierDiv">
                                    <label class="p-1" for="filterSupplier">Value:</label>
                                    <select class="form-select" aria-label="Default select example" name="filterValue2" id="filterSupplier">
                                        <!-- Các option của combobox supplier -->
                                        <c:forEach var="sl" items="${supplierList}">
                                            <option value="${sl.id}" ${sl.id == filterValue2 ? "selected" : ""}>${sl.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group pb-2 row">
                            <div class="col-md-3">
                                <label  class="p-1" for="sortOption">Sort By:</label>
                                <select class="form-select" aria-label="Default select example" name="sortOption" id="sortOption">
                                    <option value="name" ${sortOption == "name" ? "selected" : ""}>Name</option>
                                    <option value="category" ${sortOption == "category" ? "selected" : ""}>Category</option>
                                    <option value="minPrice" ${sortOption == "minPrice" ? "selected" : ""}>Min Price</option>
                                    <option value="maxPrice" ${sortOption == "maxPrice" ? "selected" : ""}>Max Price</option>
                                </select>
                            </div>

                            <div class="col-md-3">
                                <label  class="p-1" for="sortType">Sort Type:</label>
                                <select class="form-select" aria-label="Default select example" name="sortType" id="sortType">
                                    <option value="Ascending" ${sortType == "Ascending" ? "selected" : ""}>Ascending</option>
                                    <option value="Descending" ${sortType == "Descending" ? "selected" : ""}>Descending</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group pb-2 row">
                            <div class="col-md-3">
                                <label  class="p-1" for="searchOption">Search By:</label>
                                <select class="form-select" aria-label="Default select example" name="searchOption" id="searchOption">
                                    <option value="name" ${searchOption == "name" ? "selected" : ""}>Name</option>
                                    <option value="description" ${searchOption == "description" ? "selected" : ""}>Description</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label  class="p-1" for="searchValue">Search Content:</label>
                                <input type="text" class="form-control" name="searchValue" value="${searchValue}" aria-describedby="emailHelp" placeholder="Enter search value">
                            </div>

                        </div>

                        <button type="submit" class="btn btn-primary" name = "op" value="generate"><i class="bi bi-search"></i></button>
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
                                    <th>Description</th>
                                    <th>Min Price</th>
                                    <th>Max Price</th>
                                    <th>Rated</th>
                                    <th>Supplier</th>
                                    <th>Category</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <c:forEach var="s" items="${list}" varStatus="loop">
                                <tbody>
                                    <tr>
                                        <td>${s.serviceID}</td>
                                        <td>${s.name}</td>
                                        <td>${s.description}</td>
                                        <td>${s.lowerPrice}</td>
                                        <td>${s.upperPrice}</td>
                                        <td>${s.rated}</td>
                                        <td>${s.supplierID}</td>
                                        <td>${s.categoryID}</td>
                                        <td><a class="btn btn-outline-primary" href="<c:url value="/admin/service-detail.do?serviceID=${s.serviceID}"/>">View <i class="bi bi-gear"></i></a></td>
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
        <c:url var="previousPageUrl" value="/admin/service-list.do">
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
                        <c:url var="pageUrl" value="/admin/service-list.do">
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
        <c:url var="nextPageUrl" value="/admin/service-list.do">
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
                            
                            


