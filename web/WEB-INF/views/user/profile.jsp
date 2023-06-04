<%-- 
    Document   : resident
    Created on : May 27, 2023, 9:18:55 PM
    Author     : vsngh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!--Page Header Start-->
<section class="page-header">
    <div class="page-header-bg" style="background-image: url(${pageContext.request.contextPath}/assets/images/backgrounds/page-header-bg.jpg)">
    </div>
    <div class="page-header-bubble"><img src="${pageContext.request.contextPath}/assets/images/shapes/page-header-bubble.png" alt=""></div>
    <div class="container">
        <div class="page-header__inner">
            <ul class="thm-breadcrumb list-unstyled">
                <li><a href="<c:url value="/home/index.do"/>">Home</a></li>
                <li><span>/</span></li>
                <li>Profile</li>
            </ul>
            <h2>My Profile</h2>
        </div>
    </div>
</section>
<!--Page Header End-->

<div style="margin-bottom: 5rem; margin-top: 5px;" class="container-xl p-5">
    <!-- Tab bar navigation-->
    <ul class="sub-nav" style="margin-bottom: -1px" activeindex="2">
        <li class="sub-nav__item" label="Billing"><a href="<c:url value="/user/profile.do" />" class="active"><i class="fa fa-info"></i><span>Information</span></a></li>
        <li class="sub-nav__item" label="Billing"><a href="<c:url value="/user/change-password.do" />"><i class="fa fa-lock"></i><span>Security</span></a></li>
    </ul>
    <!-- Divider-->
    <hr class="mt-0 mb-5">
    <!-- Profile content row-->
    <div class="row gx-5">
        <div class="col-xl-4">
            <!-- Profile picture card-->
            <div class="card card-raised mb-3">
                <div style="margin: 0px;"  class="card-body p-5">
                    <div class="card-title">Profile Image</div>
                    <div class="card-subtitle mb-4">This image will be publicly visible to other users.</div>
                    <div class="text-center">
                        <!-- Profile picture image-->
                        <img id="avatar-preview" class="img-fluid rounded-circle mb-1" src="<c:url value="/assets/img/account/avatar-${sessionScope.user.AID}.jpg" />" alt="avatar" style="width: 150px; height: 150px; object-fit: cover;">
                        <!-- Profile picture help block-->
                        <div class="caption text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                        <!-- Profile picture upload button-->
                        <label for="avatar" class="btn btn-primary mdc-ripple-upgraded" type="button">
                            Upload new image
                            <i class="fa fa-upload"></i>
                        </label>
                    </div>
                </div>
            </div>

            <div class="card card-raised mb-5">
                <div style="margin: 0px!important;" class="card-body p-5">
                    <c:choose>
                        <c:when test="${res.room == null}">
                            <div class="caption text-center">Enter your room number
                                <form action="<c:url value="/user/updateRoom.do" />">
                                    <input type="hidden" name="AID" value="${res.AID}" />
                                    <input class="form-control" type="text" name="room" placeholder="Room Number"/>
                                <button class="btn btn-primary mt-10" type="submit">Save</button>
                                </form>
                            </div>
                            </c:when>
                        <c:otherwise>
                        <div class="card-title">Your Room:</div>
                        <div class="card-subtitle mb-4">${res.room}</div>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${user.roleID != 1}">
                        <div class="mb-4 form-group">
                            <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-cash"></i></div>
                            <label class="label">Your salary</label>
                            <div class="card-subtitle mb-4"><fmt:formatNumber value="${emp.salary}" type="currency"/></div>
                        </div>
                        
                        <div class="mb-4 form-group">
                            <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-command"></i></div>
                            <label class="label">Your Manager_ID</label>
                            <div class="card-subtitle mb-4">${emp.manager_id}</div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card card-raised mb-5">
                <div style="margin: 0px!important;" class="card-body p-5">
                    <div class="card-title">Account Details</div>
                    <div class="card-subtitle mb-4">Review and update your account information below.</div>
                    <form method="post" action="<c:url value="/user/updateInfo.do" />" enctype="multipart/form-data">
                        <!-- Form Group (username)-->
                        <div class="mb-4 form-group">
                            <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-envelope-fill"></i></div>
                            <label class="label">Username</label>
                            <input class="w-100 form-control" name="username" value="${sessionScope.user.name}">
                        </div>

                        <!-- Form Group (address)-->

                        <div class="mb-4 form-group">
                            <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-person-vcard-fill"></i></div>
                            <label class="label">Email</label>
                            <input class="w-100 form-control" name="email" value="${user.email}">
                        </div>

                        <!-- Form Row-->
                        <div class="row">
                            <!-- Form Group (email)-->
                            <div class="col-md-6">
                                <div class="form-group mb-4">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-building-fill"></i></div>
                                    <label class="label">Block ID</label>
                                    <input class="w-100 form-control" name="bid" value="${user.BID}">
                                </div>
                            </div>
                            <!-- Form Group (phone)-->
                            <div class="col-md-6">
                                <div class="form-group mb-4">
                                    <div class="form-group-icon" style="background-color: #1239ac;"><i class="bi bi-telephone-fill"></i></div>
                                    <label class="label">Phone</label>
                                    <input class="w-100 form-control" name="phone" value="${user.phone}">
                                </div>
                            </div>
                        </div>

                        <input id="avatar" name="avatar" type="file" accept="image/*" style="display: none" />
                        <input type="hidden" name="isAvaChange" value="false">
                        <!-- Save changes button-->
                        <div class="text-end"><button class="btn btn-primary" type="submit">Save changes</button></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

                                
                                
<script>
    document.querySelector("#avatar").addEventListener("change", (e) => {
        document.querySelector("#avatar-preview").src = URL.createObjectURL(e.target.files[0]);
        document.querySelector("input[name=isAvaChange]").value = true;
    })
    
    document.querySelector("#avatar-preview").addEventListener("error", (e) => {
        e.target.src = "<c:url value="/assets/img/user-avatar.png" />";
    })
    
</script>
