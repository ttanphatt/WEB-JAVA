<%-- 
    Document   : admin
    Created on : Jul 31, 2023, 2:31:08 PM
    Author     : ThanhThuyen
--%>

<%@page import="com.ntt.pojo.BaiViet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Objects" %>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<!DOCTYPE html>
<html>
    <div>
        <h2>Thống kê theo năm: </h2>
        <form action="${pageContext.request.contextPath}/admin" method="post">
            <label for="year">Chọn năm:</label>
            <select id="year" name="year">
                <c:forEach var="y" begin="2010" end="2024">
                    <option value="${y}">${y}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-danger">Thực hiện thống kê</button>
            <p>Số lượng chủ trọ: ${countChuTro}</p>
            <p>Số lượng khách hàng: ${countKhachHang}</p>
        </form>
    </div>
    <!--    <div id="bieuDoContainer">
            <canvas id="myChart"></canvas>
        </div>
            <script>
            var countKhachHang = ${countKhachHang};
            var countChuTro = ${countChuTro};
    
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Khách hàng', 'Chủ trọ'],
                    datasets: [{
                            label: 'Số lượng',
                            data: [countKhachHang, countChuTro],
                            backgroundColor: ['blue', 'green']
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>-->

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        var khachHangCount = ${countKhachHang};
        var chuTroCount = ${countChuTro};
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Loại', 'Số lượng'],
                ['Khách hàng', khachHangCount],
                ['Chủ trọ', chuTroCount]
            ]);

            var options = {
                chart: {
                    title: 'Thống kê số lượng',
                    subtitle: 'Số lượng khách hàng và chủ trọ'
                },
                bars: 'vertical'
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));


            chart.draw(data, options);
        }
    </script>

    <div id="chart_div" style="width: 100%; height: 500px;"></div>

    <table class="table">

        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Email</th>
                <th scope="col">avatar</th>
            </tr>
        </thead>
        <tbody class="bangtkngdung">
            <c:forEach items="${listNg}" var="p">
                
                <tr>
                    <th valign="middle"  scope="row">${p.id}</th>
                    <td valign="middle">${p.tenNguoiDung}</td>
                    <td valign="middle">${p.email}</td>
                    <td valign="middle"><img src="${p.avatar}" class="rounded-circle" style="width: 150px;" alt="${pageContext.request.userPrincipal.name}" /></td>
<!--                    <td>
                       
                        <c:url value="/api/deleteTaiKhoan/${p.id}" var="apiDelete"/>
                        <button class="btn btn-danger text-center" onclick="deleteTaiKhoanpr('${apiDelete}')">xóa</button>
                    </td>-->
                        <td valign="middle" class="btn-info btn " style="border-radius: 20px">${p.ngayTao}</td>
                </tr>
            </c:forEach>

        </tbody>

    </table>

    <script src="<c:url value="/js/main.js"/>"></script>





</html>