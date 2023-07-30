<%-- 
    Document   : update-resource
    Created on : Jun 1, 2023, 8:37:26 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<div class="pagetitle">
    <h1>Add Resources</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item">Add</li>
            <li class="breadcrumb-item active">Resource Create</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="col">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add Resource</h5>
                    <div class="row p-2">
                        ${listResource!=null?"":"There is no unassigned resource to be added"}
                    </div>
                    <!-- General Form Elements -->
                    <form id="addResourceForm" action="<c:url value="/admin-resource/add-resource-handler.do"/>">
                        <input type="text" hidden name="blockId" value="${block.BID}"/>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Block Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" disabled value = "${block.name}" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="resource" class="col-sm-2 col-form-label" >Resource Name</label>
                            <div class="col-sm-10">
                                <select ${requestScope.listResource!=null?"":"disabled"} name="resourceId" id="resource" class="form-select" aria-label="Default select example">
                                    <c:forEach var="resource" items="${listResource}">
                                        <option value="${resource.id}">${resource.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputNumber" class="col-sm-2 col-form-label">Quantity</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="quantity">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-10">
                                <button type="button" class="btn btn-primary" ${listResource!=null?"":"disabled"} data-toggle="modal" data-target="#addResourceModal">Create</button>
                                <input id="addResource" type="submit" name="op" value="" hidden>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cancelAddResourceModal">Cancel</button>
                            </div>
                        </div>

                    </form><!-- End General Form Elements -->

                </div>
            </div>

        </div>

    </div>
</section>


<div class="modal fade" id="addResourceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to update this resource?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Create" below if you are ready to update this resource.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a onclick="addResource()" class="btn btn-primary"/>Create</a>
            </div>
        </div>
    </div>
</div>   
<div class="modal fade" id="cancelAddResourceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to cancel update this resource?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Yes" below if you are ready to cancel the update transaction.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">No</button>
                <a onclick="cancelAddResource()" class="btn btn-primary"/>Yes</a>
            </div>
        </div>
    </div>
</div>   
<script>
    function addResource() {
        var form = document.getElementById("addResourceForm");
        var submitOp = document.getElementById("addResource");
        submitOp.value = 'add';
        submitOp.click();
    }
    function cancelAddResource() {
        var form = document.getElementById("addResourceForm");
        var submitOp = document.getElementById("addResource");
        submitOp.value = 'cancel';
        submitOp.click();
    }
</script>





