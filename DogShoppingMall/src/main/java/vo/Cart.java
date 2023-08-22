package vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
//DTO : 전달할 값 중 한글이 있다면 바로 "utf-8"로 인코딩해줌
public class Cart {
	private int id;// id 로 해당 개 상품을 찾아 수량증가 또는 감소할 때 사용됨
	


	private String image;//개 상품에 대한 이미지
	
	private String kind;//"푸들" 한글값 그대로 -> 자바스크립트에서 사용
	//★인코딩된 개 상품명 추가 (한글이므로 "utf-8"로 인코딩된 값"%16) -> 링크 get방식으로 넘길 때 사용
	private String encodingKind;
	
	private int price;//개 가격
	
	private int qty;//수량 - 추가

	//기본생성자 public Cart(){}
	


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	//테스트 후 : 한글 값이 깨지면 [방법-2]여기서 utf-8로 인코딩한 값을 리턴 하도록 수정하겠다..... 
	public String getKind() {
		return kind;//[방법-1] 인코딩 안된 한글이 전달 되어 예외발생
		
//		[방법-2]
//		try {
//			kind =  URLEncoder.encode(kind, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		return kind;
	}

	public String getEncodingKind() {
		
		try {
			encodingKind =  URLEncoder.encode(encodingKind, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encodingKind;
	}

	public void setKind(String kind) {
		this.kind = kind;
		this.encodingKind = kind;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", image=" + image + ", kind=" + kind + ", encodingKind=" + encodingKind + ", price="
				+ price + ", qty=" + qty + "]";
	}
}
