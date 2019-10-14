package CoinAPI.Main;

import java.text.NumberFormat;
import java.util.Locale;

public class SplitAPI {

	public static String splitNum(Integer num) {

		return NumberFormat.getNumberInstance(Locale.GERMANY).format(num);

	}

}
