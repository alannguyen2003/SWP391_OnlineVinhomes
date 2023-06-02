<%-- 
    Document   : resident-tables
    Created on : Jun 2, 2023, 9:53:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<div class="card shadow mb-4">
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Room</th>
                        <th>Block</th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach var="r" items="${list}" varStatus="loop">
                    <tbody>
                        <tr>
                            <td>${r.AID}</td>
                            <td>${r.name}</td>
                            <td>${r.email}</td>
                            <td>${r.phone}</td>
                            <td>${r.room}</td>
                            <td>${r.BID}</td>
                            <td>
<!--                                <a href="<c:url value="/admin/table_update.do?id=${product.id}" />">Update</a> |
                                <a href="<c:url value="/admin//table_delete.do?id=${product.id}"/>" id="delete" data-toggle="modal" data-target="#deleteModal">
                                    Delete
                                </a>                         -->
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<nav aria-label="Page navigation example" class="col-lg-12" style="display: flex; justify-content: center">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${endP}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin-resource/table-resource.do?endP=${endP}&page=${i}&op=${op}&txtSearch=${searchValue}&optionQuantity=${optionQuantity}&searchOption=${searchOption}" />">${i}</a></li>
            </c:forEach>
    </ul>
</nav>