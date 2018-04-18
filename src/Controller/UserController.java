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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Dao.Dao_Company;
import Dao.Dao_Request;
import Dao.Dao_Response;
import Dao.Dao_Item;
import Dao.Dao_Member;
import Dao.Dao_User;
import Dto.Dto_Company;
import Dto.Dto_Request;
import Dto.Dto_Item;
import Dto.Dto_Item_Detail;
import Dto.Dto_Member;
import Util.UtilClass;

public class UserController extends HttpServlet {

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
    Dao_User dao_user = Dao_User.getInstance();
    Dao_Company dao_company = Dao_Company.getInstance();
    Dao_Member dao_member = Dao_Member.getInstance();
    
    
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
    
    
    
    //로그 아웃
    else if(command.equals("logout")) {
      System.out.println("\nUserController - logout 실행" );
      
      System.out.println("Session 삭제" );
      req.getSession().invalidate();
      
      /*System.out.println("로그 아웃 되셨습니다.");
      req.setAttribute("message", "로그 아웃 되셨습니다.");*/
      
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      alerting.println("alert('로그 아웃 되셨습니다.');");
      
      System.out.println("CoordinateController?command=main로 이동합니다." );
      alerting.println("location.href='CoordinateController?command=main';"); 
      
      alerting.println("</script>"); 
      alerting.close();
      
      //System.out.println("01_Main.jsp로 이동합니다." );
      //dispatch("01_Main.jsp", req, resp);  
    }
    
    
    
    //회원가입 페이지 이동
    else if(command.equals("regist")) {
      System.out.println("\nUserController - regist 실행" );
      
      System.out.println("02_Regist.jsp로 이동합니다." );
      dispatch("02_Regist.jsp", req, resp);  
    }
    
    
    //아이디 & 패스워드 찾기 페이지 이동
    else if(command.equals("findID")) {
      
      System.out.println("\nUserController - findID 실행" );
      
      dispatch("05_FindID.jsp", req, resp);
    }  
    
    //아이디 찾기 완료
    else if(command.equals("findID_end")) {
      
      System.out.println("\nUserController - findID_end 실행" );
      
      String name = req.getParameter("name");
      String email = req.getParameter("email");
      
      System.out.println("Parameter로 받은 name : " + name );
      System.out.println("Parameter로 받은 email : " + email );
      
      String userID = dao_member.findID(name, email);
      
      if(userID==null) {
        userID = dao_company.findID(name, email);
      }
      
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      
      //메세지 설정
      if (userID==null) {
        System.out.println("일치하는 아이디가 없습니다.");
        alerting.println("alert('일치하는 아이디가 없습니다.');");
      }
      else {
        System.out.println("해당 정보와 일치하는 아이디는 " + userID + " 입니다.");
        alerting.println("alert('해당 정보와 일치하는 아이디는 " + userID + " 입니다.');");
      }
      
      System.out.println("UserController?command=findID로 이동합니다." );
      alerting.println("location.href='UserController?command=findID';"); 
      
      alerting.println("</script>"); 
      alerting.close();
    }
    
    
    //비밀번호 찾기    
    if (command.equals("findPW_end")) {
      System.out.println("\nUserController - findPW_end 실행" );
      
      String id = req.getParameter("id");
      String name = req.getParameter("name");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");
      
      System.out.println("Parameter로 받은 id : " + id );
      System.out.println("Parameter로 받은 name : " + name );
      System.out.println("Parameter로 받은 email : " + email );
      System.out.println("Parameter로 받은 phone : " + phone );

      String userPW = dao_member.findPW(id, name, email, phone);
      
      if(userPW==null) {
        userPW = dao_company.findPW(id, name, email, phone);
      }
      
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      
      //메세지 설정
      if (userPW==null) {
        System.out.println("일치하는 비밀번호가 없습니다.");
        alerting.println("alert('일치하는 비밀번호가 없습니다.');");
      }
      else {
        System.out.println(id + "님의 비밀번호는 " + userPW + " 입니다.");
        alerting.println("alert('" + id + "님의 비밀번호는 " + userPW + " 입니다.');");
      }
      
      System.out.println("UserController?command=findID로 이동합니다." );
      alerting.println("location.href='UserController?command=findID';"); 
      
      alerting.println("</script>"); 
      alerting.close();
    }
    
    
    //AJAX 중복 확인
    else if(command.equals("ajax_checkID")) {
      System.out.println("\nUserController - ajax_checkID 실행" );
      
      String id = req.getParameter("id");
      System.out.println("Parameter로 받은 id : " + id );
      
      boolean check = dao_user.checkID(id);
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
