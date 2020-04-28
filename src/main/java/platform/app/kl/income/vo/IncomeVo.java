package platform.app.kl.income.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: dalele
 * @date: 2020/4/27 00:10
 * @description:
 */
@Data
public class IncomeVo  implements Serializable {


	private static final long serialVersionUID = 2827704287951678256L;
	/**id**/
	private String id;
	/**收入名称**/
	private String incomeName;
	/**收入类型**/

	private String incomeType;
	/**收入金额**/

	private String incomePrice;
	/**收入日期**/

	private String   incomeDate;
	/**备注**/

	private String remark;
	/**添加时间**/

	private String   addTime;

}
