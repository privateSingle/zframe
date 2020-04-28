package platform.app.kl.expend.service;

import platform.app.kl.expend.entity.Expend;

import java.util.List;
import java.util.Map;

/**
 * 财产支出表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
public interface ExpendService {

	Expend get(String id);

	List<Expend> getList(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	void save(Expend expend);

	void update(Expend expend);

	void delete(String id);

	void deleteBatch(String[] ids);


}
