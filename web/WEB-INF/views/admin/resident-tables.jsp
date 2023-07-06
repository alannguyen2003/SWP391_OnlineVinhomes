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

<!-- Resident Table -->
<div class="col-12">
    <div class="card recent-sales overflow-auto">
        <div class="card-body">
            <h5 class="card-title">Resident Tables</h5>
            <div class="filter">
                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-funnel-fill"></i></a>
                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                        <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="<c:url value="/admin/resident-tables.do?op=getAll" />">All Resident</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/resident-tables.do?op=getAll&filterOption=block" />">Block</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/resident-tables.do?op=getAll&filterOption=status" />">Status</a></li>
                </ul>
            </div>
            <c:if test="${filterOption == 'block'}">
                <div class="form-group pb-2" id="filterBlock">
                    <label class="p-1" for="filterBlock">Value:</label>
                    <select class="form-select" aria-label="Default select example" name="filterValue" id="filterBlockValue">
                        <!--Các option của combobox block--> 
                        <c:forEach var="bl" items="${blockList}">
                            <option value="${bl.BID}" ${bl.BID == filterValue ? "selected" : ""}>${bl.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
            <c:if test="${filterOption == 'status'}">
                <div class="form-group pb-2" id="filterStatus">
                    <label class="p-1" for="filterStatus">Value:</label>
                    <select class="form-select" aria-label="Default select example" name="filterValue" id="filterStatusValue">
                        <!--Các option của combobox status--> 
                        <option value="1" ${filterValue == 1 ? "selected" : ""}>Active</option>
                        <option value="0" ${filterValue == 0 ? "selected" : ""}>Inactive</option>
                    </select>
                </div>
            </c:if>


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
                        <th scope="col">Status</th>
                        <th scope="col">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${list}" var ="r">
                        <tr>
                            <th scope="row">${r.AID}</a></th>
                            <td>${r.name}</td>
                            <td>${r.gender}</td>
                            <td>${r.email}</td>
                            <td>${r.phone}</td>
                            <td>${r.room}</td>
                            <td>${r.BID}</td>
                            <td>${r.status}</td>
                            <td><a class="btn btn-outline-primary" href="<c:url value="/admin/resident-detail.do?AID=${r.AID}"/>">View <i class="bi bi-gear"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- End Resident Table -->
<c:if test="${filterOption == 'block'}">
    <script>
        document.getElementById("filterBlockValue").addEventListener("change", function () {
            var filterValue = this.value;
            var baseUrl = "<c:url value='/admin/resident-tables.do?op=filterBlock&filterOption=block'/>"; // URL cơ sở

            // Tạo link với tham số mới
            var url = baseUrl + "&filterValue=" + filterValue;

            // Chuyển hướng trang sang link đã tạo
            if (url !== baseUrl) {
                window.location.href = url;
            }
        });
    </script>
</c:if>

<c:if test="${filterOption == 'status'}">
    <script>
        document.getElementById("filterStatusValue").addEventListener("change", function () {
            var filterValue = this.value;
            var baseUrl = "<c:url value='/admin/resident-tables.do?op=filterStatus&filterOption=status'/>"; // URL cơ sở

            // Tạo link với tham số mới
            var url = baseUrl + "&filterValue=" + filterValue;

            // Chuyển hướng trang sang link đã tạo
            if (url !== baseUrl) {
                window.location.href = url;
            }
        });
    </script>

</c:if>

