package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DBClose;
import DB.DBConnection;
import Dto.Bb_BurgerDto;

public class Dao_Ingredient {

  private static Dao_Ingredient dao_burger = new Dao_Ingredient();
  public Dao_Ingredient() {  DBConnection.initConnect(); }
  public static Dao_Ingredient getInstance() { return dao_burger;  }

  //햄버거 등록
  public boolean addIngredient(Bb_BurgerDto burger) {
    String sql = " INSERT INTO BB_BURGER " + 
                 " VALUES( BB_BURGER_SEQ.NEXTVAL, '" + 
                 burger.getName() + "', '"+ 
                 burger.getBread() + "', "+
                 burger.getIngredients01() + "', "+
                 burger.getIngredients02() + "', "+
                 burger.getIngredients03() + "', "+
                 burger.getIngredients04() + "', "+
                 burger.getIngredients05() + "', "+
                 burger.getIngredients06() + "', "+
                 burger.getIngredients07() + "', "+
                 burger.getIngredients08() + "', "+
                 burger.getIngredients09() + "', "+
                 burger.getCreator() + "', "+
                 burger.getPrice() + "', "+
                 burger.getCal()+ "', 0, ) ";
    
    System.out.println("addBurger : " + sql);
    
    Connection conn = null;
    PreparedStatement psmt = null;
    
    boolean burgerAdd = false;
    
    try {
      conn = DBConnection.makeConnection();
      psmt = conn.prepareStatement(sql);
      psmt.executeQuery();
      
      burgerAdd = true;
      
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      DBClose.close(psmt, conn, null);
    }  
    
    return burgerAdd;
  }
  
  //주문용 햄버거 출력
  public Map<String, List> getOrderBurgerList(int creatorSeq) {
    
    Map<String, List> orderBurgerMap = new HashMap();
    List<Bb_BurgerDto> defaultBurgerList = new ArrayList<>();
    List<Bb_BurgerDto> userBurgerList = new ArrayList<>();
    
    String sql = " SELECT *" +
                 " FROM BB_BURGER " +
                 " WHERE CREATOR LIKE (0, " + creatorSeq + ") " +
                 " AND DEL = 0 ";
    
    System.out.println("getOrderBurgerList : " + sql);

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    try {
      conn = DBConnection.makeConnection();
      psmt = conn.prepareStatement(sql);
      rs = psmt.executeQuery();

      while (rs.next()) {
        
        int seq = rs.getInt("SEQ");
        String name = rs.getString("NAME");
        int bread = rs.getInt("BREAD");
        int ingredients01 = rs.getInt("INGREDIENT01");
        int ingredients02 = rs.getInt("INGREDIENT02");
        int ingredients03 = rs.getInt("INGREDIENT03");
        int ingredients04 = rs.getInt("INGREDIENT04");
        int ingredients05 = rs.getInt("INGREDIENT05");
        int ingredients06 = rs.getInt("INGREDIENT06");
        int ingredients07 = rs.getInt("INGREDIENT07");
        int ingredients08 = rs.getInt("INGREDIENT08");
        int ingredients09 = rs.getInt("INGREDIENT09");
        int creator = rs.getInt("CREATOR");
        int price = rs.getInt("PRICE");
        int cal = rs.getInt("CAL");
        int del = rs.getInt("DEL");
        
        Bb_BurgerDto burgerTemp = 
            new Bb_BurgerDto(seq, name, 
                ingredients01, ingredients02, ingredients03, 
                ingredients04, ingredients05, ingredients06, 
                ingredients07, ingredients08, ingredients09, 
                creator, price, cal, del);
        
        if(creator==0) { defaultBurgerList.add(burgerTemp); }
        else if (creator==creatorSeq) { userBurgerList.add(burgerTemp); }
          
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return orderBurgerMap;
  }
  

}
