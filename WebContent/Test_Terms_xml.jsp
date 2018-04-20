<%@page import="Dto.Bb_IngredientDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="Util.xmlParser"%>
<%@page import="Util.directoryFinder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<%
String ingredient_xml = directoryFinder.directory + "Terms.xml";
String terms_text = xmlParser.xml_getTerms("D:/Burgerbucks-Customer/WebContent/XML/Terms.xml");
%>

<p><%=ingredient_xml%></p>

<h1>이용약관</h1>
<p><%=terms_text %></p>




</script>

</body>
</html>