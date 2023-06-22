<%-- 
    Document   : table-resource
    Created on : May 31, 2023, 5:57:37 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<div class="pagetitle">
    <h1>Data Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">Data</li>
        </ol>
    </nav>
</div><!-- End Page Title -->

<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Resource Table</h6>
            </div>
            <div class="card-header py-3">
                <a href="<c:url value="/resource/resource-add.do"/>" class="btn btn-primary">Create</a>
            </div>

            <div class="card-body">

                <div class="table-responsive">
                    <table class="table table-borderless datatable" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Resource ID</th>
                                <th scope="col">Resource Name</th>
                                <th scope="col">Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="resource" items="${list}" varStatus="loop">
                                <tr>
                                    <th>${loop.count}</th>
                                    <td>${resource.id}</td>
                                    <td>${resource.name}</td>
                                    <td><a class="btn btn-outline-primary" href="<c:url value="/resource/resource-update.do?resourceId=${resource.id}"/>">Update <i class="bi bi-gear"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

