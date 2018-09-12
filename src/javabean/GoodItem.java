package javabean;

public class GoodItem {
	public String name;
	public Integer number=0;//购买件数
	public Double price=0.0;//单价
	public Double total=0.0;//购买此商品的总价

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	
	//计算购买的此商品的总价
	public void setNumber(Integer number) {
		this.number = number;
		total=number*price;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		total=number*price;
	}
	public Double getTotal() {//total只能计算出来		
		return total;
	}	
}
