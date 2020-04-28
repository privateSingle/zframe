package platform.app.kl.repay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import platform.app.kl.repay.dao.RepayDao;
import platform.app.kl.repay.entity.Repay;
import platform.app.kl.repay.service.RepayService;




@Service("RepayServiceImpl")
@Transactional
public class RepayServiceImpl implements RepayService {
	@Autowired
	private RepayDao repayDao;

	@Override
	public Repay get(String id){
		return repayDao.get(id);
	}

	@Override
	public List<Repay> getList(Map<String, Object> map){
		return repayDao.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return repayDao.getCount(map);
	}

	@Override
	public void save(Repay repay){
		repayDao.save(repay);
	}

	@Override
	public void update(Repay repay){
		repayDao.update(repay);
	}

	@Override
	public void delete(String id){
		repayDao.delete(id);
	}

	@Override
	public void deleteBatch(String[] ids){
		repayDao.deleteBatch(ids);
	}


}
