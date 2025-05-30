<%@ page language="java" contentType="text/html; 
    charset=UTF-8" pageEncoding="UTF-8"
    import="repositories.customer.CustomerRepository, repositories.employee.EmployeeRepository, utilities.cryptography.Cryptography"
    %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="en">

        <head>

            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">

            <title>JPOS - Orders</title>

            <!-- Custom fonts for this template-->
            <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
                rel="stylesheet" type="text/css">
            <link
                href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
                rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

            <!-- Custom styles for this page -->
            <link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css"
                rel="stylesheet">

        </head>

        <body id="page-top">

            <!-- Page Wrapper -->
            <div id="wrapper">

                <!-- Sidebar -->
                <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion mh-100" id="accordionSidebar">

                    <!-- Sidebar - Brand -->
                    <a class="sidebar-brand d-flex align-items-center justify-content-center"
                        href="${pageContext.request.contextPath}/app/dashboard">
                        <div class="sidebar-brand-icon rotate-n-15">
                            <i class="fas fa-laugh-wink"></i>
                        </div>
                        <div class="sidebar-brand-text mx-3">JPOS</div>
                    </a>

                    <!-- Divider -->
                    <hr class="sidebar-divider my-0">

                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/app/orders/place"
                            class="nav-link bg-gradient-success">
                            <span class="icon text-white-50">
                                <i class="fas fa-flag"></i>
                            </span>
                            <span class="text">Place Order</span>
                        </a>
                    </li>

                    <!-- Nav Item - Dashboard -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/dashboard">
                            <i class="fas fa-fw fa-tachometer-alt"></i>
                            <span>Dashboard</span></a>
                    </li>

                    <!-- Divider -->
                    <hr class="sidebar-divider">

                    <li class="nav-item active">
                        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                            aria-expanded="false" aria-controls="collapsePages">
                            <i class="fas fa-fw fa-receipt"></i>
                            <span>Orders</span>
                        </a>
                        <div id="collapsePages" class="collapse" aria-labelledby="headingPages"
                            data-parent="#accordionSidebar">
                            <div class="bg-white py-2 collapse-inner rounded">
                                <h6 class="collapse-header">Orders</h6>
                                <a class="collapse-item active"
                                    href="${pageContext.request.contextPath}/app/orders/list">All
                                    Orders</a>
                                <a class="collapse-item"
                                    href="${pageContext.request.contextPath}/app/orders/list?pending=true">Pending
                                    Orders</a>
                            </div>
                        </div>
                    </li>

                    <!-- Nav Item - Products -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/products/list">
                            <i class="fas fa-fw fa-utensils"></i>
                            <span>Products</span>
                        </a>
                    </li>

                    <!-- Nav Item - Inventory -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/inventory/list">
                            <i class="fas fa-fw fa-list"></i>
                            <span>Inventory</span>
                        </a>
                    </li>

                    <!-- Nav Item - Employees -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/employees/list">
                            <i class="fas fa-fw fa-id-card"></i>
                            <span>Employees</span>
                        </a>
                    </li>

                    <!-- Divider -->
                    <hr class="sidebar-divider h-100 d-none d-md-block">

                    <!-- Sidebar Toggler (Sidebar) -->
                    <div class="text-center d-none d-md-inline">
                        <button class="rounded-circle border-0" id="sidebarToggle"></button>
                    </div>
                </ul>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                            <!-- Sidebar Toggle (Topbar) -->
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>

                            <!-- Topbar Navbar -->
                            <ul class="navbar-nav ml-auto">
                                <!-- Nav Item - User Information -->
                                <li class="nav-item dropdown no-arrow">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                            ${sessionScope.username}
                                        </span>
                                        <img class="img-profile rounded-circle"
                                            src="${pageContext.request.contextPath}/resources/img/undraw_profile.svg">
                                    </a>
                                    <!-- Dropdown - User Information -->
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                        aria-labelledby="userDropdown">
                                        <form action="${pageContext.request.contextPath}/app/employees/view/" method="get">
                                            <input class="d-none" type="text" name="employeeId"
                                                value="${sessionScope.employeeId}">
                                            <button type=" submit" class="dropdown-item">
                                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Profile
                                            </button>
                                        </form>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#" data-toggle="modal"
                                            data-target="#logoutModal">
                                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Logout
                                        </a>
                                    </div>
                                </li>

                            </ul>

                        </nav>
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h3 mb-0 text-gray-800">Orders</h1>
                                <a href="${pageContext.request.contextPath}/app/orders/new"
                                    class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                        class="fas fa-plus fa-sm text-white-50"></i> Create New Order</a>
                            </div>

                            <!-- DataTable -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Orders</h6>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Order ID</th>
                                                    <th>Employee</th>
                                                    <th>Payment Method</th>
                                                    <th>Status</th>
                                                    <th>Total</th>
                                                    <th>Date</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Order ID</th>
                                                    <th>Employee</th>
                                                    <th>Payment Method</th>
                                                    <th>Status</th>
                                                    <th>Total</th>
                                                    <th>Date</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <c:forEach items="${orders}" var="order">
                                                    <tr>
                                                        <td>
                                                            ${order.getOrderId()}
                                                        </td>
                                                        <td>
                                                            ${EmployeeRepository.findById(order.getEmployeeId()).getFullName()}
                                                        </td>
                                                        <td>
                                                            <c:if test="${order.getPaymentMethod() == 'cash'}">
                                                                <span class="badge badge-pill badge-success">
                                                                    Cash
                                                                </span>
                                                            </c:if>
                                                            <c:if test="${order.getPaymentMethod() == 'ewallet'}">
                                                                <span class="badge badge-pill badge-primary">
                                                                    E-Wallet
                                                                </span>
                                                            </c:if>
                                                            <c:if test="${order.getPaymentMethod() == 'debit-credit'}">
                                                                <span class="badge badge-pill badge-secondary">
                                                                    Debit/Credit Card
                                                                </span>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${order.getStatus() == 'pending'}">
                                                                <span class="badge badge-pill badge-warning">
                                                                    Pending
                                                                </span>
                                                            </c:if>
                                                            <c:if test="${order.getPaymentMethod() == 'completed'}">
                                                                <span class="badge badge-pill badge-success">
                                                                    Completed
                                                                </span>
                                                            </c:if>
                                                            <c:if test="${order.getPaymentMethod() == 'cancelled'}">
                                                                <span class="badge badge-pill badge-danger">
                                                                    Cancelled
                                                                </span>
                                                            </c:if>
                                                        </td>
                                                        <td>Php ${String.format("%,.2f", order.getTotalAmount())}</td>
                                                        <td>
                                                            <a href="${pageContext.request.contextPath}/app/orders/view?order_id=${order.getOrderId()}"
                                                                class="btn btn-primary btn-circle btn-sm">
                                                                <i class="fas fa-eye"></i>
                                                            </a>
                                                            <a href="${pageContext.request.contextPath}/app/orders/edit?order_id=${order.getOrderId()}"
                                                                class="btn btn-secondary btn-circle btn-sm">
                                                                <i class="fas fa-pen"></i>
                                                            </a>
                                                            <a class="btn btn-secondary btn-circle btn-sm" href="#"
                                                                id="actionDropdown" role="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">
                                                                <i class="fas fa-pen"></i>
                                                            </a>
                                                            <!-- Dropdown - User Information -->
                                                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                                                aria-labelledby="actionDropdown">
                                                                <form
                                                                    action="${pageContext.request.contextPath}/app/orders/view/"
                                                                    method="get">
                                                                    <input class="d-none" type="text" name="orderId"
                                                                        value="${sessionScope.orderId}">
                                            <button type=" submit" class="dropdown-item">
                                                                    <i
                                                                        class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                                                    Profile
                                                                    </button>
                                                                </form>
                                                                <div class="dropdown-divider"></div>
                                                                <a class="dropdown-item" href="#" data-toggle="modal"
                                                                    data-target="#logoutModal">
                                                                    <i
                                                                        class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                                                    Logout
                                                                </a>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- /.container-fluid -->
                    </div>
                    <!-- End of Main Content -->

                    <!-- Footer -->
                    <footer class="sticky-footer bg-white">
                        <div class="container my-auto">
                            <div class="copyright text-center my-auto">
                                <span>Copyright &copy; JPOS 2025 </span>
                            </div>
                        </div>
                    </footer>
                    <!-- End of Footer -->

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

            <!-- Logout Modal-->
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Select "Logout" below if you are ready to end your current session.
                        </div>
                        <form action="${pageContext.request.contextPath}/auth/logout" method="post">
                            <div class="modal-footer">
                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                <button class="btn btn-primary" type="submit">Logout</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Bootstrap core JavaScript-->
            <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
            <script
                src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script
                src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

            <!-- Page level plugins -->
            <script
                src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.min.js"></script>
            <script
                src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="${pageContext.request.contextPath}/resources/js/demo/datatables-demo.js"></script>

        </body>

        </html>