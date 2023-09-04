<%-- 
    Document   : thtin_bviet
    Created on : Aug 12, 2023, 2:34:35 PM
    Author     : Admins
--%>


<%@page import="com.ntt.pojo.BaiViet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Objects" %>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<c:url value="/thtin_bviet_bl" var="action">
    <c:param name="baivietId" value="${BaiViet.id}" />  
</c:url>
<c:url value="/thtin_bviet_fl" var="actionfl">
    <c:param name="baivietId" value="${BaiViet.id}" />  
</c:url>
<section class="chitiettin" >
    <div class="chitiettin-col1">
        <div class="ct-anh">
            <center>
                <img src="${BaiViet.hinhAnh}"" style="width:100%" > 

            </center>
        </div>
        <div class="ndung-chitiet">
            <h4 style="color: tomato; font-weight: bold">${BaiViet.tenBaiViet}</h4>
            <c:if test="${BaiViet.loaiBaiViet.id==1}">
                <p>Địa chỉ: ${BaiViet.diaChiCt}</p>
                <div class="chitiet-3tt">
                    <p>Giá: ${BaiViet.giaThue}</p>
                    <p>Diện tích: ${BaiViet.dienTich}</p>
                    <p> #${BaiViet.id}</p>
                </div>
            </c:if>
            <h4>Thông tin mô tả:</h4>
            <p>${BaiViet.noiDung}</p>

            <!--DAC DIEM TIN DANG-->
            <h4>Đặc điểm tin đăng:</h4>
            <div class="dacdiemtin">
                <table style="width:100%">
                    <tr>
                        <th>Mã tin:</th>
                        <td>#${BaiViet.id}</td>
                    </tr>
                    <tr>
                        <c:if test="${BaiViet.loaiBaiViet.id==1}">
                            <th>Khu vực cho thuê trọ:</th>

                        </c:if>
                        <c:if test="${BaiViet.loaiBaiViet.id==2}">
                            <th>Khu vực cần tìm trọ:</th>
                            </c:if>
                        <td>${BaiViet.phamViCanTim}</td>
                    </tr>
                    <tr>
                        <th>Ngày đăng:</th>
                        <td>${BaiViet.ngayDang}</td>
                    </tr>
                    <tr>
                        <th>Ngày hết hạn:</th>
                        <td>${BaiViet.ngayDang}</td>
                    </tr>
                </table>
            </div>

        </div>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <form:form method="post" action="${action}" var="p" modelAttribute="binhluan" enctype="multipart/form-data" >

                <form:input type="text" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>
                <form:input type="text" id="file" path="idBaiVietBinhLuan" value="${BaiViet.id}"  readonly="true"  cssClass="form -control"/>
                <form:input type="text" path="noiDung"/>
                <input type="submit" value="Bình Luận" class="btn btn-danger" disabled/>
            </form:form>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form:form method="post" action="${action}" var="p" modelAttribute="binhluan" enctype="multipart/form-data" >

                <form:input type="text" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>
                <form:input type="text" id="file" path="idBaiVietBinhLuan" value="${BaiViet.id}"  readonly="true"  cssClass="form -control"/>

                <form:input type="text" path="noiDung"/>
                <input type="submit" value="Bình Luận" class="btn btn-danger"/>
            </form:form>
        </c:if>
    </div>


    <div class="chitiettin-col2">
        <div class="ct-thtinngdung">
            <center>
                <img src="${BaiViet.idNguoiDung.avatar}" class="rounded-circle" style="width: 150px;" alt="${pageContext.request.userPrincipal.name}" />
                <p>${BaiViet.idNguoiDung.tenNguoiDung}</p>
                <p>${BaiViet.idNguoiDung.sdt}</p>
            </center>
        </div>
        <div class="ct-tinnoibat">
            <center><p>Tin vừa đăng</p></center>
        </div>
        <c:forEach items="${followlist}" var="list">
            <c:if test="${list.idChuTro.id.toString() eq BaiViet.idNguoiDung.id.toString()}">
                <c:url value="/api/thtin_bviet/${list.id}" var="apiDelete"/>
                <button class="btn btn-danger text-center" onclick="deleteFollowpr('${apiDelete}')">Hủy Follow</button>
                <button class="btn btn-danger" disabled="">Follow</button>

            </c:if>

        </c:forEach>

        <form:form method="post" action="${actionfl}" var="p" modelAttribute="follow" >
            <c:if test="${nguoidung.idLoaiTaiKhoan.id==3}">
                <c:choose>
                    <c:when test="${empty flChuTro}">
                        <button class="btn btn-danger">Follow</button>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${empty followlist}">
                                <button class="btn btn-danger">Follow</button>
                            </c:when>
                            <c:otherwise>
                                <c:set var="shouldShowFollowButton" value="true" />
                                <c:forEach items="${flChuTro}" var="chuTro">
                                    <c:if test="${shouldShowFollowButton && followlist.contains(chuTro)}">
                                        <c:set var="shouldShowFollowButton" value="false" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${shouldShowFollowButton}">
                                    <button class="btn btn-danger">Follow</button>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:if>
                                    




            <form:input type="hidden" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>
            <form:input type="hidden" id="file" path="idChuBaiViet" value="${BaiViet.idNguoiDung.id}"  readonly="true"   cssClass="form -control"/>
        </form:form>



    </div>
    <div>
        <c:forEach items="${binhluans}" var="b">
            <div class="comtent row" style="border-width: 20px">
                <div class="col-md-1">
                    <img src="${b.idNguoiDung.avatar}" style="width:80px" />
                </div>
                <div>
                    <p>${b.idNguoiDung.tenNguoiDung}</p>  
                    <p>${b.noiDung}</p>
                    <p class="commentDate">${b.ngayBinhLuan}</p>
                </div>  
            </div>
        </c:forEach>
    </div>
    <script>
        window.onload = function () {
            let dates = document.getElementsByClassName("commentDate")
            for (let i = 0; i < dates.length; i++)
            {
                dates[i].innerText = moment(dates[i].innerText).fromNow()
            }
        }

    </script>
    <script src="<c:url value="/js/main.js"/>"></script>


</section>
