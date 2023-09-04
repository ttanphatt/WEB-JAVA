<%-- 
    Document   : chutro
    Created on : Jul 31, 2023, 12:45:22 PM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <form:form modelAttribute="taikhoan">
        <section class="body-trangcanhan">
            <div class="trangcanhan">
                <div class="trangcanhan-col1">
                    <div class="catngang">
                        <h4 style="color: white; padding: 10px;">THÔNG TIN CÁ NHÂN</h4>
                    </div>
                    <div class="thongtincanhan">
                        <center>
                            <img src="${taikhoan.avatar}" class="rounded-circle" style="width: 150px;" alt="${pageContext.request.userPrincipal.name}" />
                            <p><c:if test="${taikhoan.idLoaiTaiKhoan.id == 2}">CHỦ TRỌ</c:if></p>
                            <p><c:if test="${taikhoan.idLoaiTaiKhoan.id == 3}">KHÁCH HÀNG</c:if></p>
                            <p>${taikhoan.tenNguoiDung}</p>
                            <p>${taikhoan.sdt}</p>

                            <div class="canhanhoa">
                                <c:url value="/doimatkhau" var="userAction">
                                            
                                        </c:url>
                                <a href="#">Cập nhật</a>
                                <a href="${userAction}">Đổi mật khẩu</a>
                            </div>
                        </center>
                    </div>
                </div>
                <div class="trangcanhan-col2">
                    <div class="catngang">
                        <h4 style="color: white; padding: 10px;">TIN ĐĂNG</h4>
                    </div>
                    <div>
                        <c:forEach items="${baiviet}" var="p">
                            <div class="tincanhan">
                                <div class="tincanhan-anh">
                                    <img src="${p.hinhAnh}"/>
                                </div>
                                <div class="tincanhan-noidung">
                                    <table style="width:100%">
                                        <c:url value="/thtin_bviet" var="bvietAction">
                                            <c:param name="baivietId" value="${p.id}" />  
                                        </c:url>
                                        <a href="${bvietAction}" >${p.tenBaiViet}</a>
                                        <tr>
                                            <th>Khu vực:</th>
                                            <td>${p.phamViCanTim}</td>
                                        </tr>
                                        <tr>
                                            <th>Giá thuê:</th>
                                            <td>${p.giaThue}/tháng</td>
                                        </tr>
                                        <tr>
                                            <th>Ngày đăng:</th>
                                            <td>${p.ngayDang}</td>
                                        </tr>
                                        <tr>
                                            <th>Địa chỉ:</th>
                                            <td>${p.diaChiCt}</td>
                                        </tr>
                                        <tr>
                                            <th></th>
                                            <td>   
                                                <c:url value="/capnhat" var="bvietAction">
                                                    <c:param name="baivietId" value="${p.id}" />  
                                                </c:url>
                                                <a href="${bvietAction}" class="btn btn-success" style="vertical-align:middle"> Cập nhật</a>
                                            </td>
                                            <td>   
                                                <c:url value="/capnhat1" var="api">
                                                    <c:param name="baivietId" value="${p.id}" />  
                                                </c:url>
                                                <button  onclick="deleteBViet('${api}')" class="btn btn-danger">Xóa</button>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
    </form:form>
</html>
<script src="<c:url value="/js/main.js" />"></script>
