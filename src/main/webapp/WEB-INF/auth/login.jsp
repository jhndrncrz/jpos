<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>

            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">

            <title>JPOS - Login</title>

            <!-- Custom fonts for this template-->
            <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
                rel="stylesheet" type="text/css">
            <link
                href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
                rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

        </head>

        <body class="bg-gradient-primary">

            <div class="container">

                <!-- Outer Row -->
                <div class="row min-vh-100 align-items-center justify-content-center">

                    <div class="col-xl-10 col-lg-12 col-md-9">

                        <div class="card o-hidden border-0 shadow-lg my-5">
                            <div class="card-body p-0">
                                <!-- Nested Row within Card Body -->
                                <div class="row">
                                    <div class="col">
                                        <div class="p-5">
                                            <div class="text-center">
                                                <h1 class="h4 text-gray-900 mb-4">
                                                    Welcome to
                                                    <span class="fw-bold">
                                                        JPOS!
                                                    </span>
                                                </h1>
                                            </div>
                                            <form class="user" action="${pageContext.request.contextPath}/auth/login"
                                                method="post">
                                                <div class="form-group">
                                                    <input type="text" class="form-control form-control-user"
                                                        id="exampleInputUsername" aria-describedby="usernameHelp"
                                                        placeholder="Username..." name="username">
                                                </div>
                                                <div class="form-group">
                                                    <input type="password" class="form-control form-control-user"
                                                        id="exampleInputPassword" placeholder="Password..."
                                                        name="password">
                                                </div>
                                                <div class="form-group">
                                                    <div class="custom-control custom-checkbox small">
                                                        <input type="checkbox" class="custom-control-input"
                                                            id="customCheck" name="remember-me" checked>
                                                        <label class="custom-control-label" for="customCheck">Remember
                                                            Me</label>
                                                    </div>
                                                </div>
                                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                                    Login
                                                </button>
                                                <!-- <hr>
                                                    <a href="index.html" class="btn btn-google btn-user btn-block">
                                                        <i class="fab fa-google fa-fw"></i> Login with Google
                                                    </a>
                                                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                                        <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                                                    </a> -->
                                            </form>
                                            <c:if test="${not empty error}">
                                                <hr>
                                                <div class="alert alert-danger" role="alert">
                                                    ${error}
                                                </div>
                                            </c:if>
                                            <!-- <hr>
                                            <div class="text-center">
                                                <a class="small" href="forgot-password.html">Forgot Password?</a>
                                            </div>
                                            <div class="text-center">
                                                <a class="small" href="register.html">Create an Account!</a>
                                            </div> -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

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

        </body>

        </html>