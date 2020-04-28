package platform.app.kl.expend.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;



/**
 * 财产支出表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
@Getter
@Setter
@ToString
public class Expend implements Serializable {
	private static final long serialVersionUID = 1L;

	/**id**/
    @ExportField(columnName = "id")
    @ImportField
	private String id;
	/**支出名称**/
    @ExportField(columnName = "支出名称")
    @ImportField
	private String expendName;
	/**支出金额**/
    @ExportField(columnName = "支出金额")
    @ImportField
	private String expendPrice;
	/**支出日期**/
    @ExportField(columnName = "支出日期")
    @ImportField
	private Date expendDate;
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
