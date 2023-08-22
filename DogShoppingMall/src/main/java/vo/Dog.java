package vo;

public class Dog {
	
	private int id ;
	private String kind;
	private String country;
	private int price;
	private int height;
	private int weight;
	private String content;
	private String image;
	private int readcount;
	
	
	public Dog() {}
	
	public Dog(int id, String kind, String country, int price, int height, int weight, String content, String image,
			int readcount) {
		super();
		this.id = id;
		this.kind = kind;
		this.country = country;
		this.price = price;
		this.height = height;
		this.weight = weight;
		this.content = content;
		this.image = image;
		this.readcount = readcount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", kind=" + kind + ", country=" + country + ", price=" + price + ", height=" + height
				+ ", weight=" + weight + ", content=" + content + ", image=" + image + ", readcount=" + readcount + "]";
	}
	
	
}
