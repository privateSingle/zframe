package platform.app.kl.repay.vo;

import lombok.Data;
import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;

import java.util.Date;

/**
 * @author: dalele
 * @date: 2020/4/27 14:40
 * @description:
 */
@Data
public class RepayVo {
	/**id**/
	private String id;
	/**欠款总额**/
	private String totalAmount;
	/**还款期限**/
	private String deadline;
	/**还款日期**/
	private String   refundDate;

}
