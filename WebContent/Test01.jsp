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
String ingredient_xml = directoryFinder.directory + "ingredient.xml";
Map<String, List<Bb_IngredientDto>> ingredient_map = xmlParser.xml_getIngredient(ingredient_xml);
List<Bb_IngredientDto> bun_list = ingredient_map.get("bun_list");
List<Bb_IngredientDto> patty_list = ingredient_map.get("patty_list");
List<Bb_IngredientDto> vege_list = ingredient_map.get("vege_list");
List<Bb_IngredientDto> etc_list = ingredient_map.get("etc_list");
%>

<p><%=ingredient_xml%></p>

<h1>번 리스트</h1>
<%for (int i=0; i< bun_list.size(); i++){%>
<p><%=bun_list.get(i).toString() %></p>
<%}%>

<h1>패티 리스트</h1>
<%for (int i=0; i< patty_list.size(); i++){%>
<p><%=patty_list.get(i).toString() %></p>
<%}%>

<h1>야채 리스트</h1>
<%for (int i=0; i< vege_list.size(); i++){%>
<p><%=vege_list.get(i).toString() %></p>
<%}%>

<h1>기타 리스트</h1>
<%for (int i=0; i< etc_list.size(); i++){%>
<p><%=etc_list.get(i).toString() %></p>
<%}%>

<script type="text/javascript">

</script>

</body>
</html>