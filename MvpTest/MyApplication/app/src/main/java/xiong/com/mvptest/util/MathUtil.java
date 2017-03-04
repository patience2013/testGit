package xiong.com.mvptest.util;

import java.text.DecimalFormat;

public class MathUtil {
	public static String getDecimalByDot(double price){
		DecimalFormat df = new DecimalFormat("######0.0");
		Double currentDouble = new Double(price);
		return df.format(currentDouble);
	}
}
