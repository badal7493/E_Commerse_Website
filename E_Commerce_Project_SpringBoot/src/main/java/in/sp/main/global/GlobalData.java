package in.sp.main.global;

import java.util.ArrayList;
import java.util.List;

import in.sp.main.model.Product;

public class GlobalData {
	
	public static List<Product> cart;
	
	static {
		cart=new ArrayList<Product>();
	}

}
