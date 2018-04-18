package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
import Dao.Dao_User;
import Dto.Dto_Company;
import Dto.Dto_Request;
import Dto.Dto_Item;
import Dto.Dto_Item_Detail;
import Dto.Dto_Member;
import Util.UtilClass;

public class CompanyController extends HttpServlet {

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
    Dao_Item dao_item = Dao_Item.getInstance();
    Dao_Company dao_company = Dao_Company.getInstance();
    UtilClass utilClass = UtilClass.getInstance();
    
    //업체 회원 가입 페이지로 이동
    if (command.equals("regist")) {
      System.out.println("\nCompanyController - regist 실행");

      System.out.println("04_C_Regist.jsp로 이동합니다.");
      dispatch("04_C_Regist.jsp", req, resp);
    }
    

    //회원 가입 완료
    else if(command.equals("regist_end")) {
      System.out.println("\nCompanyController - regist_end 실행");
      
      //현재 최대 seq 번호구해오기
      int seq = dao_company.getRecentSeq();      
      
      String relativePath = "./Image/Logo/" + (seq+1);
      String absolutePath = "D:/eclipse-workspace/JSP/PuzzleFit_Remaster6/WebContent/Image/Logo/" + (seq+1);
      //String absolutePath = "E:/Puzzle/PuzzleFit_Remaster4/WebContent/Image/Logo/" + (seq+1);
      
      boolean makeFolder = utilClass.makeFolder(absolutePath);
      if(!makeFolder) {
        System.out.println("CoordinateController?command=main로 이동합니다.");
        dispatch("CoordinateController?command=main", req, resp);
        return;
      }
      
      MultipartRequest multiReq = new MultipartRequest(req, absolutePath, 5242880, "utf-8", new DefaultFileRenamePolicy());
      
      String relativeImagePath = null;
      Enumeration files = multiReq.getFileNames();
      
      while (files.hasMoreElements()) {
       String filesName = (String)files.nextElement();
       String fileName = (String)multiReq.getFilesystemName(filesName);
       
       if(fileName==null) {
         relativeImagePath = "./UI/empty_logo.png";
       }     
       else {
         relativeImagePath = relativePath + "/" + fileName;
       }       
       
       System.out.println("Parameter로 받은 File : " + relativeImagePath);
      }
      
      String id = multiReq.getParameter("id");      
      String pw = multiReq.getParameter("pw");
      String name = multiReq.getParameter("name");
      String phone = multiReq.getParameter("phone");
      String address = multiReq.getParameter("address");
      String url = multiReq.getParameter("url");
      String comment = multiReq.getParameter("comment");
      
      String email_front = multiReq.getParameter("email_front");
      String email_back = "";
      
      if(email_front.equals("direct")) {
        email_back = multiReq.getParameter("email_direct");
      }
      else {
        email_back = multiReq.getParameter("email_back");
      }
      
      String email = email_front + "@" + email_back;
      
      System.out.println("Parameter로 받은 id : " + id);
      System.out.println("Parameter로 받은 pw : " + pw);
      System.out.println("Parameter로 받은 name : " + name);
      System.out.println("Parameter로 받은 phone : " + phone);
      System.out.println("Parameter로 받은 address : " + address);
      System.out.println("Parameter로 받은 url : " + url);
      System.out.println("Parameter로 받은 comment : " + comment);
      System.out.println("Parameter로 받은 email : " + email);
      
      boolean companyAdd = dao_company.addCompany(new Dto_Company(id, pw, name, phone, email, address, url, comment, relativeImagePath));
      
      // 메세지 설정 및 페이지 이동
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      
      
      if (companyAdd) {
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
    
    
    //내 정보 페이지로 이동
    else if (command.equals("info")) {
      
      dispatch("Mypage_C_Info.jsp", req, resp);
    }

    
    //내 정보 수정 페이지로 이동
    else if (command.equals("info_update")) {
      
      dispatch("Mypage_C_Info_Update.jsp", req, resp);
    }
    
    
    //내 정보 수정 완료
    else if(command.equals("info_update_end")) {
      
      dispatch("Mypage_C_Info.jsp", req, resp);  
    }

    
    //상품 관리 리스트
    if (command.equals("item_list")) {
      System.out.println("\nCompanyController - item_list 실행");
      
      Dto_Company dto_company = (Dto_Company)req.getSession().getAttribute("userdto");
      System.out.println("Session에서 받은 Company의 Name : " + dto_company.getName() );
            
      String part = "ALL";
      int minCost = 0;
      int maxCost = 99999999;
      
      if (req.getParameter("part") != null) { part = req.getParameter("part"); }
      if (req.getParameter("mincost") != null) { minCost = Integer.parseInt(req.getParameter("mincost")); }
      if (req.getParameter("maxcost") != null) { maxCost = Integer.parseInt(req.getParameter("maxcost")); }
      
      List<Dto_Item> itemList = dao_item.getUserItemList(dto_company.getSeq(), part, minCost, maxCost);
      req.setAttribute("itemList", itemList);
      
      System.out.println("12_C_Item_List.jsp로 이동합니다.");
      dispatch("12_C_Item_List.jsp", req, resp);
    }

    
    // 상품 업로드 페이지
    else if (command.equals("item_add")) {
      System.out.println("\nCompanyController - item_add 실행");

      System.out.println("13_C_Item_Add.jsp로 이동합니다.");
      dispatch("13_C_Item_Add.jsp", req, resp);
    }
    
    
    
    // 상품 업로드 완료
    else if (command.equals("item_add_end")) {
      System.out.println("\nCompanyController - item_add_end 실행");
      
      //현재 최대 seq 번호구해오기
      int seq = dao_item.getRecentSeq();
      System.out.println("Recent Seq : " + seq);
      
      String absolutePath = "D:/eclipse-workspace/JSP/PuzzleFit_Remaster6/WebContent/Image/Item/" + (seq+1);
      //String absolutePath = "E:/Puzzle/PuzzleFit_Remaster4/WebContent/Image/Item/" + (seq+1);
      String relativePath = "./Image/Item/" + (seq+1);
      
      System.out.println("absolutePath : " + absolutePath);
      
      boolean makeFolder = utilClass.makeFolder(absolutePath);
      if(!makeFolder) {
        System.out.println("01_Main.jsp로 이동합니다.");
        dispatch("01_Main.jsp", req, resp);
        return;
      }
      
      MultipartRequest multiReq = new MultipartRequest(req, absolutePath, 5242880, "utf-8", new DefaultFileRenamePolicy());
      
      String relativeImagePath = null;
      Enumeration files = multiReq.getFileNames();
      
      while (files.hasMoreElements()) {
       String filesName = (String)files.nextElement();
       String fileName = (String)multiReq.getFilesystemName(filesName);
       
       if(fileName==null) {
         relativeImagePath = "./UI/empty_item.png";
       }     
       else {
         relativeImagePath = relativePath + "/" + fileName;
       }       
       
       System.out.println("Parameter로 받은 File : " + relativeImagePath);
      }
      
      
      Dto_Company dto_company = (Dto_Company)req.getSession().getAttribute("userdto");
      
      String name = multiReq.getParameter("name");
      String part = multiReq.getParameter("part");
      int price = Integer.parseInt(multiReq.getParameter("price"));
      String comment = multiReq.getParameter("comment");
      int companySeq = Integer.parseInt(multiReq.getParameter("companySeq"));
      
      String color[] = multiReq.getParameterValues("color");
      String size[] = multiReq.getParameterValues("size");
      String stock[] = multiReq.getParameterValues("stock");

      
      System.out.println("Parameter로 받은 name : " + name);
      System.out.println("Parameter로 받은 part : " + part);
      System.out.println("Parameter로 받은 price : " + price);
      System.out.println("Parameter로 받은 comment : " + comment);
      System.out.println("Parameter로 받은 companySeq : " + companySeq);
      
      for (int i = 0; i < stock.length; i++) {
        System.out.println((i+1) + "번째 Item Detail : [Color : " + color[i] + " / Size : " + size[i] + " / Stock : " + stock[i] + "]");
      }
      
      boolean itemAdd = dao_item.addItem(new Dto_Item(name, part, price, comment, relativeImagePath, companySeq));
      
      if (itemAdd) {
        System.out.println("상품 추가에 성공했습니다.");
            
        int itemSeq = dao_item.getRecentSeq(); //가장 최근에 추가된 Item의 seq
        
        for (int i = 0; i < stock.length; i++) {
          dao_item.addItemDetail(new Dto_Item_Detail(color[i], size[i], Integer.parseInt(stock[i]), itemSeq), itemSeq);
        }
      }
      else {
        System.out.println("상품 추가에 실패했습니다.");
      }
      
      PrintWriter alerting = resp.getWriter();
      alerting.println("<script language='javascript'>");
      
      if (!itemAdd) { alerting.println("alert('상품 추가에 실패했습니다.');"); }
      else { alerting.println("alert('상품 추가에 성공했습니다');"); }    
      
      System.out.println("CompanyController?command=item_list로 이동합니다." );
      alerting.println("location.href='CompanyController?command=item_list';"); 
      
      alerting.println("</script>"); 
      alerting.close();    

    }
    
    
    
    // 상품 수정 페이지
    if (command.equals("item_update")) {
      System.out.println("\nCompanyController - item_update 실행");

      System.out.println("Mypage_C_Item_Add.jsp로 이동합니다.");
      dispatch("Mypage_C_Item_Add.jsp", req, resp);
    }
    
    
    
    //상품 삭제
    else if(command.equals("item_del")) {
      System.out.println("\nCompanyController - item_del 실행");
      
      String seq = req.getParameter("seq");
      System.out.println("Parameter로 받은 seq : " + seq );
      
      dao_item.delItem(Integer.parseInt(seq));
      
      dispatch("CompanyController?command=item_list", req, resp);
    }
    
    //주문 조회 페이지 이동
    else if(command.equals("order")) {
      
      System.out.println("\nCompanyController - order 실행" );
      
      System.out.println("Mypage_C_Order.jsp로 이동합니다." );
      dispatch("Mypage_C_Order.jsp", req, resp );
    }
  }
}
