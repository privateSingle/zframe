package platform.app.kl.rate.service;

import platform.app.kl.rate.entity.Rate;

import java.util.List;
import java.util.Map;

/**
 * 利率表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
public interface RateService {

	Rate get(String id);

	List<Rate> getList(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	void save(Rate rate);

	void update(Rate rate);

	void delete(String id);

	void deleteBatch(String[] ids);

}
