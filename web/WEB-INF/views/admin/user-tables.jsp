<%-- 
    Document   : user-tables
    Created on : Jun 4, 2023, 5:41:28 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div class="pagetitle">
    <h1>User Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">User Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->

<div class="col-12">
    <div class="card recent-sales overflow-auto">
        <div class="card-body">
            <h5 class="card-title">User Tables</h5>
            <div>
                <a class="btn btn-outline-primary" href="<c:url value="/admin/user-create.do"/>">Add <i class="bi bi-person-plus"></i></a>
            </div>
            <div class="filter">
                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-funnel-fill"></i></a>
                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                        <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="<c:url value="/admin/user-tables.do?op=getAll" />">All Resident</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/user-tables.do?op=getAll&filterOption=gender" />">Gender</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/user-tables.do?op=getAll&filterOption=role" />">Role</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/admin/user-tables.do?op=getAll&filterOption=status" />">Status</a></li>
                </ul>
            </div>
                
            <c:if test="${filterOption == 'gender'}">
                <div class="form-group pb-2" id="filterGender">
                    <label class="p-1" for="filterGender">Value:</label>
                    <select class="form-select" aria-label="Default select example" name="filterValue1" id="filterGenderValue">
                        Các option của combobox gender 
                        <option value="Male" ${filterValue1 == 'Male' ? "selected" : ""}>Male</option>
                        <option value="Female" ${filterValue1 == 'Female' ? "selected" : ""}>Female</option>
                    </select>
                </div>
            </c:if>
            <c:if test="${filterOption == 'role'}">
                <div class="form-group pb-2" id="filterRole">
                    <label class="p-1" for="filterRole">Value:</label>
                    <select class="form-select" aria-label="Default select example" name="filterValue" id="filterRoleValue">
                        Các option của combobox role 
                        <c:forEach var="r" items="${roleList}">
                            <option value="${r.id}" ${r.id == filterValue ? "selected" : ""}>${r.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
            <c:if test="${filterOption == 'status'}">
                <div class="form-group pb-2" id="filterStatus">
                    <label class="p-1" for="filterStatus">Value:</label>
                    <select class="form-select" aria-label="Default select example" name="filterValue" id="filterStatusValue">
                        Các option của combobox status 
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
                        <th scope="col">Role</th>
                        <th scope="col">Status</th>
                        <th scope="col">Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${list}" var ="r">
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
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- End Resident Table -->
<c:if test="${filterOption == 'gender'}">
    <script>
        document.getElementById("filterGenderValue").addEventListener("change", function () {
            var filterValue = this.value;
            var baseUrl = "<c:url value='/admin/user-tables.do?op=filterGender&filterOption=gender'/>"; // URL cơ sở

            // Tạo link với tham số mới
            var url = baseUrl + "&filterValue1=" + filterValue;

            // Chuyển hướng trang sang link đã tạo
            if (url !== baseUrl) {
                window.location.href = url;
            }
        });
    </script>
</c:if>
<c:if test="${filterOption == 'role'}">
    <script>
        document.getElementById("filterRoleValue").addEventListener("change", function () {
            var filterValue = this.value;
            var baseUrl = "<c:url value='/admin/user-tables.do?op=filterRole&filterOption=role'/>"; // URL cơ sở

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
            var baseUrl = "<c:url value='/admin/user-tables.do?op=filterStatus&filterOption=status'/>"; // URL cơ sở

            // Tạo link với tham số mới
            var url = baseUrl + "&filterValue=" + filterValue;

            // Chuyển hướng trang sang link đã tạo
            if (url !== baseUrl) {
                window.location.href = url;
            }
        });
    </script>

</c:if>
