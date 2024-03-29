package platform.zframe.dao;
import org.apache.ibatis.annotations.Param;
import platform.zframe.entity.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 * 
 * @author zhangyantao
 * @email zytzhangyantao@163.com
 * @date 2016年12月4日 下午6:46:16
 */
public interface SysConfigDao extends BaseDao<SysConfig> {
	
	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);

    List<SysConfig> findRule(Map<String, Object> params);

	void setRule(SysConfig config);

    List<SysConfig> findByCode(String code);
}
