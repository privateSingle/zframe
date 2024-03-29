package platform.zframe.dao;

import org.springframework.stereotype.Repository;
import platform.zframe.entity.SysUserRole;

import java.util.List;

/**
 * 用户与角色对应关系
 * 
 * @author zhangyantao
 * @email zytzhangyantao@163.com
 * @date 2016年9月18日 上午9:34:46
 */
@Repository
public interface SysUserRoleDao extends BaseDao<SysUserRole> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
