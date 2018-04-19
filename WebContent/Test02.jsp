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
%>

<button type="button" id="addIngredient">재료 추가 버튼</button>
<br>
<br>
<br>
<button type="button" id="updIngredient">재료 수정 버튼</button>
<br>
<br>
<br>
<button type="button" id="delIngredient">재료 삭제 버튼</button>

<script>
<%Bb_IngredientDto sample = null;%>

$(document).ready(function(){
  $("#addIngredient").click(function(){
    alert("재료 추가");
    <%
    sample = new Bb_IngredientDto(11, "아무거나", 2, 5000, 300, 0, "이미지위치");
    xmlParser.xml_addIngredient(ingredient_xml, sample);
    %>
  });
  
  $("#updIngredient").click(function(){
    alert("재료 수정");
    <%
	  sample = new Bb_IngredientDto(11, "바꿔봤어요", 3, 3000, 300, 0, "이미지위치");
	  xmlParser.xml_updIngredient(ingredient_xml, sample);
    %>
  });
  
  $("#delIngredient").click(function(){
    alert("재료 삭제");
    <%
    sample = new Bb_IngredientDto(11, "삭제했어요", 2, 5000, 300, 0, "이미지위치");
	  xmlParser.xml_delIngredient(ingredient_xml, sample);
    %>
  });
});
</script>

</body>
</html>