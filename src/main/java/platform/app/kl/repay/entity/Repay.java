package platform.app.kl.repay.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;



/**
 * 还贷助手表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
@Getter
@Setter
@ToString
public class Repay implements Serializable {
	private static final long serialVersionUID = 1L;

	/**id**/
    @ExportField(columnName = "id")
    @ImportField
	private String id;
	/**欠款总额**/
    @ExportField(columnName = "欠款总额")
    @ImportField
	private String totalAmount;
	/**还款期限**/
    @ExportField(columnName = "还款期限")
    @ImportField
	private String deadline;
	/**还款日期**/
    @ExportField(columnName = "还款日期")
    @ImportField
	private Date refundDate;


    private Long userId;

}
