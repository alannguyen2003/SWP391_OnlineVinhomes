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

<%--
<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">User Tables</h6>
            </div>
            <div class="row">
                <div class="card-header py-3">
                    <form action="<c:url value="/admin/user-tables.do" />" method="post">
                        <div class="form-group pb-2 row">
                            <div class="col-md-2">
                                <label class="p-1" for="filterOption">Filter By:</label>
                                <select class="form-select" aria-label="Default select example" name="filterOption" id="filterOption">
                                    <option value="gender" ${filterOption=="gender" ? "selected" : ""}>Gender</option>
                                    <option value="role" ${filterOption=="role" ? "selected" : ""}>Role</option>
                                    <option value="status" ${filterOption=="status" ? "selected" : ""}>Status</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group pb-2" id="filterGenderDiv">
                                    <label class="p-1" for="filterCategory">Value:</label>
                                    <select class="form-select" aria-label="Default select example" name="filterValue1" id="filterGender">
                                         Các option của combobox gender 
                                        <option value="${user.getGender()}" ${filterValue1 == user.getGender() ? "selected" : ""}>Male</option>
                                        <option value="${user.getGender()}" ${filterValue1 == user.getGender() ? "selected" : ""}>Female</option>
                                    </select>
                                </div>

                                <div class="form-group pb-2" id="filterRoleDiv">
                                    <label class="p-1" for="filterCategory">Value:</label>
                                    <select class="form-select" aria-label="Default select example" name="filterValue2" id="filterRole">
                                         Các option của combobox role 
                                        <c:forEach var="r" items="${roleList}">
                                            <option value="${r.id}" ${r.id == filterValue2 ? "selected" : ""}>${r.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group pb-2" id="filterStatusDiv">
                                    <label class="p-1" for="filterSupplier">Value:</label>
                                    <select class="form-select" aria-label="Default select example" name="filterValue3" id="filterStatus">
                                         Các option của combobox status 
                                        <option value="1" ${filterValue3 == 1 ? "selected" : ""}>Active</option>
                                        <option value="0" ${filterValue3 == 0 ? "selected" : ""}>Inactive</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group pb-2 row">
                            <div class="col-md-3">
                                <label  class="p-1" for="sortOption">Sort By:</label>
                                <select class="form-select" aria-label="Default select example" name="sortOption" id="sortOption">
                                    <option value="id" ${sortOption == "id" ? "selected" : ""}>ID</option>
                                    <option value="name" ${sortOption == "name" ? "selected" : ""}>Name</option>
                                    <option value="gender" ${sortOption == "gender" ? "selected" : ""}>Gender</option>
                                    <option value="email" ${sortOption == "email" ? "selected" : ""}>Email</option>
                                    <option value="phone" ${sortOption == "phone" ? "selected" : ""}>Phone</option>
                                    <option value="role" ${sortOption == "role" ? "selected" : ""}>Role</option>
                                    <option value="status" ${sortOption == "status" ? "selected" : ""}>Status</option>
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
                                    <option value="email" ${searchOption == "email" ? "selected" : ""}>Email</option>
                                    <option value="phone" ${searchOption == "phone" ? "selected" : ""}>Phone</option>
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
                                            <a class="btn btn-outline-primary" href="<c:url value="/admin/user-detail.do?AID=${r.AID}"/>">View <i class="bi bi-gear"></i></a>
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
            <c:url var="previousPageUrl" value="/admin/user-tables.do">
                <c:param name="page" value="${currentPage - 1}" />
                <c:param name="op" value="${op}" />
                <c:param name="filterOption" value="${filterOption}" />
                <c:param name="filterValue1" value="${filterValue1}" />
                <c:param name="filterValue2" value="${filterValue2}" />
                <c:param name="filterValue3" value="${filterValue3}" />
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
                        <c:url var="pageUrl" value="/admin/user-tables.do">
                            <c:param name="page" value="${i}" />
                            <c:param name="op" value="${op}" />
                            <c:param name="filterOption" value="${filterOption}" />
                            <c:param name="filterValue1" value="${filterValue1}" />
                            <c:param name="filterValue2" value="${filterValue2}" />
                            <c:param name="filterValue3" value="${filterValue3}" />
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
            <c:url var="nextPageUrl" value="/admin/user-tables.do">
                <c:param name="page" value="${currentPage + 1}" />
                <c:param name="op" value="${op}" />
                <c:param name="filterOption" value="${filterOption}" />
                <c:param name="filterValue1" value="${filterValue1}" />
                <c:param name="filterValue2" value="${filterValue2}" />
                <c:param name="filterValue3" value="${filterValue3}" />
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
</nav>--%>

<!<!-- Easter Egg lol dasdjahkdjhadjka -->


<!-- Recent Sales -->
<div class="col-12">
    <div class="card recent-sales overflow-auto">

        <div class="filter">
            <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
            <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                <li class="dropdown-header text-start">
                    <h6>Filter</h6>
                </li>

                <li><a class="dropdown-item" href="#">Today</a></li>
                <li><a class="dropdown-item" href="#">This Month</a></li>
                <li><a class="dropdown-item" href="#">This Year</a></li>
            </ul>
        </div>

        <div class="card-body">
            <h5 class="card-title">Recent Sales <span>| Today</span></h5>

            <table class="table table-borderless datatable">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Room</th>
                        <th scope="col">Block</th>
                        <th scope="col">Role</th>
                        <th scope="col">Status</th>
                        <th scope="col">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${list}" var ="r">
                        <tr>
                            <th scope="row"><a href="#">${r.AID}</a></th>
                            <td>${r.name}</td>
                            <td>${r.gender}</td>
                            <td>${r.email}</td>
                            <td>${r.phone}</td>
                            <td>${r.room}</td>
                            <td>${r.BID}</td>
                            <td>${r.roleID}</td>
                            <td>${r.status}</td>
                            <td>
                                <a class="btn btn-outline-primary" href="<c:url value="/admin/user-detail.do?AID=${r.AID}"/>">View <i class="bi bi-gear"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div><!-- End Recent Sales -->