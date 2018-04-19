package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import Dao.Dao_Burger;
import Dto.Bb_BurgerDto;


public class IngredientController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doProcess(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doProcess(req, resp);
  }
  
  public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
    RequestDispatcher dispatch = req.getRequestDispatcher(urls);
    dispatch.forward(req, resp);
  }
  
  
  public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
    
    //인코딩
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/html; charset=UTF-8");
    
    //커맨드 체크
    String command = req.getParameter("command");
    System.out.println("\n입력받은 command : " + command);
    
    //함수 선언
    Dao_Burger dao_burger = Dao_Burger.getInstance();
    
    
    
    //재료 저장
    if(command.equals("addIngredient")) {
      System.out.println("\nBurgerController - saveBurger 실행" );
      
      //로그인을 위한 변수
      Bb_BurgerDto burger = (Bb_BurgerDto)req.getAttribute("burger");
      boolean burgerAdd = dao_burger.addBurger(burger);
      
      PrintWriter out = resp.getWriter();
      out.println("<script language='javascript'>");
      
      if (burgerAdd) { out.println("alert('햄버거가 저장되었습니다.');"); }
      else { out.println("alert('햄버거 저장에 실패했습니다.');"); }
      
      out.println("</script>"); 
      
    }
    
    //AJAX 중복 확인
    else if(command.equals("ajax_checkID")) {
      System.out.println("\nUserController - ajax_checkID 실행" );
      
      String id = req.getParameter("id");
      System.out.println("Parameter로 받은 id : " + id );
      
      boolean check = false;
      PrintWriter out = resp.getWriter();
      
      String IDCheck = "no";
      
      if(!check) {
        IDCheck = "yes";
        System.out.println("사용 가능한 ID입니다.");
      }
      else {
        IDCheck = "no";
        System.out.println("현재 사용중인 ID입니다.");
      }
      
      System.out.println("Parameter에 첨부하는 checkID값 : " + IDCheck );
      out.print(IDCheck);
    }
      
  }

}
