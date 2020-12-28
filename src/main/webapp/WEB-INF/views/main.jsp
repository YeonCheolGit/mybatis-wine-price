<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <table>
        <thead>
            <tr>
                <th>이름</th>
                <th>생산지</th>
                <th>종류</th>
                <th>도수</th>
                <th>당도</th>
                <th>산도</th>
                <th>바디감</th>
                <th>어울리는 음식</th>
                <th>가격</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allWineList}" var="wineList">
                <tr>
                    <td>${wineList.name}</td>
                    <td>${wineList.origin}</td>
                    <td>${wineList.type}</td>
                    <td>${wineList.alcohol}</td>
                    <td>${wineList.sweetness}</td>
                    <td>${wineList.acid}</td>
                    <td>${wineList.body}</td>
                    <td>${wineList.food}</td>
                    <td>${wineList.price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
