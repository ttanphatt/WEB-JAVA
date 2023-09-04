<%-- 
    Document   : dangki
    Created on : Jul 26, 2023, 4:02:50 PM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/trangchu.css"/>"rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>


<c:url value="/dangki" var="action"/>

<body>
    <div class="wrapper">
        <form:form method="post" action="${action}" modelAttribute="user"  enctype="multipart/form-data">
            <h1>Đăng kí</h1>
            <div class="input-box">
                <form:input type="text" placeholder="Họ và tên" path="tenNguoiDung" />
            </div>
            <div class="input-box">
                <form:input type="text" placeholder="Email" path="email" />
            </div>
            <div class="input-box">
                <form:input type="text" placeholder="Số điện thoại" path="sdt" />
            </div>

            <div class="input-box">
                <form:input type="text" placeholder="Tên tài khoản" path="tenTaiKhoan" />
            </div>
            <div class="input-box">
                <form:input type="password" placeholder="Mật khẩu" path="matKhau" />           
            </div>
            <div class="input-box">
                <form:input type="password" placeholder="Xác nhận lại mật khẩu" path="xacNhanMatKhau" />           
            </div>
            <div class="input-box btn-danger">
                <form:select class="role" name="role" id="role" path="idLoaiTaiKhoan">
                    <c:forEach items="${user_role}" var="c" >
                        <option value="${c.id}" selected>${c.tenLoaiTaiKhoan}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="upload-avatar">
                <form:input type="file" id="file" path="file" placeholder="Upload Avatar"/>
            </div>

            <button class="btn" type="submit" >Đăng kí</button>

        </form:form>

    </div>

</body>