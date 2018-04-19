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

<p>재료 수정</p>

<%
  String ingredient_xml = directoryFinder.directory + "ingredient.xml";
  System.out.println("xml file directory : " + ingredient_xml);

  Bb_IngredientDto sample = new Bb_IngredientDto(11, "바뀌어라", 3, 3000, 200, 0, "이미지위치바뀜");
  xmlParser.xml_updIngredient(ingredient_xml, sample);
%>

</body>
</html>