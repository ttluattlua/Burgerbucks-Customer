<%@page import="Util.txtReader"%>
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
List<String> terms_text = txtReader.txt_readTerms("D:/Burgerbucks-Customer/WebContent/Terms/Terms.txt");
%>

<h1>이용약관</h1>
<%for(int i=0; i<terms_text.size(); i++){ %>
<p><%=terms_text.get(i) %></p>
<%} %>




</script>

</body>
</html>