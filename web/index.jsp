<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ie.css" type="text/css" media="screen"/>
    <title>Simple jsp page</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?builder=dom">DOM parser</a>
<a href="${pageContext.request.contextPath}/controller?builder=sax">SAX parsers</a>
<a href="${pageContext.request.contextPath}/controller?builder=stax">StAX parsers</a>
<a href="${pageContext.request.contextPath}/XSLTServlet">XSLT(Tree variant)</a>
</body>
</html>