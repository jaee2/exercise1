package javabean;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	public Double totalMoney;//购物车中所有商品总价	
	//数组元素表示某商品的购买情况
	public List<GoodItem> goods=new ArrayList<GoodItem>();

	public Double getTotalMoney() {
		totalMoney=0.0;
		for(int i=0;i<goods.size();i++){
			totalMoney+=goods.get(i).getTotal();
		}
		return totalMoney;
	}

	public List<GoodItem> getGoods() {
		return goods;
	}
	public void setGoods(List<GoodItem> goods) {
		this.goods = goods;
	}	
	
}
