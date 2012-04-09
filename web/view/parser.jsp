<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ie.css" type="text/css" media="screen"/>
    <title>Goods  jsp page</title>
</head>
<body>
<table border="1" cellspacing=3 align="center">
    <TR>
        <TH>Category</TH>
        <TH>Subcategory</TH>
        <TH>Product Name</TH>
        <TH>Provider</TH>
        <TH>Model</TH>
        <TH>Date of Issue</TH>
        <TH>Color</TH>
        <TH>Price</TH>
    </TR>
    <c:forEach items="${table}" var="tabl">
        <tr>
            <td align="center"><c:out value="${tabl.getCategory()}"/></td>
            <td align="center"><c:out value="${tabl.getSubCategory()}"/></td>
            <td align="center"><c:out value="${tabl.getUnit()}"/></td>
            <td align="center"><c:out value="${tabl.getProvider()}"/></td>
            <td align="center"><c:out value="${tabl.getModel()}"/></td>
            <td align="center"><c:out value="${tabl.getDateOfIssue()}"/></td>
            <td align="center" style="background:${tabl.getColor()}; color:${tabl.getColor()}"
                ><c:out value="${tabl.getColor()}"/></td>
            <c:if test="${tabl.getStock()}">
                  <td align="center"><c:out value="Not in  stock"/></td>
            </c:if>
            <c:if test="${!tabl.getStock()}">
                  <td align="center"><c:out value="${tabl.getPrice()}"/></td>
            </c:if>

        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/controller?builder=dom">DOM parser</a>
<a href="${pageContext.request.contextPath}/controller?builder=sax">SAX parsers</a>
<a href="${pageContext.request.contextPath}/controller?builder=stax">StAX parsers</a>
<a href="${pageContext.request.contextPath}/index.jsp">Back</a>
</body>
</html>