package platform.app.kl.expend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import platform.app.kl.expend.dao.ExpendDao;
import platform.app.kl.expend.entity.Expend;
import platform.app.kl.expend.service.ExpendService;




@Service("ExpendServiceImpl")
@Transactional
public class ExpendServiceImpl implements ExpendService {
	@Autowired
	private ExpendDao expendDao;

	@Override
	public Expend get(String id){
		return expendDao.get(id);
	}

	@Override
	public List<Expend> getList(Map<String, Object> map){
		return expendDao.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return expendDao.getCount(map);
	}

	@Override
	public void save(Expend expend){
		expendDao.save(expend);
	}

	@Override
	public void update(Expend expend){
		expendDao.update(expend);
	}

	@Override
	public void delete(String id){
		expendDao.delete(id);
	}

	@Override
	public void deleteBatch(String[] ids){
		expendDao.deleteBatch(ids);
	}


}
