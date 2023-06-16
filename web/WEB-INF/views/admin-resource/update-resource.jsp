<%-- 
    Document   : update-resource
    Created on : Jun 1, 2023, 8:37:26 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<div class="pagetitle">
    <h1>Form Elements</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item">Update</li>
            <li class="breadcrumb-item active">Resource Update</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="col">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Update Resource</h5>
                    <!-- General Form Elements -->
                    <form id="updateResourceForm" action="<c:url value="/admin-resource/update-resource-handler.do"/>">
                        <input type="text" class="form-control" hidden name="blockId" value="${blockResourceEntity.bId}">
                        <input type="text" class="form-control" hidden name="blockName" value = "${blockResourceEntity.blockName}" >
                        <input type="text" class="form-control" hidden name="resourceId" value = "${blockResourceEntity.rId}" >
                        <input type="text" class="form-control" hidden name="resourceName" value = "${blockResourceEntity.resourceName}" >
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Block ID</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" disabled value="${blockResourceEntity.bId}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Block Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" disabled value = "${blockResourceEntity.blockName}" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label"  >Resource Id</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" disabled value = "${blockResourceEntity.rId}" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Resource Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" disabled value = "${blockResourceEntity.resourceName}" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputNumber" class="col-sm-2 col-form-label">Quantity</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="quantity" value = "${blockResourceEntity.quantity}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-10">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateResourceModal">Update</button>
                                <input id="updateResource" type="submit" name="op" value="" hidden>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cancelUpdateResourceModal">Cancel</button>
                            </div>
                        </div>

                    </form><!-- End General Form Elements -->

                </div>
            </div>

        </div>

    </div>
</section>


<div class="modal fade" id="updateResourceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Do you want to update this resource?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Update" below if you are ready to update this resource.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a id="updateResourceLink" onclick="updateResource()" class="btn btn-primary"/>Update</a>
            </div>
        </div>
    </div>
</div>   
<div class="modal fade" id="cancelUpdateResourceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                <a id="cancelUpdateResourceLink" onclick="cancelUpdateResource()" class="btn btn-primary"/>Yes</a>
            </div>
        </div>
    </div>
</div>   
<script>
    function updateResource() {
        var form = document.getElementById("updateResourceForm");
        var submitOp = document.getElementById("updateResource");
        submitOp.value = 'update';
        submitOp.click();
    }
    function cancelUpdateResource() {
        var form = document.getElementById("updateResourceForm");
        var submitOp = document.getElementById("updateResource");
        submitOp.value = 'cancel';
        submitOp.click();
    }
</script>





