<%-- 
    Document   : doimatkhau
    Created on : Sep 4, 2023, 10:41:51 PM
    Author     : Admins
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="change-password">
    <p class="change-password-title">Thay Đổi Mật Khẩu</p>
    <form action="/action_page.php" class="change-password-form">
        <div class="mb-3 mt-3">
            <label for="email" class="form-label">Nhập Mật Khẩu Cũ:</label>
            <input type="email" class="form-control" id="email" placeholder="Nhập Mật Khẩu Cũ" name="email"
                   required>
        </div>
        <div class="mb-3">
            <label for="pwd" class="form-label">Nhập Mật Khẩu Mới:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Nhập Mật Khẩu Mới" name="pswd"
                   required>
        </div>
        <div class="mb-3">
            <label for="pwd" class="form-label">Nhập Lại Mật Khẩu:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Nhập Lại Mật Khẩu" name="pswd"
                   required>
        </div>
        <div class="btn-change-password-div">
            <button type="submit" class="btn btn-primary btn-change-password ">Thay Đổi</button>
        </div>


    </form>
</div>

