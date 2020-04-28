package platform.app.kl.income.controller;

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

import platform.app.kl.expend.entity.Expend;
import platform.app.kl.expend.vo.ExpendVo;
import platform.app.kl.income.vo.IncomeVo;
import platform.zframe.common.enumresource.StateEnum;
import platform.zframe.common.log.SysLog;
import platform.zframe.common.utils.*;
import platform.zframe.common.support.office.excel.ExcelBoot;
import platform.zframe.common.support.office.excel.entity.ErrorEntity;
import platform.zframe.common.support.office.excel.function.ImportFunction;
import platform.zframe.common.support.office.excel.function.ExportFunction;

import platform.app.kl.income.entity.Income;
import platform.app.kl.income.service.IncomeService;
import platform.zframe.common.utils.PageUtils;
import platform.zframe.common.utils.Query;
import platform.zframe.common.utils.R;
import platform.zframe.controller.AbstractController;


/**
 * 财产收入表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-26 23:46:00
 */
@Controller
@RequestMapping("app/kl/income")
public class IncomeController extends AbstractController {
	@Autowired
	private IncomeService incomeService;

    /**
     * 跳转到列表页
     */
    @RequestMapping("/list")
    @RequiresPermissions("income:list")
    public String list() {
        return "app/kl/income/list";
    }

	/**
	 * 列表数据
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("income:list")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Income> incomeList = incomeService.getList(query);

		List<IncomeVo> collect = incomeList.stream().filter(income -> income.getUserId().equals(getUserId())).map(income -> {
			IncomeVo incomeVo = new IncomeVo();
			incomeVo.setId(income.getId());
			incomeVo.setIncomeName(income.getIncomeName());
			incomeVo.setIncomeType(income.getIncomeType());
			incomeVo.setIncomePrice(income.getIncomePrice());
			incomeVo.setIncomeDate(DateUtils.format(income.getIncomeDate()));
			incomeVo.setRemark(income.getRemark());
			incomeVo.setAddTime(DateUtils.format(income.getAddTime()));
			return incomeVo;

		}).collect(Collectors.toList());


		int total = collect.size();

		PageUtils pageUtil = new PageUtils(collect, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("income:save")
    public String add(){
        return "app/kl/income/add";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("income:update")
    public String edit(Model model, @PathVariable("id") String id){
		Income income = incomeService.get(id);
        model.addAttribute("model",income);
        return "app/kl/income/edit";
    }

	/**
	 * 信息json
	 */
    @ResponseBody
    @RequestMapping("/infojson/{id}")
    @RequiresPermissions("income:info")
    public R info(@PathVariable("id") String id){
        Income income = incomeService.get(id);
        return R.ok().put("income", income);
    }

    /**
     * 信息 view
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("income:info")
    public String info(Model model,@PathVariable("id") String id){
        Income income = incomeService.get(id);
		IncomeVo incomeVo = ConvertUtils.sourceToTarget(income, IncomeVo.class);
		incomeVo.setAddTime(DateUtils.format(income.getAddTime()));
		incomeVo.setIncomeDate(DateUtils.format(income.getIncomeDate()));
		model.addAttribute("model",incomeVo);
        return "app/kl/income/info";
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存财产收入表")
	@RequestMapping("/save")
	@RequiresPermissions("income:save")
	public R save(@RequestBody Income income){
    	income.setUserId(getUserId());
		incomeService.save(income);
		return R.ok();
	}

	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改财产收入表")
	@RequestMapping("/update")
	@RequiresPermissions("income:update")
	public R update(@RequestBody Income income){
		incomeService.update(income);

		return R.ok();
	}


	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除财产收入表")
	@RequestMapping("/delete")
	@RequiresPermissions("income:delete")
	public R delete(@RequestBody String[] ids){
		incomeService.deleteBatch(ids);

		return R.ok();
	}
    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response,@RequestParam Map<String, Object> params){
        ExcelBoot.ExportBuilder(response,"财产收入表",Income.class).exportResponse(params, new ExportFunction<Map<String, Object>, Income>() {
            @Override
            public List<Income> pageQuery(Map<String, Object> queryQaram, int pageNum, int pageSize) {
                List<Income> incomeList =null;
                if("tmpl".equals(params.get("opr"))){

                }else if("data".equals(params.get("opr"))){
                        Query query = new Query(params);
                        incomeList= incomeService.getList(query);
                }
                return incomeList;
            }
            //每个对象会回调这个方法
            @Override
            public Income convert(Income income) {
                return income;
            }
        });
    }




	@RequestMapping("/userList/{userId}")
	public  String userList(Model model,@PathVariable("userId") String userId){
		model.addAttribute("model",userId);

		return "app/kl/income/list2";
	}
	@ResponseBody
	@RequestMapping("/userListData")
	public R userListData(@RequestParam Map<String, Object> params,@RequestParam Long userId){
		//查询列表数据
		Query query = new Query(params);

		List<Income> incomeList = incomeService.getList(query);

		List<IncomeVo> collect = incomeList.stream().filter(income -> income.getUserId().equals(userId)).map(income -> {
			IncomeVo incomeVo = new IncomeVo();
			incomeVo.setId(income.getId());
			incomeVo.setIncomeName(income.getIncomeName());
			incomeVo.setIncomeType(income.getIncomeType());
			incomeVo.setIncomePrice(income.getIncomePrice());
			incomeVo.setIncomeDate(DateUtils.format(income.getIncomeDate()));
			incomeVo.setRemark(income.getRemark());
			incomeVo.setAddTime(DateUtils.format(income.getAddTime()));
			return incomeVo;

		}).collect(Collectors.toList());


		int total = collect.size();

		PageUtils pageUtil = new PageUtils(collect, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

    @ResponseBody
    @RequestMapping("/import")
    public R importExcel(@RequestParam("file") MultipartFile file){
        try {
            List<Income> list = new ArrayList<Income>();
            List<ErrorEntity> err = new ArrayList<ErrorEntity>();
            ExcelBoot.ImportBuilder(file.getInputStream(), Income.class).importExcel(new ImportFunction() {
                //每一行回调
                @Override
                public void onProcess(int sheetIndex, int rowIndex, Object entity) {
                    list.add((Income) entity);
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
//				for(Income income:list){
//					incomeService.save(income);
//				}
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
