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
            <li class="breadcrumb-item">Forms</li>
            <li class="breadcrumb-item active">Elements</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="col">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">General Form Elements</h5>
                    <!-- General Form Elements -->
                    <form action="<c:url value="/admin-resource/update-resource-handler.do"/>">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Disabled</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="blockId" value="${blockResourceEntity.bId}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Block Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="blockName" value = "${blockResourceEntity.blockName}" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label"  >Resource Id</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="resourceId" value = "${blockResourceEntity.rId}" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="inputText" class="col-sm-2 col-form-label" >Resource Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="resourceName" value = "${blockResourceEntity.resourceName}" >
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
                                <button type="submit" class="btn btn-primary"name="op" value="update">Update</button>
                                <button type="submit" class="btn btn-primary">Cancel</button>
                            </div>
                        </div>

                    </form><!-- End General Form Elements -->

                </div>
            </div>

        </div>

    </div>
</section>
