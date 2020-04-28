package platform.app.kl.expend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.servlet.http.HttpServletResponse;

import platform.app.kl.expend.vo.ExpendVo;
import platform.zframe.common.enumresource.StateEnum;
import platform.zframe.common.log.SysLog;
import platform.zframe.common.utils.*;
import platform.zframe.common.support.office.excel.ExcelBoot;
import platform.zframe.common.support.office.excel.entity.ErrorEntity;
import platform.zframe.common.support.office.excel.function.ImportFunction;
import platform.zframe.common.support.office.excel.function.ExportFunction;

import platform.app.kl.expend.entity.Expend;
import platform.app.kl.expend.service.ExpendService;
import platform.zframe.common.utils.PageUtils;
import platform.zframe.common.utils.Query;
import platform.zframe.common.utils.R;
import platform.zframe.controller.AbstractController;
import sun.util.resources.ga.LocaleNames_ga;


/**
 * 财产支出表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
@Controller
@RequestMapping("app/kl/expend")
public class ExpendController extends AbstractController {
	@Autowired
	private ExpendService expendService;

    /**
     * 跳转到列表页
     */
    @RequestMapping("/list")
    @RequiresPermissions("expend:list")
    public String list() {
        return "app/kl/expend/list";
    }






	/**
	 * 列表数据
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("expend:list")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Expend> expendList = expendService.getList(query);
		List<ExpendVo> collect = expendList.stream().filter(expend -> expend.getUserId().equals(getUserId())).map(expend -> {
			ExpendVo expendVo = new ExpendVo();
			expendVo.setId(expend.getId());
			expendVo.setExpendName(expend.getExpendName());
			expendVo.setExpendPrice(expend.getExpendPrice());
			expendVo.setExpendDate(DateUtils.format(expend.getExpendDate()));
			expendVo.setRemark(expend.getRemark());
			expendVo.setAddTime(DateUtils.format(expend.getAddTime()));
			return expendVo;

		}).collect(Collectors.toList());


		int total = collect.size();

		PageUtils pageUtil = new PageUtils(collect, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("expend:save")
    public String add(){
        return "app/kl/expend/add";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("expend:update")
    public String edit(Model model, @PathVariable("id") String id){
		Expend expend = expendService.get(id);
		model.addAttribute("model",expend);
        return "app/kl/expend/edit";
    }

	/**
	 * 信息json
	 */
    @ResponseBody
    @RequestMapping("/infojson/{id}")
    @RequiresPermissions("expend:info")
    public R info(@PathVariable("id") String id){
        Expend expend = expendService.get(id);
        return R.ok().put("expend", expend);
    }

    /**
     * 信息 view
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("expend:info")
    public String info(Model model,@PathVariable("id") String id){
        Expend expend = expendService.get(id);
		ExpendVo expendVo = ConvertUtils.sourceToTarget(expend, ExpendVo.class);
		expendVo.setAddTime(DateUtils.format(expend.getAddTime()));
		expendVo.setExpendDate(DateUtils.format(expend.getExpendDate()));
        model.addAttribute("model",expendVo);
        return "app/kl/expend/info";
    }


	@RequestMapping("/userList/{userId}")
    public  String userList(Model model,@PathVariable("userId") String userId){
		model.addAttribute("model",userId);

		return "app/kl/expend/list2";
	}
	@ResponseBody
	@RequestMapping("/userListData")
	public R userListData(@RequestParam Map<String, Object> params,@RequestParam Long userId){
		Query query = new Query(params);

		List<Expend> expendList = expendService.getList(query);
		List<ExpendVo> collect = expendList.stream().filter(expend -> expend.getUserId().equals(userId)).map(expend -> {
			ExpendVo expendVo = new ExpendVo();
			expendVo.setId(expend.getId());
			expendVo.setExpendName(expend.getExpendName());
			expendVo.setExpendPrice(expend.getExpendPrice());
			expendVo.setExpendDate(DateUtils.format(expend.getExpendDate()));
			expendVo.setRemark(expend.getRemark());
			expendVo.setAddTime(DateUtils.format(expend.getAddTime()));
			return expendVo;

		}).collect(Collectors.toList());


		int total = collect.size();

		PageUtils pageUtil = new PageUtils(collect, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}




    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存财产支出表")
	@RequestMapping("/save")
	@RequiresPermissions("expend:save")
	public R save(@RequestBody Expend expend){
    	expend.setUserId(getUserId());
		expendService.save(expend);
		return R.ok();
	}

	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改财产支出表")
	@RequestMapping("/update")
	@RequiresPermissions("expend:update")
	public R update(@RequestBody Expend expend){
		expendService.update(expend);

		return R.ok();
	}


	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除财产支出表")
	@RequestMapping("/delete")
	@RequiresPermissions("expend:delete")
	public R delete(@RequestBody String[] ids){
		expendService.deleteBatch(ids);

		return R.ok();
	}
    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response,@RequestParam Map<String, Object> params){
        ExcelBoot.ExportBuilder(response,"财产支出表",Expend.class).exportResponse(params, new ExportFunction<Map<String, Object>, Expend>() {
            @Override
            public List<Expend> pageQuery(Map<String, Object> queryQaram, int pageNum, int pageSize) {
                List<Expend> expendList =null;
                if("tmpl".equals(params.get("opr"))){

                }else if("data".equals(params.get("opr"))){
                        Query query = new Query(params);
                        expendList= expendService.getList(query);
                }
                return expendList;
            }
            //每个对象会回调这个方法
            @Override
            public Expend convert(Expend expend) {
                return expend;
            }
        });
    }

    @ResponseBody
    @RequestMapping("/import")
    public R importExcel(@RequestParam("file") MultipartFile file){
        try {
            List<Expend> list = new ArrayList<Expend>();
            List<ErrorEntity> err = new ArrayList<ErrorEntity>();
            ExcelBoot.ImportBuilder(file.getInputStream(), Expend.class).importExcel(new ImportFunction() {
                //每一行回调
                @Override
                public void onProcess(int sheetIndex, int rowIndex, Object entity) {
                    list.add((Expend) entity);
                }
                //发生导入错误回调
                @Override
                public void onError(ErrorEntity errorEntity) {
                    err.add(errorEntity);
                }
            });
            if (err.size() > 0) {
                return R.error("发生异常，请核对： "+err.toString());
            } else {
                // service 执行保存， 请自己写数据关联
//				for(Expend expend:list){
//					expendService.save(expend);
//				}
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
