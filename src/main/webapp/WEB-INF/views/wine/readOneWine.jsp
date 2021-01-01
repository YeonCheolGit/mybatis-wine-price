<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <table>
            <thead>
            <tr>
                <th></th>
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
            <tr>
                <td>
                    ${readOneWine.number}
                </td>
                <td>
                    ${readOneWine.name}
                <td>
                    ${readOneWine.origin}
                </td>
                <td>
                    ${readOneWine.alcohol}
                </td>
                <td>
                    ${readOneWine.sweetness}
                </td>
                <td>
                    ${readOneWine.acid}
                </td>
                <td>
                    ${readOneWine.body}
                </td>
                <td>
                    ${readOneWine.food}
                </td>
                <td>
                    ${readOneWine.price}
                </td>

            </tr>
            </tbody>
        </table>

    </div>
</body>
</html>
