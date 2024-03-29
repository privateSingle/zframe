package platform.zframe.service;

import platform.zframe.entity.SysOss;

import java.util.List;
import java.util.Map;

/**
 * oss配置
 * 
 * @author zhangyantao
 * @email zytzhangyantao@163.com
 * @date 2017-12-13 10:07:04
 */
public interface SysOssService {
	
	SysOss get(String bucket);
	
	List<SysOss> getList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(SysOss sysOss);
	
	void update(SysOss sysOss);
	
	void delete(String bucket);
	
	void deleteBatch(String[] buckets);

    void updateState(String[] ids, String stateValue);
}
