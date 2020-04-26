package platform.zframe.common.xss;

import platform.zframe.common.exception.MyException;
import org.apache.commons.lang.StringUtils;
import platform.zframe.common.utils.Underline2Camel;

/**
 * SQL过滤
 * @author zhangyantao
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str,boolean flag){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str=flag?Underline2Camel.camel2Underline(str):str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new MyException("包含非法字符");
            }
        }

        return str;
    }
}
