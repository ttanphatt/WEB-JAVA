<%-- 
    Document   : bando
    Created on : Aug 22, 2023, 2:07:58 AM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Map with Geocoder</title>

        <!-- Thêm các thư viện cần thiết của Leaflet -->
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
        <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>

        <!-- Thêm thư viện Leaflet Control Geocoder -->
        <link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder@1.13.0/dist/Control.Geocoder.css" />
        <script src="https://unpkg.com/leaflet-control-geocoder@1.13.0/dist/Control.Geocoder.js"></script>
    </head>
    <body>
        <div id="search-bar">
            <input type="text" id="search-input" placeholder="Nhập địa chỉ hoặc tên địa điểm">
            <button class="btn-danger" id="search-button">Tìm kiếm</button>
        </div>
        <div id="map" style="width: 2000px; height: 800px;"></div>
        <script>
            var map = L.map('map').setView([10.7769, 106.7009], 12);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);

            <c:forEach items="${dsBaiViet}" var="baiViet">
            var diaChiCt = "<c:out value='${baiViet.diaChiCt}' />"; 

            L.Control.Geocoder.nominatim().geocode(diaChiCt, function (results) {
                var latlng = results[0].center;
                var marker = L.marker(latlng).addTo(map);
                marker.bindPopup("<c:out value='${baiViet.diaChiCt}' />").openPopup(); 
            });
            </c:forEach>
            var searchInput = document.getElementById('search-input');
            var searchButton = document.getElementById('search-button');

            searchButton.addEventListener('click', function () {
                var query = searchInput.value; 

                
                L.Control.Geocoder.nominatim().geocode(query, function (results) {
                    if (results && results.length > 0) {
                        var latlng = results[0].center;
                        map.setView(latlng, 15); 
                    } else {
                        alert('Không tìm thấy địa điểm.');
                    }
                });
            });
            var latlng = results[0].center;
            map.setView(latlng, 12);
        </script>
    </body>
</html>