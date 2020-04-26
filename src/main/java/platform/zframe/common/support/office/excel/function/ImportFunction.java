package platform.zframe.common.support.office.excel.function;

import platform.zframe.common.support.office.excel.entity.ErrorEntity;
public interface ImportFunction<T> {

    /**
     * 导入常规校验通过后执行的操作
     *
     * @param sheetIndex
     * @param rowIndex
     * @param entity
     */
    void onProcess(int sheetIndex, int rowIndex, T entity);

    /**
     * 导入常规校验失败后执行的操作
     *
     * @param errorEntity
     */
    void onError(ErrorEntity errorEntity);
}