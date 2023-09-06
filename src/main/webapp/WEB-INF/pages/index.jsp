<%-- 
    Document   : index
    Created on : Jul 25, 2023, 4:05:23 PM
    Author     : ThanhThuyen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="<c:url value="/css/style.css"/>" rel="stylesheet" />

<!DOCTYPE html>
<html>
    <body>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <div style="text-align: center">
                <c:url value="/timkiem" var="action"/>
                <h1>TRANG CHỦ</h1>
                <form action="${action}" method="GET">
                    <input type="text" name="address" placeholder="Nhập địa chỉ...">
                    <input type="text" name="price" placeholder="Nhập giá tiền...">
                    <input type="text" name="soNguoi" placeholder="Nhập số người...">
                    <button type="submit" class="btn btn-danger">Tìm kiếm</button>
                </form>
            </div>
        </c:if>




        <div class="bangtin">
            <c:forEach items="${baiviet}" var="t" >
                <c:if test="${t.loaiTrangThai.id==1}">

                    <c:if test="${t.loaiBaiViet.id==1}">
                        <div class="bviet">
                            <div class="bviet_anh">
                                <img src="${t.hinhAnh}"/>
                            </div>
                            <div class="bviet_ndung">
                                <table style="width:100%">
                                    <c:url value="/thtin_bviet" var="bvietAction">
                                        <c:param name="baivietId" value="${t.id}" />  
                                    </c:url>
                                    <a href="${bvietAction}" >${t.tenBaiViet}</a>
                                    <tr>
                                        <th>Khu vực:</th>
                                        <td>${t.phamViCanTim}</td>
                                    </tr>
                                    <tr>
                                        <th>Giá thuê:</th>
                                        <td>${t.giaThue}/tháng</td>
                                    </tr>
                                    <tr>
                                        <th>Diện tích:</th>
                                        <td>${t.dienTich}m2</td>
                                    </tr>
                                    <tr>

                                        <th></th>
                                        <td>   
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${t.id}" />  
                                            </c:url>
                                            <a href="${bvietAction}" class="bt-docthem" style="vertical-align:middle"> <span>Đọc thêm </span></a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                    </c:if>
                </c:if>


            </c:forEach>

        </div>
        <! TIN TIM TRO !>
        <div class="bangtin">

            <c:forEach items="${baiviet}" var="m" >
                <c:if test="${m.loaiTrangThai.id==1}">
                    <c:if test="${m.loaiBaiViet.id==2}">
                        <div class="bviet">

                            <div class="bviet_ndung">
                                <table style="width:100%">
                                    <a >${m.tenBaiViet}</a>
                                    <tr>
                                        <th>Khu vực:</th>
                                        <td>${m.phamViCanTim}</td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td>   
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${m.id}" />  
                                            </c:url>
                                            <a href="${bvietAction}" class="bt-docthem" style="vertical-align:middle"> <span>Đọc thêm </span></a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </c:if>

                </c:if>

            </c:forEach>
        </div>

    </body>
</html>