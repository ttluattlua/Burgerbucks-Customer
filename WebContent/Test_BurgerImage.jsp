<%@page import="java.util.ArrayList"%>
<%@page import="Util.burgerImageEditor"%>
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
List<Bb_IngredientDto> a = new ArrayList();
a.add(new Bb_IngredientDto(0,"빵",0,300,300,0,directoryFinder.directory + "/Ingredient/Ingredient_Black.png"));
a.add(new Bb_IngredientDto(0,"빵",0,300,300,0,directoryFinder.directory + "/Ingredient/Ingredient_Black.png"));
a.add(new Bb_IngredientDto(0,"빵",0,300,300,0,directoryFinder.directory + "/Ingredient/Ingredient_Chicken.png"));
a.add(new Bb_IngredientDto(0,"빵",0,300,300,0,directoryFinder.directory + "/Ingredient/Ingredient_Tomato.png"));
a.add(new Bb_IngredientDto(0,"빵",0,300,300,0,directoryFinder.directory + "/Ingredient/Ingredient_Cheese.png"));
a.add(new Bb_IngredientDto(0,"빵",0,300,300,0,directoryFinder.directory + "/Ingredient/Ingredient_Lettuce.png"));
burgerImageEditor.addBurgerImage(a);
%>

<p>Burger Image Test</p>


</body>
</html>