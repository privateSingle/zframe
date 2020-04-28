package platform.app.kl.income.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import platform.app.kl.income.dao.IncomeDao;
import platform.app.kl.income.entity.Income;
import platform.app.kl.income.service.IncomeService;




@Service("IncomeServiceImpl")
@Transactional
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	private IncomeDao incomeDao;

	@Override
	public Income get(String id){
		return incomeDao.get(id);
	}

	@Override
	public List<Income> getList(Map<String, Object> map){
		return incomeDao.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return incomeDao.getCount(map);
	}

	@Override
	public void save(Income income){
		incomeDao.save(income);
	}

	@Override
	public void update(Income income){
		incomeDao.update(income);
	}

	@Override
	public void delete(String id){
		incomeDao.delete(id);
	}

	@Override
	public void deleteBatch(String[] ids){
		incomeDao.deleteBatch(ids);
	}


}
