package platform.app.kl.rate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import platform.app.kl.rate.dao.RateDao;
import platform.app.kl.rate.entity.Rate;
import platform.app.kl.rate.service.RateService;




@Service("RateServiceImpl")
@Transactional
public class RateServiceImpl implements RateService {
	@Autowired
	private RateDao rateDao;

	@Override
	public Rate get(String id){
		return rateDao.get(id);
	}

	@Override
	public List<Rate> getList(Map<String, Object> map){
		return rateDao.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return rateDao.getCount(map);
	}

	@Override
	public void save(Rate rate){
		rateDao.save(rate);
	}

	@Override
	public void update(Rate rate){
		rateDao.update(rate);
	}

	@Override
	public void delete(String id){
		rateDao.delete(id);
	}

	@Override
	public void deleteBatch(String[] ids){
		rateDao.deleteBatch(ids);
	}


}
