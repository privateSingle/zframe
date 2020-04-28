package platform.app.kl.income.service;

import platform.app.kl.income.entity.Income;

import java.util.List;
import java.util.Map;

/**
 * 财产收入表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-26 23:46:00
 */
public interface IncomeService {

	Income get(String id);

	List<Income> getList(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	void save(Income income);

	void update(Income income);

	void delete(String id);

	void deleteBatch(String[] ids);

}
