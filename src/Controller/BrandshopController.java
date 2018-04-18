package Controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Dao.Dao_Item;
import Dto.Dto_Item;
import Dto.Dto_Item_Detail;

public class BrandshopController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}

	public void dispatch(String url, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(url);
		dispatch.forward(req, resp);
	}

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 인코딩
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		// 커맨드 체크
		String command = req.getParameter("command");
		System.out.println("\n입력받은 command : " + command);

		// 함수 선언
		Dao_Item dao_item = Dao_Item.getInstance();

		// 브랜드샵 - List (상품 리스트)
		if (command.equals("list")) {
			System.out.println("\nBrandshopController - list 실행");
			
			String part = "ALL";
			int minCost = 0;
			int maxCost = 99999999;
			int page = 1;
			
			if (req.getParameter("part") != null) { part = req.getParameter("part"); }
			if (req.getParameter("minCost") != null) { minCost = Integer.parseInt(req.getParameter("minCost")); }
			if (req.getParameter("maxCost") != null) { maxCost = Integer.parseInt(req.getParameter("maxCost")); }
			if (req.getParameter("page") != null) { page = Integer.parseInt(req.getParameter("page")); }
			
			System.out.println("Parameter로 받은 part : " + part );
			System.out.println("Parameter로 받은 minCost : " + minCost );
			System.out.println("Parameter로 받은 maxCost : " + maxCost );
			System.out.println("Parameter로 받은 page : " + page );
			
			List<Dto_Item> itemList =  dao_item.getItemList(part, minCost, maxCost, page);
			
			
			req.setAttribute("itemList", itemList);
			req.setAttribute("part", part);
			
			System.out.println("16_Brandshop_List.jsp로 이동합니다.");
			dispatch("16_Brandshop_List.jsp", req, resp);
		}

		// 브랜드샵 - Detail (상품 상세 정보)
		else if (command.equals("detail")) {
			System.out.println("\nBrandshopController - detail 실행");
			
			// 아이템 처리를 위한 변수
			int seq = Integer.parseInt(req.getParameter("seq"));
			System.out.println("Parameter로 받은 seq : " + seq );
			
			// 아이템 처리
			Dto_Item item = dao_item.getItem(seq);
			
			/*List<Dto_Item_Detail> itemDetailList = new ArrayList();
			
			for (int i = 0; i < item.getDetailList().size(); i++) {
			  
			  Dto_Item_Detail itemDetail = item.getDetailList().get(i);
			  
			  if(i==0) {
			    List<String> sizeList = new ArrayList();
			    sizeList.add(itemDetail.getSize());
			    
			    List<Integer> stockList = new ArrayList();
			    stockList.add(itemDetail.getStock());
			    
			    itemDetailList.add(new Dto_Item_Detail(itemDetail.getColor(), sizeList, stockList));
			  }
			  
			  else {
			    for (int j = 0; j < itemDetailList.size(); j++) {
            for (int k = 0; k < itemDetailList.get(j).getSizeList().size(); k++) {
              
            }
          }
			  }
      }*/
			
			req.setAttribute("item", item);
			
			System.out.println("17_Brandshop_Detail.jsp로 이동합니다.");
			dispatch("17_Brandshop_Detail.jsp", req, resp);
		}

	}
}