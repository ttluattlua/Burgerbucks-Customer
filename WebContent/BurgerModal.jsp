<%@page import="Util.xmlParser"%>
<%@page import="Dto.Bb_IngredientDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="Util.directoryFinder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="CSS/Necessary.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <title>Insert title here</title>

</head>

<body style="background-color: #f3f3f3;">

  <!-- Load Ingredient Script -->
  <% 
    String ingredient_xml = directoryFinder.directory + "/XML/ingredient.xml";
    Map<String, List<Bb_IngredientDto>> ingredient_map = xmlParser.xml_getIngredient_classified(ingredient_xml);
    List<Bb_IngredientDto> bun_list = ingredient_map.get("bun_list");
    List<Bb_IngredientDto> patty_list = ingredient_map.get("patty_list");
    List<Bb_IngredientDto> vege_list = ingredient_map.get("vege_list");
    List<Bb_IngredientDto> etc_list = ingredient_map.get("etc_list");
    System.out.println("bun_list 사이즈  : " + bun_list.size() );
    System.out.println("patty_list 사이즈  : " + patty_list.size() );
    System.out.println("vege_list 사이즈  : " + vege_list.size() );
    System.out.println("etc_list 사이즈  : " + etc_list.size() );
  %>
  
  <!-- Button Div -->
  <div style="background: linear-gradient(#5b5b5b, #333333); padding: 0; margin: 0 auto; height:80px; width: 1000px; border: 1px solid #999999; border-radius: 4px; box-shadow: 0px 4px 4px #bbbbbb;">
    <div style="background: linear-gradient(#ad3b29, #771e10); padding:10px; height:100%; width: 80px; border-right: 2px ridge #7a7979; display: block; float: left; border-top-left-radius: 3px; border-bottom-left-radius: 3px;">
      <a href="#">
        <img src="./UI/BB_Symbol.png" style="width: 60px; height: 60px; margin: 0 auto;">
      </a>
    </div>
    <div style="height:100%; width: 160px; border-right: 2px ridge #7a7979; display: block; float: left;" align="center">
      <a class="hover_a" style="color: #ffe25f;"  href="#">
        <h4 style="size: 28px; margin-top: 30px;">메뉴</h4>
      </a>
    </div>
    <div style="height:100%; width: 160px; border-right: 2px ridge #7a7979; display: block; float: left;" align="center">
      <a class="hover_a" style="color: #ededed;" href="#" data-toggle="modal" data-target="#burgerModal" onclick="loadBurger()">
        <h4 style="size: 28px; margin-top: 30px;">DIY햄버거</h4>
      </a>
    </div>
    <div class="dropdown" style="height:100%; width: 160px; border-right: 2px ridge #7a7979; display: block; float: left;" align="center">
      <a class="hover_a dropdown-toggle" data-toggle="dropdown" >
        <h4 style="cursor:pointer; size: 28px; margin-top: 30px; color: #ededed; size: 28px; margin-top: 30px;">마이 페이지 ▼</h4>
      </a>
      <ul class="dropdown-menu">
        <li><a href="#">주문 내역</a></li>
        <li><a href="#">즐겨찾기</a></li>
        <li><a href="#">주소록</a></li>
        <li><a href="#">내 정보</a></li>
      </ul>
    </div>
    <div class="dropdown" style="height:100%; width: 160px; border-right: 2px ridge #7a7979; display: block; float: left;" align="center">
      <a class="hover_a dropdown-toggle" data-toggle="dropdown" >
        <h4 style="cursor:pointer; size: 28px; margin-top: 30px; color: #ededed; size: 28px; margin-top: 30px;">기타 페이지 ▼</h4>
      </a>
      <ul class="dropdown-menu">
        <li><a href="#">이용약관</a></li>
        <li><a href="#">원산지 표시</a></li>
        <li><a href="#">FAQ</a></li>
        <li><a href="#">문의하기</a></li>
      </ul>
    </div>
  </div>
  
  <!-- DIY Burger Modal -->
  <div class="modal fade" id="burgerModal" role="dialog" align="center" style="margin: auto; margin-top:100px; overflow: hidden;">
    
    <!-- Modal Part -->
    <div style="height:600px; width: 800px; background-color: #f3f3f3; border-radius: 4px;">
      
      <!-- Modal Explanation -->
      <div style="width: 800px; height: 40px; padding: 10px;">
        <label style="float: left;">나만의 햄버거 제작</label>
        <a onclick="initBurgerModal()" data-dismiss="modal" style="float: right;"><label style="cursor:pointer;">X</label></a>
      </div> 
      
      <!-- Modal Contents -->
      <div style="width: 800px; height: 560px; padding:10px;">
        <form action="BurgerController" method="get" name="burgerInfo">
          <input id="saveburgertype" type="hidden" name="command" value="addBurger">
          
          <!-- Name input-->
          <div style="width: 780px; height: 40px;">
            <div class="input-group" style="width: 300px; float:left;">
              <span class="input-group-addon">이름</span> 
              <input id="burgername" name="burgername" type="text" class="form-control" 
              placeholder="햄버거의 이름을 입력해주세요" required>
            </div>
          </div>
          
          <!-- Burger Ingredient -->
          <div style="height:440px; width: 780px; margin-top:10px;">
          
            <!-- Ingredient List -->
            <div style="height:440px; width: 320px; float: left;">
              <div id="ingredientList" style="border:1px solid lightgray; border-radius: 4px; height:440px; width: 320px; vertical-align: bottom; display: table-cell;" align="center">            
	              
	              <div style="height:30px; width: 318px; margin-bottom: 30px; z-index: 0; position: relative; display: block;">
	                <div style="height:30px; width: 140px; float:left;" align="center">
	                  <img src="<%=bun_list.get(0).getImage_Src()%>" id="bread_down" style="height: 50px; width: 100px; margin-top:-10px;">
	                </div>
	                <div style="height:30px; width: 120px; float:left; padding-top:5px;" align="center">
	                  <p id="bread_down_name"><%=bun_list.get(0).getName()%></p>
	                </div>
	                <div style="height:30px; width: 58px; float:left; padding-top:5px;" align="center">
	                </div>
	              </div>
	              
	             </div>
            </div>
            
            <!-- Ingredient Select -->
            <div style="border:1px solid lightgray; border-radius: 4px; height:440px; width: 450px; float: right; display: inline-block;">
              
              <!-- Bread and Meat Select -->
              <div style="height:220px; width: 440px;">
                <div class="form-group" style="height:220px; width: 220px; float:left; text-align: left; padding:10px;">
                  <label class="control-label">햄버거 번</label><br>
                  <select class="form-control" id='bunSelect' name='bunSelect' style="width: 180px; float:left;" size="8">
                    <%for (int i=0; i<bun_list.size(); i++) { %>
                        <option onclick="changeBun(this)" value="<%=bun_list.get(i).getImage_Src()%>" id="<%=bun_list.get(i).getSeq()%>"><%=bun_list.get(i).getName()%></option>
									  <%}%>
                  </select>
                </div>
                
                <div class="form-group" style="height:220px; width: 220px; float:left; text-align: left; padding:10px;">
                  <label class="control-label">패티</label><br>
                  <select class="form-control" id='pattySelect' name='pattySelect' style="width: 180px; float:left;" size="8">
                    <%for (int i=0; i<patty_list.size(); i++) { %>
                        <option onclick="addIngredient(this)" value="<%=patty_list.get(i).getImage_Src()%>" id="<%=patty_list.get(i).getSeq()%>"><%=patty_list.get(i).getName()%></option>
                    <%}%>
                  </select>
                </div>
              </div>
              
              <!-- Lettuce and Etc Select -->
              <div style="height:220px; width: 440px;">
                <div class="form-group" style="margin-top:-15px; height:220px; width: 220px; float:left; text-align: left; padding:10px;">
                  <label class="control-label">채소</label><br>
                  <select class="form-control" id='vegeSelect' name='vegeSelect' style="width: 180px; float:left;" size="8">
                    <%for (int i=0; i<vege_list.size(); i++) { %>
                        <option onclick="addIngredient(this)" value="<%=vege_list.get(i).getImage_Src()%>" id="<%=vege_list.get(i).getSeq()%>"><%=vege_list.get(i).getName()%></option>
                    <%}%>
                  </select>
                </div>
                
                <div class="form-group" style="margin-top:-15px; height:220px; width: 220px; float:left; text-align: left; padding:10px;">
                  <label class="control-label">기타</label><br>
                  <select class="form-control" id='etcSelect' name='etcSelect' style="width: 180px; float:left;" size="8">
                    <%for (int i=0; i<etc_list.size(); i++) { %>
                        <option onclick="addIngredient(this)" value="<%=etc_list.get(i).getImage_Src()%>" id="<%=etc_list.get(i).getSeq()%>"><%=etc_list.get(i).getName()%></option>
                    <%}%>
                  </select>
                </div>
              </div>
              
            </div>
          </div>
          
          <div style="width: 780px; margin-top:10px; height: 40px; padding-top: 2px;" align="center">
            <button id="save_burger" class="btn btn-success" type="submit">햄버거 저장</button>
          </div>
          
        </form>
      </div>
        
    </div>
            
    <!-- Burger Edit Script Part -->
    <script type="text/javascript">
    var ingredientList = [];
    var idCount = 0;
    var ingredientCount = 0;
    
    function changeBun(option) {
      $("#bread_down").attr("src", option.value);
      $("#bread_down_name").text(option.text);
    }
    
    function addIngredient(option) {
      if (ingredientCount>=9) {alert("더 이상 재료를 추가할 수 없습니다."); return;}
      
      idCount += 1;
      ingredientCount += 1;
      
      var newIngredient = '<div id="div' +idCount+ '" style="height:30px; width: 318px; margin-top: 5px; z-index:' + idCount + '; position: relative; display: block;">';
      
      newIngredient += '<div style="height:30px; width: 140px; float:left;" align="center">';
      newIngredient += '<img id="img' +idCount+ '" src="' +option.value+ '" style="height: 50px; width: 100px; margin-top:-110px; opacity:0.2;">';
      newIngredient += '</div>';
      
      newIngredient += '<div style="height:30px; width: 120px; float:left; padding-top: 5px;" align="center">';
      newIngredient += '<p>' +option.text+ '</p>';
      newIngredient += '</div>';
      
      newIngredient += '<div style="height:30px; width: 58px; float:left; padding-top:5px;" align="center">';
      newIngredient += '<button name="div' +idCount+ '" style="height:20px; width: 20px; padding:0px;" class="btn btn-danger glyphicon glyphicon-remove" onclick="delRow(this)" type="button"></button>';
      newIngredient += '</div>';
      
      newIngredient += '</div>';
      
      $('#ingredientList').prepend(newIngredient);
      $('#img'+idCount).animate({marginTop: "+=100px", opacity: "1"});
      
      $("#pattySelect option:selected").prop("selected", false);
      $("#vegeSelect option:selected").prop("selected", false);
      $("#etcSelect option:selected").prop("selected", false);
      
      ingredientList += ("div" + idCount);
    }
  
    //행 삭제
    function delRow (button){
      ingredientCount -= 1;
      alert($('#'+button.name));
      $(button).parent().parent().remove();
    }
    
    //버거 모달 로딩
    function loadBurger() {
    }
    
    //버거 모달 초기화
    function initBurgerModal() {
      
      for (var i = 0; i < divIdList.length; i++) {
        $("#"+divIdList[i]).remove();
      }
      
      ingredientList = [];
      divIdList = [];
      ingredientIdCount = 0;
      ingredientCount = 0;
    }
        
    $('#save_burger').submit(function(e) {
      e.preventDefault();
      $('#burgerModal').modal('toggle');
      return false;
    });
    </script>

  </div>
         


</body>
</html>