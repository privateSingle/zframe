package platform.app.kl.income.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;



/**
 * 财产收入表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-26 23:46:00
 */
@Getter
@Setter
@ToString
public class Income implements Serializable {
	private static final long serialVersionUID = 1L;

	/**id**/
    @ExportField(columnName = "id")
    @ImportField
	private String id;
	/**收入名称**/
    @ExportField(columnName = "收入名称")
    @ImportField
	private String incomeName;
	/**收入类型**/
    @ExportField(columnName = "收入类型")
    @ImportField
	private String incomeType;
	/**收入金额**/
    @ExportField(columnName = "收入金额")
    @ImportField
	private String incomePrice;
	/**收入日期**/
    @ExportField(columnName = "收入日期")
    @ImportField
	private Date incomeDate;
	/**备注**/
    @ExportField(columnName = "备注")
    @ImportField
	private String remark;
	/**添加时间**/
    @ExportField(columnName = "添加时间")
    @ImportField
	private Date addTime;


    private Long userId;

}
