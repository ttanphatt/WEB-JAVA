<%-- 
    Document   : DangBai
    Created on : Aug 6, 2023, 8:58:16 PM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<html>

    <section class="body-dangbai">
        <h1 class="text-center text-danger">Đăng bài </h1>
        <c:url value="/dangbai" var="action"/>

        <div class="dangbai">

            <!-- PHẦN ĐĂNG BÀI -->

            <div class="dangbai-col1">
                <form:form method="post" action="${action}" var="p" modelAttribute="baiviet" enctype="multipart/form-data" >
                    <div class="catngang">
                        <h4 style="color: white; padding: 10px;">THÔNG TIN CƠ BẢN</h4>
                    </div>
                    <div class="dangbai-tincodinh">
                        <c:if test="${nguoidung.idLoaiTaiKhoan.id==2}">
                            <p>Danh mục tin đăng: Tin cho thuê</p>
                            <form:input type="hidden" path="loaiBaiViet" value="1" readonly="true"  cssClass="form -control"/>
                        </c:if>

                        <c:if test="${nguoidung.idLoaiTaiKhoan.id==3}">
                            <p>Danh mục tin đăng: Tin tìm trọ</p>
                            <form:input type="hidden" path="loaiBaiViet" value="2" readonly="true"  cssClass="form -control"/>
                        </c:if>

                        <p>Người đăng tin:                 
                            <form:input type="text" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>
                        </p>
                    </div>


                    <div class="dangbai-tinnhaplieu">
                        <div class="input-bigsize">
                            <p>Tiêu đề bài đăng </p>
                            <form:input type="text" id="tenbv" name="tenbv" path="tenBaiViet" placeholder="Tiêu đề bài đăng"/>
                        </div>

                        <div class="input-bigsize">
                            <p>Ngày đăng: </p>
                            <form:input type="text" id="ngaydangbv" name="ngaydangbv" path="ngayDang"  placeholder="Format yyyy/mm/dd (VD: 2023-05-22)" disabled="true"/>
                        </div>

                        <!--BÀI CỦA CHỦ TRỌ-->
                        <c:if test="${nguoidung.idLoaiTaiKhoan.id==2}">
                            <div class="input-smallsize">
                                <div class="input-smallsize1">                            
                                    <p>Khu vực(quận/huyện/thành phố) </p>
                                    <form:input type="text" id="khuvucbv" name="khuvucbv" path="phamViCanTim" placeholder="Nhập khu vực (VD: Gò Vấp)"/>
                                </div>

                                <div class="input-smallsize1">
                                    <p>Giá cho thuê </p>
                                    <form:input type="text" id="giathuebv" name="giathuebv" path="giaThue" placeholder="Nhập giá cho thuê (VD: 3000000)"  /> 

                                </div>
                            </div>
                            <div class="input-smallsize">
                                <div class="input-smallsize2">
                                    <p>Số lượng người </p>
                                    <form:input type="text" id="slnguoibv" name="slnguoibv" path="soNguoi" placeholder="Nhập số người (VD: 3)" /> 
                                </div>

                                <div class="input-smallsize2">
                                    <p>Diện tích phòng (m2) </p>
                                    <form:input type="text" id="dientichbv" name="dientichbv" path="dienTich" placeholder="Nhập diện tích (VD: 30)"/>
                                </div>

                            </div>


                            <div class="input-bigsize">                            
                                <p>Địa chỉ chi tiết: </p>
                                <form:input type="text" id="dchibv" name="dchibv" path="diaChiCt"/>
                            </div>

                            <script>
                                function chooseFile(fileInput) {
                                    if (fileInput.files && fileInput.files[0]) {
                                        var reader = new FileReader();

                                        reader.onload = function (e) {
                                            $('#image').attr('src', e.target.result);
                                        }
                                        reader.readAsDataURL(fileInput.files[0]);
                                    }
                                }

                            </script>
                            <div>  
                                <p for="file">Hình ảnh phòng trọ: </p>
                                <img src =""  id="image" width="200" height="200"/>
                                <form:input path="file" multiple="multiple" type="file" id="imageFile" name="imageFile" onchange=   "chooseFile(this)" accept="image/jpg, image/jpeg, image/png"/>
                            </div>
                        </c:if>

                        <!--BÀI CỦA KHÁCH HÀNG-->
                        <c:if test="${nguoidung.idLoaiTaiKhoan.id==3}">
                            <div>                            
                                <p>Phạm vi cần tìm: </p>
                                <form:input type="text" id="khuvucbv" name="khuvucbv" path="phamViCanTim"/>
                            </div>
                        </c:if>
                        <div class="input-bigsize">
                            <p>Mô tả chi tiết </p>     
                            <form:textarea  type="text" id="motabv" name="motabv"  path="noiDung" placeholder="Mô tả chi tiết"/>

                        </div>
                    </div>    
                    <div class="input-box btn-danger">
                        <form:select class="role" name="role" id="role" path="loaiTrangThai">
                            <c:forEach items="${trangThai_role}" var="c" >
                                <option value="${c.id}" selected>${c.tenLoaiTrangThai}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <br></br>
                    <div class="form-group">
                        <input type="submit" value="Đăng tải" class="btn btn-danger"/>
                    </div>
                </form:form>
            </div>

            <!-- PHẦN HƯỚNG DẪN -->
            <div class="dangbai-col2">
                <div class="catngang">
                    <h4 style="color: white; padding: 10px;">HƯỚNG DẪN</h4>
                </div>
                <div class="">

                    <pre>Để đăng tin bán nhà trên mạng hay cách đăng tin cho thuê nhà, nội dung tin của bạn cần đầy đủ nội dung sau:

- Tiêu đề.

- Nội dung

- Thông tin liên hệ

- Hình ảnh

Làm thế nào để đăng tin thuê nhà nội dung tốt nhất? Bạn cần chỉ rõ những ưu điểm vị trí của ngôi nhà, gần những tiện ích công cộng nào (trường học, trung tâm thương mại, bệnh viện, công viên, tuyến đường lớn, …).

Cung cấp đầy đủ thông số nhà như diện tích thực, diện tích xây dựng, hướng nhà, mặt tiền, cách đường cái bao nhiêu m, số tầng, số phòng, giá bán (bao gồm phí sang tên hay không).

Đồng thời thông tin giấy tờ pháp lý có sổ đỏ, sổ hồng, …
                    </pre>
                </div>
            </div>
        </div>



    </section>


</html>