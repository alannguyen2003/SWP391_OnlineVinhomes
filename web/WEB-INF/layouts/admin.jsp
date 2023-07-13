<%-- 
    Document   : admin
    Created on : May 31, 2023, 2:46:00 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Brote Vinhomes Services - Admin</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="${pageContext.request.contextPath}/assets/img/favicon.png" rel="icon">
        <link href="${pageContext.request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet">



        <!-- template styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/brote.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/brote-responsive.css" />

        <!-- Vendor CSS Files -->
        <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/vendor/simple-datatables/style.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/order.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/admin-profile/admin-profile.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">


        <!-- Template Main CSS File -->
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">


    </head>

    <body>

        <!-- ======= Header ======= -->
        <header id="header" class="header fixed-top d-flex align-items-center">
            <div class="logo-container">
                <a href=<c:url value="/home/index.do"/> class="d-flex align-items-center">
                    <img src="<c:url value="/assets/images/resources/logo-1.png"/>" alt="" class="logo-image">
                </a>
            </div>
            <i class="bi bi-list toggle-sidebar-btn"></i>
            <!-- End Logo -->
            <nav class="header-nav ms-auto">
                <ul class="d-flex align-items-center">

                    <li class="nav-item d-block d-lg-none">
                        <a class="nav-link nav-icon search-bar-toggle " href="#">
                            <i class="bi bi-search"></i>
                        </a>
                    </li><!-- End Search Icon-->

                    <li class="nav-item dropdown">

                        <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
                            <i class="bi bi-bell"></i>
                            <span class="badge bg-primary badge-number noti" id="noti">4</span>
                        </a><!-- End Notification Icon -->

                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
                            <li class="dropdown-header">
                                You have <span class="noti">4</span> new notifications
                                <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
                            </li>
                        </ul><!-- End Notification Dropdown Items -->

                    </li><!-- End Notification Nav -->



                    <li class="nav-item dropdown pe-3">

                        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                            <img src="<c:url value='/assets/img/account/avatar-${sessionScope.user.AID}.jpg' />" alt="Profile" class="rounded-circle">
                            <span class="d-none d-md-block dropdown-toggle ps-2">${sessionScope.user.name}</span>
                        </a><!-- End Profile Iamge Icon -->

                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                            <li class="dropdown-header">
                                <h6>${sessionScope.user.name}</h6>
                                <span>Administrator</span>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>

                            <li>
                                <a class="dropdown-item d-flex align-items-center" href="<c:url value="/user/profile.do" />">
                                    <i class="bi bi-person"></i>
                                    <span>My Profile</span>
                                </a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>

                            <li>
                                <a class="dropdown-item d-flex align-items-center" href="<c:url value="/user/change-password.do" />">
                                    <i class="bi bi-gear"></i>
                                    <span>Change Password</span>
                                </a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <a class="dropdown-item d-flex align-items-center" href="<c:url value="/user/logout.do" />">
                                    <i class="bi bi-box-arrow-right"></i>
                                    <span>Sign Out</span>
                                </a>
                            </li>

                        </ul><!-- End Profile Dropdown Items -->
                    </li><!-- End Profile Nav -->

                </ul>
            </nav><!-- End Icons Navigation -->

        </header><!-- End Header -->

        <!-- ======= Sidebar ======= -->
        <aside id="sidebar" class="sidebar">

            <ul class="sidebar-nav" id="sidebar-nav">

                <li class="nav-item ${activeTab == "dashboard" ? "active" : ""}">
                    <a class="nav-link1" href="<c:url value="/admin/admin-dashboard.do"/>">
                        <i class="bi bi-grid"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <!-- End Dashboard -->

                <c:if test="${sessionScope.user.roleID == 4}">
                    <li class="nav-item ${activeTab == "service" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin/service-list.do?op=getAll" />">
                            <i class="bi bi-gear-fill"></i>
                            <span>Manage Service</span>
                        </a>
                    </li><!-- End Manage Service -->

                    <li class="nav-item ${activeTab == "supplier" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin/admin-supplier.do?op=getall" />">
                            <i class="bi bi-building-up"></i>
                            <span>Manage Suppiler</span>
                        </a>
                    </li><!-- End Suppiler Nav -->
                </c:if>
                <c:if test="${sessionScope.user.roleID != 2}">
                    <li class="nav-item ${activeTab == "resident" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin/resident-tables.do?op=getAll" />">
                            <i class="bi bi-people-fill"></i>
                            <span>Manage Resident</span>
                        </a>
                    </li>
                    <!-- End Resident Nav -->
                </c:if>

                <c:if test="${sessionScope.user.roleID == 4}">
                    <li class="nav-item ${activeTab == "user" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin/user-tables.do?op=getAll" />">
                            <i class="bi bi-person-vcard-fill"></i>
                            <span>Manage User</span>
                        </a>
                    </li><!-- End User Nav -->
                </c:if>
                <c:if test="${user.roleID == 3}">
                    <li class="nav-item ${activeTab == "resources" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin-resource/table-resource.do?op=getAll"/>">
                            <i class="bi bi-box-fill"></i>
                            <span>Manage Block Resources</span>
                        </a>
                    </li><!-- End Resouces Nav -->
                </c:if>
                <c:if test="${user.roleID == 4}">
                    <li class="nav-item ${activeTab == "resources" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/resource/resource-list.do"/>">
                            <i class="bi bi-box-fill"></i>
                            <span>Manage Resources</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.roleID == 4}">
                    <li class="nav-item ${activeTab == "resources" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin-sales/dashboard.do"/>">
                            <i class="bi bi-cash-coin"></i>
                            <span>Revenue</span>
                        </a>
                    </li><!-- End Revenue Nav -->
                </c:if>
                <c:if test="${user.roleID != 2}">
                    <li class="nav-item ${activeTab == "order" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin/order-list.do?op=getall"/>">
                            <i class="bi bi-clipboard-fill"></i>
                            <span>Order List</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.roleID == 2}">
                    <li class="nav-item ${activeTab == "employeeOrder" ? "active" : ""}">
                        <a class="nav-link1" href="<c:url value="/admin/employee-order.do?op=getAll&AID=${sessionScope.user.AID}"/>">
                            <i class="bi bi-clipboard-fill"></i>
                            <span>Employee Order List</span>
                        </a>
                    </li>
                </c:if>
                <!-- End Orderlist Nav -->

                <c:if test="${user.roleID != 4}">
                    <li class="nav-item ${activeTab == "pendingOrder" ? "active" : ""}">
                        <a class="nav-link1 collapsed" href="<c:url value="/admin/pending-order.do?op=getall"/>">
                            <i class="bi bi-list-check"></i>
                            <span>Pending Order</span>
                        </a>
                    </li>
                </c:if>
                <!-- End Pending Order Page Nav -->

                <li class="nav-heading">Pages</li>

                <li class="nav-item ${activeTab == "profile" ? "active" : ""}">
                    <a class="nav-link1 collapsed" href="<c:url value="/user/profile.do"/>">
                        <i class="bi bi-person"></i>
                        <span>Profile</span>
                    </a>
                </li>
                <!-- End Profile Page Nav -->



            </ul>

        </aside><!-- End Sidebar-->

        <main id="main" style="margin-top: 150px" class="main">
            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
        </main><!-- End #main -->



        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                class="bi bi-arrow-up-short"></i></a>

        <input type="text" hidden id="block" value="${user.BID}">
        <!--JS SSE-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sse/sse_uo1.js"></script>
        <!-- Vendor JS Files -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/pooper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/chart.js/chart.umd.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/echarts/echarts.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/quill/quill.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>


        <!-- Template Main JS File -->
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>


    </body>

</html>
