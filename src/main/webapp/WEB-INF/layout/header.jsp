<%-- 
Document   : header
Created on : Jul 25, 2023, 9:33:27 PM
Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class=" navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value ="/" />">Phòng Trọ Sinh Viên</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="<c:url value ="/canhan" />">
            <img src="${taikhoan.avatar}" alt="${pageContext.request.userPrincipal.name}" style="width:80px;" class="rounded-pill"> 
        </a>
        <div class="collapse navbar-collapse" id="mynavbar">

            <ul class="navbar-nav me-auto">
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/dangnhap"/>"> Đăng nhập </a>                       
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/dangki"/>"> Đăng kí </a>                       
                    </li>
                </c:if>

                <c:if test="${pageContext.request.userPrincipal.name != null}">

                    <li class="nav-item">
                        <a style="color: red" class="nav-link" href="<c:url value ="/canhan" />">Xin chào, ${pageContext.request.userPrincipal.name} </a>                       
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/canhan"/> ">Trang cá nhân</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/dangbai"/> ">Đăng bài</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)">Thông báo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/logout" />"> Đăng xuất </a>                       
                    </li>


                </c:if>



            </ul>

            <form class="d-flex">
                <input class="form-control me-2" type="text" placeholder="Tìm kiếm">
                <button class="btn btn-primary" type="button">Tìm</button>
            </form>
        </div>
    </div>
</nav>

