<%-- 
    Document   : adminquarter
    Created on : Aug 26, 2023, 1:49:21 PM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <form action="${pageContext.request.contextPath}/adminquarter" method="post">
        <label for="year">Chọn năm:</label>
        <select id="year" name="year">
            <c:forEach var="year" begin="2020" end="2030">
                <option value="${year}">${year}</option>
            </c:forEach>
        </select>
        <br>
        <label for="quarter">Chọn quý:</label>
        <select id="quarter" name="quarter">
            <option value="1">Quý 1</option>
            <option value="2">Quý 2</option>
            <option value="3">Quý 3</option>
            <option value="4">Quý 4</option>
        </select>
        <br>
        <button type="submit" class="btn btn-danger">Thực hiện thống kê</button>

    </form>
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

    <div id="chart_div" style="width: 100%; height: 300px;"></div>

    <table class="table">

        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Email</th>
                <th scope="col">Avatar</th>
                <th scope="col">Ngày tạo</th>
            </tr>
        </thead>
        <tbody class="bangtkngdung">
            <c:forEach items="${listNg}" var="p">
                <tr>
                    <th valign="middle"  scope="row">${p.id}</th>
                    <td valign="middle">${p.tenNguoiDung}</td>
                    <td valign="middle">${p.email}</td>
                    <td valign="middle"><img src="${p.avatar}" class="rounded-circle" style="width: 150px;" alt="${pageContext.request.userPrincipal.name}" /></td>
                    <td valign="middle" class="btn-info btn " style="border-radius: 20px">${p.ngayTao}</td>
                </tr>
            </c:forEach>

        </tbody>

    </table>

</html>