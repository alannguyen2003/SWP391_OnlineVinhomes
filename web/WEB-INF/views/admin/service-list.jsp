<%-- 
    Document   : resident-tables
    Created on : Jun 2, 2023, 9:53:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<div class="pagetitle">
    <h1>Service Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">Service Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->

<!-- Service List -->
<div class="col-12">
    <div class="card recent-sales overflow-auto">
        <div class="card-body">
            <h5 class="card-title">Recent Sales <span>| Today</span></h5>

            <div class="filter">
                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-funnel-fill"></i></a>
                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                        <h6>Filter</h6>
                    </li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/service-list.do?op=getAll" />">All</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/service-list.do?op=getAll&filterOption=category" />">Category</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/service-list.do?op=getAll&filterOption=supplier" />">Supplier</a></li>
                </ul>
            </div>
            <c:if test="${filterOption == 'category'}">
                <div class="form-group pb-2 col-md-3" id="filterCategory">
                    <label class="p-1" for="filterCategory">Value:</label>
                    <select class="form-select" aria-label="Default select example" name="filterValue" id="filterCategoryValue">
                        <!-- Các option của combobox category -->
                        <c:forEach var="cl" items="${categoryList}">
                            <option value="${cl.id}" ${cl.id == filterValue ? "selected" : ""}>${cl.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
            <c:if test="${filterOption == 'supplier'}">
                <div class="form-group pb-2 col-md-5" id="filterSupplier">
                    <label class="p-1" for="filterSupplier">Value:</label>
                    <select class="form-select" aria-label="Default select example" name="filterValue" id="filterSupplierValue">
                        <!-- Các option của combobox supplier -->
                        <c:forEach var="sl" items="${supplierList}">
                            <option value="${sl.id}" ${sl.id == filterValue ? "selected" : ""}>${sl.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
            <table class="table table-borderless datatable">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Min Price</th>
                        <th scope="col">Max Price</th>
                        <th scope="col">Rated</th>
                        <th scope="col">Supplier</th>
                        <th scope="col">Category</th>
                        <th scope="col">Operation</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${requestScope.list}" var ="listService">
                        <tr>
                            <th scope="row">${listService.serviceID}</a></th>
                            <td>${listService.name}</td>
                            <td>${listService.description}</a></td>
                            <td>${listService.lowerPrice}</td>
                            <td>${listService.upperPrice}</td>
                            <td>${listService.rated}</td>
                            <td>${listService.supplierID}</td>
                            <td>${listService.categoryID}</td>
                            <td><a class="btn btn-outline-primary" href="<c:url value="/admin/service-detail.do?serviceID=${s.serviceID}"/>">View <i class="bi bi-gear"></i></a></td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
            <a href="<c:url value="/admin/service-create.do" />" class="btn btn-primary mt-2">Create</a>
        </div>

    </div>

</div><!-- End Service List -->

<!--JS tại chỗ-->
<c:if test="${filterOption == 'category'}">
    <script>
        document.getElementById("filterCategoryValue").addEventListener("change", function () {
            var filterValue = this.value;
            var baseUrl = "<c:url value='/admin/service-list.do?op=filterCategory&filterOption=category'/>"; // URL cơ sở

            // Tạo link với tham số mới
            var url = baseUrl + "&filterValue=" + filterValue;

            // Chuyển hướng trang sang link đã tạo
            if (url !== baseUrl) {
                window.location.href = url;
            }
        });
    </script>
</c:if>

<c:if test="${filterOption == 'supplier'}">
    <script>
        document.getElementById("filterSupplierValue").addEventListener("change", function () {
            var filterValue = this.value;
            var baseUrl = "<c:url value='/admin/service-list.do?op=filterSupplier&filterOption=supplier'/>"; // URL cơ sở

            // Tạo link với tham số mới
            var url = baseUrl + "&filterValue=" + filterValue;

            // Chuyển hướng trang sang link đã tạo
            if (url !== baseUrl) {
                window.location.href = url;
            }
        });
    </script>

</c:if>











