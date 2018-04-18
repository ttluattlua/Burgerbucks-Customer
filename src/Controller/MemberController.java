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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Dao.Dao_Company;
import Dao.Dao_Request;
import Dao.Dao_Response;
import Dao.Dao_Item;
import Dao.Dao_Member;
import Dao.Dao_Order;
import Dao.Dao_User;
import Dto.Dto_Basket;
import Dto.Dto_Company;
import Dto.Dto_Request;
import Dto.Dto_Item;
import Dto.Dto_Item_Detail;
import Dto.Dto_Member;
import Dto.Dto_Order;

public class MemberController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doProcess(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doProcess(req, resp);
  }

  public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher dispatch = req.getRequestDispatcher(urls);
    dispatch.forward(req, resp);
  }

  public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // 인코딩
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/html; charset=UTF-8");

    // 커맨드 체크
    String command = req.getParameter("command");
    System.out.println("\n입력받은 command : " + command);

    // 함수 선언
    Dao_User dao_user = Dao_User.getInstance();
    Dao_Member dao_member = Dao_Member.getInstance();
    Dao_Item dao_item = Dao_Item.getInstance();
    
    
    // 회원 가입 페이지 이동
    if (command.equals("regist")) {
      System.out.println("\nMemberController - regist 실행");
      

      System.out.println("03_M_Regist.jsp로 이동합니다.");
      dispatch("03_M_Regist.jsp", req, resp);
    }

    
    
    // 회원 가입 완료
    else if (command.equals("regist_end")) {
      System.out.println("\nMemberController - regist_end 실행");

      String id = req.getParameter("id");
      String pw = req.getParameter("pw");
      String name = req.getParameter("name");
      String birth = req.getParameter("birth");
      String phone = req.getParameter("phone");
      String address = req.getParameter("address");
      String email_front = req.getParameter("email_front");
      String email_back = "";
      
      if(email_front.equals("direct")) {
        email_back = req.getParameter("email_direct");
      }
      else {
        email_back = req.getParameter("email_back");
      }
      
      String email = email_front + "@" + email_back;

      System.out.println("Parameter로 받은 id : " + id);
      System.out.println("Parameter로 받은 pw : " + pw);
      System.out.println("Parameter로 받은 name : " + name);
      System.out.println("Parameter로 받은 birth : " + birth);
      System.out.println("Parameter로 받은 phone : " + phone);
      System.out.println("Parameter로 받은 address : " + address);
      System.out.println("Parameter로 받은 email : " + email);
       

      boolean memberAdd = dao_member.addMember(new Dto_Member(0, id, pw, name, birth, phone, address, email, null, false));

      // 메세지 설정 및 페이지 이동
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      
      
      if (memberAdd) {
        System.out.println("회원 가입에 성공했습니다.");
        alerting.println("alert('회원 가입에 성공했습니다.');");
      }
      else {
        System.out.println("회원 가입에 실패했습니다.");
        alerting.println("alert('회원 가입에 실패했습니다.');");
      }
      
      System.out.println("01_Main.jsp로 이동합니다." );
      alerting.println("location.href='01_Main.jsp';"); 
      
      alerting.println("</script>"); 
      alerting.close();
    }

    
    
    // 내 정보 페이지 이동
    else if (command.equals("info")) {
      System.out.println("\nMemberController - info 실행");
      
      String page = req.getParameter("page");
      System.out.println("Parameter로 받은 page : " + page);
      
      System.out.println("06_M_Info.jsp로 이동합니다.");
      dispatch("06_M_Info.jsp", req, resp);
    }

    
    
    // 내 정보 수정 페이지 이동
    else if (command.equals("info_update")) {
      System.out.println("\nMemberController - info_update 실행");
      
      System.out.println("07_M_Info_Update.jsp로 이동합니다.");
      dispatch("07_M_Info_Update.jsp", req, resp);
    }

    
    
    // 내 정보 수정 완료
    else if (command.equals("info_update_end")) {
      System.out.println("\nMemberController - info_update_end 실행");

      String id = req.getParameter("id");
      String pw = req.getParameter("pw");
      String name = req.getParameter("name");
      String birth = req.getParameter("birth");
      String phone = req.getParameter("phone");
      String address = req.getParameter("address");
      String email = req.getParameter("email");

      boolean memberUpdate = dao_member
          .updateMember(new Dto_Member(0, id, pw, name, birth, phone, address, email, null, false));

      // Alert 사용을 위한 변수 선언
      PrintWriter out = resp.getWriter();

      if (memberUpdate) {
        Dto_Member userDto = dao_member.getMember1(id);
        req.getSession().setAttribute("userdto", userDto);

        System.out.println("회원 정보 수정에 성공했습니다.");
        out.println("회원 정보 수정에 성공했습니다.");
        out.close();
      } else {
        System.out.println("회원 정보 수정에 성공했습니다.");
        out.println("회원 정보 수정에 성공했습니다.");
        out.close();
      }

      System.out.println("MemberController?command=info로 이동합니다.");
      dispatch("MemberController?command=info", req, resp);
    }
    
     

    // 장바구니 페이지 이동
    else if (command.equals("cart")) {
      System.out.println("\nMemberController - cart 실행");

      System.out.println("Mypage_M_Cart.jsp로 이동합니다.");

      dispatch("Mypage_M_Cart.jsp", req, resp);
    } 
    
    
    

    // 주문 조회 페이지 이동
    else if (command.equals("order")) {

      System.out.println("\nMemberController - order 실행");

      System.out.println("Mypage_M_Order.jsp로 이동합니다.");
      dispatch("Mypage_M_Order.jsp", req, resp);
    }
    

    // 장바구니 추가하기
    else if (command.equals("addCart")) {
      System.out.println("\nMemberController - addCart 실행");

      Dto_Member member = (Dto_Member)req.getSession().getAttribute("userdto");
      String currentpage = (String)req.getSession().getAttribute("currentpage");
      
      
      
      dispatch(currentpage, req, resp);
    }
    
    
    
    //장바구니 삭제하기
    else if (command.equals("delCart")) {

      System.out.println("\nMemberController - cart 실행");
      Dto_Member member = (Dto_Member) req.getSession().getAttribute("userdto");

      System.out.println("Mypage_M_Cart.jsp로 이동합니다.");

      dispatch("Mypage_M_Cart.jsp", req, resp);
    }
    
    
    
    //장바구니 수정하기
    else if (command.equals("updCart")) {

      System.out.println("\nMemberController - cart 실행");
      Dto_Member member = (Dto_Member) req.getSession().getAttribute("userdto");

      System.out.println("Mypage_M_Cart.jsp로 이동합니다.");

      dispatch("Mypage_M_Cart.jsp", req, resp);
    }

    
    
    // 주문하기
    else if (command.equals("addOrder")) {
      System.out.println("\nMemberController - addOrder 실행");

      Dto_Member member = (Dto_Member) req.getSession().getAttribute("userdto");
      String g[] = req.getParameter("item").split("-");
      Dao_Order.getInstance().addOrder(member.getSeq(), Integer.parseInt(g[0]), Integer.parseInt(g[1]),
          Integer.parseInt(g[2]));

      dispatch("BrandshopController?command=detail&seq=" + g[1], req, resp);
    }
    
  }
}
