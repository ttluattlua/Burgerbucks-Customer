package Dto;

import java.io.Serializable;

/*------------------------------------------------------------------------------
 * 음료 테이블을 위한 DTO
 * -----------------------------------------------------------------------------*/
public class Bb_BeverageDto implements Serializable {
	private int seq;			//시퀀스
	private String name;		//음료이름
	private int price;			//음료가격
	private int cal;			//음료 칼로리
	private int del;			//삭제여부 
	
	
////이미지테이블 
	private String what_Image;
	private String image_Src;
	private int image_Seq;
	
	
	public Bb_BeverageDto() {}

	public Bb_BeverageDto(int seq, String name, int price, int cal, int del) {
		super();
		this.seq = seq;
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.del = del;
	}
	
	

	public Bb_BeverageDto(int seq, String name, int price, int cal, int del, String what_Image, String image_Src,
			int image_Seq) {
		super();
		this.seq = seq;
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.del = del;
		this.what_Image = what_Image;
		this.image_Src = image_Src;
		this.image_Seq = image_Seq;
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
		return "Bb_BeverageDto [seq=" + seq + ", name=" + name + ", price=" + price + ", cal=" + cal + ", del=" + del
				+ ", what_Image=" + what_Image + ", image_Src=" + image_Src + ", image_Seq=" + image_Seq + "]";
	}

	
	
}	
