package platform.zframe.common.support.office.excel.factory;


import platform.zframe.common.support.office.excel.annotation.ExportField;
import platform.zframe.common.support.office.excel.annotation.ImportField;
import platform.zframe.common.support.office.excel.entity.ExcelEntity;
import platform.zframe.common.support.office.excel.entity.ExcelPropertyEntity;
import platform.zframe.common.support.office.excel.exception.ExcelBootException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ExcelMappingFactory {

    /**
     * 根据指定Excel实体获取导入Excel文件相关信息
     *
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static ExcelEntity loadImportExcelClass(Class clazz) {
        List<ExcelPropertyEntity> propertyList = new ArrayList<ExcelPropertyEntity>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ImportField importField = field.getAnnotation(ImportField.class);
            if (null != importField) {
                field.setAccessible(true);
                ExcelPropertyEntity excelPropertyEntity = ExcelPropertyEntity.builder()
                        .fieldEntity(field)
                        .required(importField.required())
                        .dateFormat(importField.dateFormat().trim())
                        .regex(importField.regex().trim())
                        .regexMessage(importField.regexMessage().trim())
                        .scale(importField.scale())
                        .roundingMode(importField.roundingMode())
                        .build();
                propertyList.add(excelPropertyEntity);
            }
        }
        if (propertyList.isEmpty()) {
            throw new ExcelBootException("[{}] 类未找到标注@ImportField注解的属性!", clazz.getName());
        }
        ExcelEntity excelMapping = new ExcelEntity();
        excelMapping.setPropertyList(propertyList);
        return excelMapping;

    }

    /**
     * 根据指定Excel实体获取导出Excel文件相关信息
     *
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static ExcelEntity loadExportExcelClass(Class<?> clazz, String fileName) {
        List<ExcelPropertyEntity> propertyList = new ArrayList<ExcelPropertyEntity>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExportField exportField = field.getAnnotation(ExportField.class);
            if (null != exportField) {
                field.setAccessible(true);
                ExcelPropertyEntity excelPropertyEntity = ExcelPropertyEntity.builder()
                        .fieldEntity(field)
                        .columnName(exportField.columnName().trim())
                        .scale(exportField.scale())
                        .roundingMode(exportField.roundingMode())
                        .dateFormat(exportField.dateFormat().trim())
                        .templateCellValue(exportField.defaultCellValue().trim())
                        .build();
                propertyList.add(excelPropertyEntity);
            }
        }
        if (propertyList.isEmpty()) {
            throw new ExcelBootException("[{}]类未找到标注@ExportField注解的属性!", clazz.getName());
        }
        ExcelEntity excelMapping = new ExcelEntity();
        excelMapping.setPropertyList(propertyList);
        excelMapping.setFileName(fileName);
        return excelMapping;
    }


}
