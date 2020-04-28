
package platform.zframe.controller;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import platform.zframe.common.enumresource.DefaultEnum;
import platform.zframe.common.exception.MyException;
import platform.zframe.common.log.SysLog;
import platform.zframe.common.shiro.ShiroUtils;
import platform.zframe.common.support.office.excel.ExcelBoot;
import platform.zframe.common.support.office.excel.entity.ErrorEntity;
import platform.zframe.common.support.office.excel.function.ExportFunction;
import platform.zframe.common.support.office.excel.function.ImportFunction;
import platform.zframe.common.utils.*;
import platform.zframe.entity.SysUser;
import platform.zframe.service.SysUserRoleService;
import platform.zframe.service.SysUserService;
import platform.zframe.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author zhangyantao
 *
 * @date 2016年10月31日 上午10:40:10
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;




	@RequestMapping("/register")
	public String register(){
		return "zframe/user/add-register";
	}



	/**
	 * 所有用户列表
	 */

	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public String list(){
		return "zframe/user/list";
	}

	/**
	 * 所有用户列表
	 */
	@ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("sys:user:list")
	public R listData(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}

		//查询列表数据
		Query query = new Query(params);
		List<SysUser> userList = sysUserService.queryList(query);
		int total = sysUserService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/add")
	public String add(){
		return "zframe/user/add";
	}
	/**
	 * @author zhangyantao
	 * @Description  跳转到修改页面
	 * @param
	 * @date 2017/6/27 11:17
	 **/
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("sys:user:list")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id){
		SysUser user = sysUserService.queryObject(id);
		model.addAttribute("model",user);
		//获取所属角色
		List<Long> roleIds=sysUserRoleService.queryRoleIdList(user.getUserId());
		//将list转为字符串
		String roleIdList=StringUtils.join(roleIds.toArray(),",");
		model.addAttribute("roleIdList",roleIdList);
		return "zframe/user/edit";
	}
	/**
	 * 获取登录的用户信息
	 */
	@ResponseBody
	@RequestMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}

	/**
	 * 修改登录用户密码
	 */
	@ResponseBody
	@SysLog("修改密码")
	@RequestMapping("/password")
	public R password(String password, String newPassword){


		//sha256加密
		password = new Sha256Hash(password).toHex();
		//sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();

		//更新密码
		int count = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(count == 0){
			return R.error("原密码不正确");
		}

		//退出
		ShiroUtils.logout();

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@ResponseBody
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:list")
	public R info(@PathVariable("userId") Long userId){
		SysUser user = sysUserService.queryObject(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		return R.ok().put("user", user);
	}


	/**
	 * 保存用户
	 */
	@ResponseBody
	@RequestMapping("/registerData")
	public R registerData(@RequestBody UserVo user){
		SysUser sysUser = ConvertUtils.sourceToTarget(user, SysUser.class);
		SysUser existUser = sysUserService.queryByUserName(user.getUsername());
		if(existUser!=null){
			return R.error("用户名已存在!");
		}
		List<Long> roleList=new ArrayList<>();
		roleList.add(6L);
		sysUser.setRoleIdList(roleList);
		sysUser.setPassword(DefaultEnum.PASSWORD.getCode());
		sysUser.setCreateUserId(1L);
		sysUser.setStatus(1);
		sysUserService.save(sysUser);

		return R.ok();
	}






	/**
	 * 保存用户
	 */
	@ResponseBody
	@SysLog("保存用户")
	@RequestMapping("/save")
	public R save(@RequestBody SysUser user){
		verifyForm(user);
		SysUser existUser = sysUserService.queryByUserName(user.getUsername());
		if(existUser!=null){
			return R.error("用户名已存在!");
		}
		user.setPassword(DefaultEnum.PASSWORD.getCode());
		user.setCreateUserId(getUserId());
		sysUserService.save(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@ResponseBody
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:list")
	public R update(@RequestBody SysUser user){
		verifyForm(user);
		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		//todo kj

		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@ResponseBody
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:list")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}

		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		return R.ok();
	}

	/**
	 * 初始化密码
	 */
	@ResponseBody
	@SysLog("初始化密码")
	@RequestMapping("/initPassword")
	@RequiresPermissions("sys:user:list")
	public R initPassword(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能初始化密码");
		}
		sysUserService.initPassword(userIds);

		return R.ok();
	}

	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysUser user){

		if(StringUtils.isEmpty(user.getMobile())){
			throw new MyException("手机号不能为空");
		}

		if(StringUtils.isEmpty(user.getEmail())){
			throw new MyException("邮箱不能为空");
		}
	}

	@RequestMapping("/export")
	public void exportExcel(HttpServletResponse response, @RequestParam Map<String, Object> params) {
		ExcelBoot.ExportBuilder(response, "系统用户", SysUser.class).exportResponse(params, new ExportFunction<Map<String, Object>, SysUser>() {
			@Override
			public List<SysUser> pageQuery(Map<String, Object> queryQaram, int pageNum, int pageSize) {
				List<SysUser> userList = null;
				if ("tmpl".equals(params.get("opr"))) {

				} else if ("data".equals(params.get("opr"))) {
					Query query = new Query(params);
					userList = sysUserService.queryList(query);
				}
				return userList;
			}

			//每个对象会回调这个方法
			@Override
			public SysUser convert(SysUser o) {
				return o;
			}
		});
	}

	@ResponseBody
	@RequestMapping("/import")
	public R importExcel(@RequestParam("file") MultipartFile file) {
		try {
			List<SysUser> list = new ArrayList<SysUser>();
			List<ErrorEntity> err = new ArrayList<ErrorEntity>();
			ExcelBoot.ImportBuilder(file.getInputStream(), SysUser.class).importExcel(new ImportFunction() {
				//每一行回调
				@Override
				public void onProcess(int sheetIndex, int rowIndex, Object entity) {
					list.add((SysUser) entity);
				}

				//发生导入错误回调
				@Override
				public void onError(ErrorEntity errorEntity) {
					err.add(errorEntity);
				}
			});
			if (err.size() > 0) {
				return R.error("发生异常，请核对： " + err.toString());
			} else {
				// service 执行保存， 请自己写数据关联
//				for(SysUser sysUser:list){
//					sysUserService.save(sysUser);
//				}
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(e.getMessage());
		}
	}
}
