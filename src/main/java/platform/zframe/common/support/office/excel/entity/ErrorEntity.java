package platform.zframe.common.support.office.excel.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author NingWei
 */
@Setter
@Getter
@Builder
@ToString
public class ErrorEntity {
    private Integer sheetIndex;
    private Integer rowIndex;
    private Integer cellIndex;
    private String cellValue;
    private String columnName;
    private String errorMessage;
}
