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


public class BurgerController extends HttpServlet {

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
    
    /*
    //로그인
    if(command.equals("login")) {
      
      System.out.println("\nUserController - login 실행" );
      
      //로그인을 위한 변수
      String id = req.getParameter("login_id");
      String pw = req.getParameter("login_id");
      
      System.out.println("Parameter로 받은 id : " + id );
      System.out.println("Parameter로 받은 pw : " + pw );
      
      boolean idConfirm = false;
      boolean pwConfirm = false;
      
      String usertype = "Logout";
      Object userdto = null;
      
      System.out.println("ID 존재 여부 체크" );
      idConfirm = dao_user.checkID(id);
      
      if(idConfirm) {        
        System.out.println("Member 로그인 체크" );
        Object[] userLogin  = dao_member.checkPW(id, pw);
        
        if (userLogin[0]==null) {
          pwConfirm = false;
          }
        else { 
          pwConfirm = true;
          usertype = (String)userLogin[0];
          userdto = (Object)userLogin[1];   
          }
        
        if (!pwConfirm) {
          System.out.println("Company 로그인 체크" );
          userLogin = dao_company.checkPW(id, pw);
          
          if (userLogin[0]==null) { 
            pwConfirm = false;
            }
          else { 
            pwConfirm = true;
            usertype = (String)userLogin[0];
            userdto = (Object)userLogin[1];
          }          
        }
      }
      
      req.getSession().setAttribute("usertype", usertype);
      req.getSession().setAttribute("userdto", userdto);
      
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      
      //메세지 설정
      if (!idConfirm) {
        System.out.println("일치하는 아이디가 없습니다.");
        alerting.println("alert('일치하는 아이디가 없습니다.');");
      }
      else {
        if (!pwConfirm) {
          System.out.println("비밀번호가 일치하지 않습니다.");
          alerting.println("alert('비밀번호가 일치하지 않습니다.');");
        }
        else {
          System.out.println("로그인에 성공했습니다.");
          alerting.println("alert('로그인에 성공했습니다.');");
        }    
      }
      
      System.out.println("CoordinateController?command=main로 이동합니다." );
      alerting.println("location.href='CoordinateController?command=main';"); 
      
      alerting.println("</script>"); 
      alerting.close();    
      
    }
    */
    
    //햄버거 저장
    if(command.equals("saveBurger")) {
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
    
    //로그 아웃
    else if(command.equals("logout")) {
      System.out.println("\nUserController - logout 실행" );
      
      System.out.println("Session 삭제" );
      req.getSession().invalidate();
      
      System.out.println("로그 아웃 되셨습니다.");
      req.setAttribute("message", "로그 아웃 되셨습니다.");
      
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      alerting.println("alert('로그 아웃 되셨습니다.');");
      
      System.out.println("CoordinateController?command=main로 이동합니다." );
      alerting.println("location.href='CoordinateController?command=main';"); 
      
      alerting.println("</script>"); 
      alerting.close();
      
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
