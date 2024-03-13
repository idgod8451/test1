package prod;

public class Prod {
	private int pnum;
	private String pname;
	private int price;
	private int pcount;
	private String seller;
	
	public Prod() {}

	public Prod(int pnum, String pname, int price, int pcount, String seller) {
		super();
		this.pnum = pnum;
		this.pname = pname;
		this.price = price;
		this.pcount = pcount;
		this.seller = seller;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPcount() {
		return pcount;
	}

	public void setPcount(int pcount) {
		this.pcount = pcount;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Prod [pnum=" + pnum + ", pname=" + pname + ", price=" + price + ", pcount=" + pcount + ", seller="
				+ seller + "]";
	}

	
	
	
	
	
}
