package platform.zframe.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author: dalele
 * @date: 2020/4/27 23:59
 * @description:
 */
public class NiTui {
	/**
	 * 计算等额本息还款
	 *
	 * @param principal 贷款总额
	 * @param months    贷款期限
	 * @param rate      贷款利率
	 * @return
	 */
	public static String[] calculateEqualPrincipalAndInterest(double principal, int months, double rate) {
		ArrayList<String> data = new ArrayList<String>();
		double monthRate = rate / (100 * 12);//月利率
		double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);//每月还款金额
		double totalMoney = preLoan * months;//还款总额
		double interest = totalMoney - principal;//还款总利息
		data.add(String.valueOf(new BigDecimal(totalMoney).intValue()));//还款总额
		data.add(String.valueOf(new BigDecimal(principal).intValue()));//贷款总额
		data.add(String.valueOf(new BigDecimal(interest).intValue()));//还款总利息
		data.add(String.valueOf(new BigDecimal(preLoan).intValue()));//每月还款金额
		data.add(String.valueOf(months));//还款期限
		return data.toArray(new String[data.size()]);
	}


}
