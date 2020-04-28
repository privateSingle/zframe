package platform.app.kl.expend.vo;

import lombok.Data;
import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;

import java.util.Date;

/**
 * @author: dalele
 * @date: 2020/4/27 14:16
 * @description:
 */
@Data
public class ExpendVo {

	/**id**/
	private String id;
	/**支出名称**/
	private String expendName;
	/**支出金额**/
	private String expendPrice;
	/**支出日期**/
	private String   expendDate;
	/**备注**/
	private String remark;
	/**添加时间**/
	private String   addTime;
}
