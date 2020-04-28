package platform.app.kl.rate.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;



/**
 * 利率表
 * 
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
@Getter
@Setter
@ToString
public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**id**/
    @ExportField(columnName = "id")
    @ImportField
	private String id;
	/**利率**/
    @ExportField(columnName = "利率")
    @ImportField
	private String rate;

}
