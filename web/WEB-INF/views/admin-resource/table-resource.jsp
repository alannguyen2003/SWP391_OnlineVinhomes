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
                <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
            </div>
            <div class="card-header py-3 col-md-12">
                <form>
                    <div class="form-group pb-2">
                        <label  class="p-1" for="exampleInputEmail1">Search</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" name = "txtSearch" value="${searchValue}" aria-describedby="emailHelp" placeholder="Enter resource name">
                    </div>
                    <br/>
                    <input type="checkbox" name="isSortedSearch" id="isSortedSearch"/> Sorted by Quantity
                    <br/>
                    <select class="form-select" hidden id="sortedSearchOption" aria-label="Default select example" name="searchOption">
                        <option ${searchOption=="quantityAsc"?"selected":""} value="quantityAsc">Order by quantity ascending</option>
                        <option ${searchOption=="quantityDesc"?"selected":""} value="quantityDesc">Order by quantity descending</option>
                    </select>
                    <br/>
                    <button type="submit" class="btn btn-primary" name = "op" value="search">Search</button>
                </form>

            </div>
            <div class="card-header py-3 col-md-6">
                <form class="pt-3">
                    <select class="form-select" aria-label="Default select example" name="optionQuantity">
                        <option ${optionQuantity=="quantityAsc"?"selected":""} value="quantityAsc">Order by quantity ascending</option>
                        <option ${optionQuantity=="quantityDesc"?"selected":""} value="quantityDesc">Order by quantity descending</option>
                    </select>
                    <br/>
                    <button type="submit" class="btn btn-primary" name = "op" value="filter">Filter</button>
                </form>
            </div>
            <div class="card-header py-3">
                <a href="<c:url value="/admin-resource/table-resource.do?op=getAll"/>" class="btn btn-primary">Reset table</a>
            </div>

            <div class="card-body">

                <div class="table-responsive">
                    ${message}
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Block Name</th>
                                <th scope="col">Resource Name</th>
                                <th scope="col">quantity</th>
                                <th scope="col">Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="resource" items="${list}" varStatus="loop">
                                <tr>
                                    <th>${loop.count + page*10}</th>
                                    <td>${resource.blockName}</td>
                                    <td>${resource.resourceName}</td>
                                    <td>${resource.quantity}</td>
                                    <td><a class="btn btn-outline-primary" href="<c:url value="/admin-resource/update-resource.do?blockId=${resource.bId}&resourceId=${resource.rId}"/>">Update <i class="bi bi-gear"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<nav aria-label="Page navigation example" class="col-lg-12" style="display: flex; justify-content: center">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${endP}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin-resource/table-resource.do?endP=${endP}&page=${i}&op=${op}&txtSearch=${searchValue}&optionQuantity=${optionQuantity}&searchOption=${searchOption}" />">${i}</a></li>
            </c:forEach>
    </ul>
</nav>

<script>
    var checkbox = document.querySelector("input[name=isSortedSearch]");

    checkbox.addEventListener('change', function () {
        if (this.checked) {
            document.getElementById("sortedSearchOption").hidden = false;
        } else {
            document.getElementById("sortedSearchOption").hidden = true;
        }
    });
</script>
