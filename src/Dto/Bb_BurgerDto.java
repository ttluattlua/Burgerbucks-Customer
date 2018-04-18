package Dto;

import java.io.Serializable;


/*------------------------------------------------------------------------------
 * 버거를 위한  DTO(버거 속 재료들은 모두 Bb_IngredientDto 클래스로 받아줌)
 * -----------------------------------------------------------------------------*/
public class Bb_BurgerDto implements Serializable {
	private int seq;				//시퀀스
	private String name;			//버거이름
	
	
	private Bb_IngredientDto breadCls;				//빵종류
	private Bb_IngredientDto ingredientsCls1;	//재료1
	private Bb_IngredientDto ingredientsCls2;		//재료2
	private Bb_IngredientDto ingredientsCls3;		//재료3
	private Bb_IngredientDto ingredientsCls4;		//재료4
	private Bb_IngredientDto ingredientsCls5;		//재료5
	private Bb_IngredientDto ingredientsCls6;		//재료6
	private Bb_IngredientDto ingredientsCls7;		//재료7
	private Bb_IngredientDto ingredientsCls8;		//재료8
	private Bb_IngredientDto ingredientsCls9;		//재료9
	
	
	private int bread;				//빵종류
	private int ingredients01;	//재료1
	private int ingredients02;		//재료2
	private int ingredients03;		//재료3
	private int ingredients04;		//재료4
	private int ingredients05;		//재료5
	private int ingredients06;		//재료6
	private int ingredients07;		//재료7
	private int ingredients08;		//재료8
	private int ingredients09;		//재료9
	private int creator;			//만든이(고객이만들었을때는 해당사람 seq번호, admin이 만들었을경우 0)
	private int price;				//버거가격
	private int cal;				//칼로리
	private int del;				//삭제여부
	
	//이미지
	////이미지테이블 
	private String creatorID;
	private String what_Image;
	private String image_Src;
	private int image_Seq;
	
	
	
	public Bb_BurgerDto() {}



	public Bb_BurgerDto(int seq, String name, Bb_IngredientDto breadCls, Bb_IngredientDto ingredientsCls1,
			Bb_IngredientDto ingredientsCls2, Bb_IngredientDto ingredientsCls3, Bb_IngredientDto ingredientsCls4,
			Bb_IngredientDto ingredientsCls5, Bb_IngredientDto ingredientsCls6, Bb_IngredientDto ingredientsCls7,
			Bb_IngredientDto ingredientsCls8, Bb_IngredientDto ingredientsCls9, int bread, int ingredients01,
			int ingredients02, int ingredients03, int ingredients04, int ingredients05, int ingredients06,
			int ingredients07, int ingredients08, int ingredients09, int creator, int price, int cal, int del,
			String creatorID, String what_Image, String image_Src, int image_Seq) {
		super();
		this.seq = seq;
		this.name = name;
		this.breadCls = breadCls;
		this.ingredientsCls1 = ingredientsCls1;
		this.ingredientsCls2 = ingredientsCls2;
		this.ingredientsCls3 = ingredientsCls3;
		this.ingredientsCls4 = ingredientsCls4;
		this.ingredientsCls5 = ingredientsCls5;
		this.ingredientsCls6 = ingredientsCls6;
		this.ingredientsCls7 = ingredientsCls7;
		this.ingredientsCls8 = ingredientsCls8;
		this.ingredientsCls9 = ingredientsCls9;
		this.bread = bread;
		this.ingredients01 = ingredients01;
		this.ingredients02 = ingredients02;
		this.ingredients03 = ingredients03;
		this.ingredients04 = ingredients04;
		this.ingredients05 = ingredients05;
		this.ingredients06 = ingredients06;
		this.ingredients07 = ingredients07;
		this.ingredients08 = ingredients08;
		this.ingredients09 = ingredients09;
		this.creator = creator;
		this.price = price;
		this.cal = cal;
		this.del = del;
		this.creatorID = creatorID;
		this.what_Image = what_Image;
		this.image_Src = image_Src;
		this.image_Seq = image_Seq;
	}

	public Bb_BurgerDto(int seq, String name, int ingredients01, int ingredients02, int ingredients03, int ingredients04,
      int ingredients05, int ingredients06, int ingredients07, int ingredients08, int ingredients09, int creator,
      int price, int cal, int del) {
    super();
    this.seq = seq;
    this.name = name;
    this.ingredients01 = ingredients01;
    this.ingredients02 = ingredients02;
    this.ingredients03 = ingredients03;
    this.ingredients04 = ingredients04;
    this.ingredients05 = ingredients05;
    this.ingredients06 = ingredients06;
    this.ingredients07 = ingredients07;
    this.ingredients08 = ingredients08;
    this.ingredients09 = ingredients09;
    this.creator = creator;
    this.price = price;
    this.cal = cal;
    this.del = del;
  }



  public int getSeq() {
		return seq;
	}



	public void setSeq(int seq) {
		this.seq = seq;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Bb_IngredientDto getBreadCls() {
		return breadCls;
	}



	public void setBreadCls(Bb_IngredientDto breadCls) {
		this.breadCls = breadCls;
	}



	public Bb_IngredientDto getIngredientsCls1() {
		return ingredientsCls1;
	}



	public void setIngredientsCls1(Bb_IngredientDto ingredientsCls1) {
		this.ingredientsCls1 = ingredientsCls1;
	}



	public Bb_IngredientDto getIngredientsCls2() {
		return ingredientsCls2;
	}



	public void setIngredientsCls2(Bb_IngredientDto ingredientsCls2) {
		this.ingredientsCls2 = ingredientsCls2;
	}



	public Bb_IngredientDto getIngredientsCls3() {
		return ingredientsCls3;
	}



	public void setIngredientsCls3(Bb_IngredientDto ingredientsCls3) {
		this.ingredientsCls3 = ingredientsCls3;
	}



	public Bb_IngredientDto getIngredientsCls4() {
		return ingredientsCls4;
	}



	public void setIngredientsCls4(Bb_IngredientDto ingredientsCls4) {
		this.ingredientsCls4 = ingredientsCls4;
	}



	public Bb_IngredientDto getIngredientsCls5() {
		return ingredientsCls5;
	}



	public void setIngredientsCls5(Bb_IngredientDto ingredientsCls5) {
		this.ingredientsCls5 = ingredientsCls5;
	}



	public Bb_IngredientDto getIngredientsCls6() {
		return ingredientsCls6;
	}



	public void setIngredientsCls6(Bb_IngredientDto ingredientsCls6) {
		this.ingredientsCls6 = ingredientsCls6;
	}



	public Bb_IngredientDto getIngredientsCls7() {
		return ingredientsCls7;
	}



	public void setIngredientsCls7(Bb_IngredientDto ingredientsCls7) {
		this.ingredientsCls7 = ingredientsCls7;
	}



	public Bb_IngredientDto getIngredientsCls8() {
		return ingredientsCls8;
	}



	public void setIngredientsCls8(Bb_IngredientDto ingredientsCls8) {
		this.ingredientsCls8 = ingredientsCls8;
	}



	public Bb_IngredientDto getIngredientsCls9() {
		return ingredientsCls9;
	}



	public void setIngredientsCls9(Bb_IngredientDto ingredientsCls9) {
		this.ingredientsCls9 = ingredientsCls9;
	}



	public int getBread() {
		return bread;
	}



	public void setBread(int bread) {
		this.bread = bread;
	}



	public int getIngredients01() {
		return ingredients01;
	}



	public void setIngredients01(int ingredients01) {
		this.ingredients01 = ingredients01;
	}



	public int getIngredients02() {
		return ingredients02;
	}



	public void setIngredients02(int ingredients02) {
		this.ingredients02 = ingredients02;
	}



	public int getIngredients03() {
		return ingredients03;
	}



	public void setIngredients03(int ingredients03) {
		this.ingredients03 = ingredients03;
	}



	public int getIngredients04() {
		return ingredients04;
	}



	public void setIngredients04(int ingredients04) {
		this.ingredients04 = ingredients04;
	}



	public int getIngredients05() {
		return ingredients05;
	}



	public void setIngredients05(int ingredients05) {
		this.ingredients05 = ingredients05;
	}



	public int getIngredients06() {
		return ingredients06;
	}



	public void setIngredients06(int ingredients06) {
		this.ingredients06 = ingredients06;
	}



	public int getIngredients07() {
		return ingredients07;
	}



	public void setIngredients07(int ingredients07) {
		this.ingredients07 = ingredients07;
	}



	public int getIngredients08() {
		return ingredients08;
	}



	public void setIngredients08(int ingredients08) {
		this.ingredients08 = ingredients08;
	}



	public int getIngredients09() {
		return ingredients09;
	}



	public void setIngredients09(int ingredients09) {
		this.ingredients09 = ingredients09;
	}



	public int getCreator() {
		return creator;
	}



	public void setCreator(int creator) {
		this.creator = creator;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getCal() {
		return cal;
	}



	public void setCal(int cal) {
		this.cal = cal;
	}



	public int getDel() {
		return del;
	}



	public void setDel(int del) {
		this.del = del;
	}



	public String getCreatorID() {
		return creatorID;
	}



	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}



	public String getWhat_Image() {
		return what_Image;
	}



	public void setWhat_Image(String what_Image) {
		this.what_Image = what_Image;
	}



	public String getImage_Src() {
		return image_Src;
	}



	public void setImage_Src(String image_Src) {
		this.image_Src = image_Src;
	}



	public int getImage_Seq() {
		return image_Seq;
	}



	public void setImage_Seq(int image_Seq) {
		this.image_Seq = image_Seq;
	}



	@Override
	public String toString() {
		return "Bb_BurgerDto [seq=" + seq + ", name=" + name + ", breadCls=" + breadCls + ", ingredientsCls1="
				+ ingredientsCls1 + ", ingredientsCls2=" + ingredientsCls2 + ", ingredientsCls3=" + ingredientsCls3
				+ ", ingredientsCls4=" + ingredientsCls4 + ", ingredientsCls5=" + ingredientsCls5 + ", ingredientsCls6="
				+ ingredientsCls6 + ", ingredientsCls7=" + ingredientsCls7 + ", ingredientsCls8=" + ingredientsCls8
				+ ", ingredientsCls9=" + ingredientsCls9 + ", bread=" + bread + ", ingredients01=" + ingredients01
				+ ", ingredients02=" + ingredients02 + ", ingredients03=" + ingredients03 + ", ingredients04="
				+ ingredients04 + ", ingredients05=" + ingredients05 + ", ingredients06=" + ingredients06
				+ ", ingredients07=" + ingredients07 + ", ingredients08=" + ingredients08 + ", ingredients09="
				+ ingredients09 + ", creator=" + creator + ", price=" + price + ", cal=" + cal + ", del=" + del
				+ ", creatorID=" + creatorID + ", what_Image=" + what_Image + ", image_Src=" + image_Src
				+ ", image_Seq=" + image_Seq + "]";
	}


	
	
}
