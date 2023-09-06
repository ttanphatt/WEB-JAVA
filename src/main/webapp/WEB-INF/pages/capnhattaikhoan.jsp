<%-- 
    Document   : capnhattaikhoan
    Created on : Sep 6, 2023, 1:04:36 AM
    Author     : ThanhThuyen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date" %>

<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>


<!DOCTYPE html>



<html>
    <c:url value="/capnhattaikhoan" var="updateTaiKhoan"/>
    
    
    <form:form action="${updateTaiKhoan}" method="POST" modelAttribute="taikhoan" enctype="multipart/form-data" >
        
        <form:hidden path="id" />
        <div class="input-bigsize">
            <p>Tiêu đề bài đăng </p>
            <form:input type="text" id="tenbv" name="tenbv" path="tenNguoiDung" placeholder="Tên nguoi dung"/>
        </div>
        <div class="input-bigsize">
            <p>Email </p>
            <form:input type="text" id="email" name="email" path="email" placeholder="email"/>
        </div>
        <div class="input-bigsize">
            <p>SDT </p>
            <form:input type="number" id="sdt" name="sdt" path="sdt" placeholder="So dien thoai"/>
        </div>
        <div class="input-bigsize">
            <p>Tên tài khoan</p>
            <form:input type="text" id="tenTaiKhoan" name="tenTaiKhoan" path="tenTaiKhoan" placeholder="Tên tài khoan"/>
        </div>


        <div>
            <p for="file">Avatar: </p>
            <form:input path="file" type="file" id="imageFile" name="imageFile" onchange="chooseFile(this)"  accept="image/jpg, image/jpeg, image/png"/>
        </div>

        <button class="btn btn-info " type="sumit">Cập nhật</button>

    </form:form>
</html>