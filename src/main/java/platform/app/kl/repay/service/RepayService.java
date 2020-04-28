package platform.app.kl.repay.service;

import platform.app.kl.repay.entity.Repay;

import java.util.List;
import java.util.Map;

/**
 * 还贷助手表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
public interface RepayService {

	Repay get(String id);

	List<Repay> getList(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	void save(Repay repay);

	void update(Repay repay);

	void delete(String id);

	void deleteBatch(String[] ids);

}
