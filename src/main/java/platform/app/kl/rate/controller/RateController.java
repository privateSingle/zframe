package platform.app.kl.rate.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.servlet.http.HttpServletResponse;

import platform.zframe.common.enumresource.StateEnum;
import platform.zframe.common.log.SysLog;
import platform.zframe.common.utils.R;
import platform.zframe.common.utils.Query;
import platform.zframe.common.utils.PageUtils;
import platform.zframe.common.support.office.excel.ExcelBoot;
import platform.zframe.common.support.office.excel.entity.ErrorEntity;
import platform.zframe.common.support.office.excel.function.ImportFunction;
import platform.zframe.common.support.office.excel.function.ExportFunction;

import platform.app.kl.rate.entity.Rate;
import platform.app.kl.rate.service.RateService;
import platform.zframe.common.utils.PageUtils;
import platform.zframe.common.utils.Query;
import platform.zframe.common.utils.R;


/**
 * 利率表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
@Controller
@RequestMapping("app/kl/rate")
public class RateController {
	@Autowired
	private RateService rateService;

    /**
     * 跳转到列表页
     */
    @RequestMapping("/list")
    @RequiresPermissions("rate:list")
    public String list() {
        return "app/kl/rate/list";
    }

	/**
	 * 列表数据
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("rate:list")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Rate> rateList = rateService.getList(query);
		int total = rateService.getCount(query);

		PageUtils pageUtil = new PageUtils(rateList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("rate:save")
    public String add(){
        return "app/kl/rate/add";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("rate:update")
    public String edit(Model model, @PathVariable("id") String id){
		Rate rate = rateService.get(id);
        model.addAttribute("model",rate);
        return "app/kl/rate/edit";
    }

	/**
	 * 信息json
	 */
    @ResponseBody
    @RequestMapping("/infojson/{id}")
    @RequiresPermissions("rate:info")
    public R info(@PathVariable("id") String id){
        Rate rate = rateService.get(id);
        return R.ok().put("rate", rate);
    }

    /**
     * 信息 view
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("rate:info")
    public String info(Model model,@PathVariable("id") String id){
        Rate rate = rateService.get(id);
        model.addAttribute("model",rate);
        return "app/kl/rate/info";
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存利率表")
	@RequestMapping("/save")
	@RequiresPermissions("rate:save")
	public R save(@RequestBody Rate rate){
		rateService.save(rate);
		return R.ok();
	}

	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改利率表")
	@RequestMapping("/update")
	@RequiresPermissions("rate:update")
	public R update(@RequestBody Rate rate){
		rateService.update(rate);

		return R.ok();
	}


	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除利率表")
	@RequestMapping("/delete")
	@RequiresPermissions("rate:delete")
	public R delete(@RequestBody String[] ids){
		rateService.deleteBatch(ids);

		return R.ok();
	}
    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response,@RequestParam Map<String, Object> params){
        ExcelBoot.ExportBuilder(response,"利率表",Rate.class).exportResponse(params, new ExportFunction<Map<String, Object>, Rate>() {
            @Override
            public List<Rate> pageQuery(Map<String, Object> queryQaram, int pageNum, int pageSize) {
                List<Rate> rateList =null;
                if("tmpl".equals(params.get("opr"))){

                }else if("data".equals(params.get("opr"))){
                        Query query = new Query(params);
                        rateList= rateService.getList(query);
                }
                return rateList;
            }
            //每个对象会回调这个方法
            @Override
            public Rate convert(Rate rate) {
                return rate;
            }
        });
    }

    @ResponseBody
    @RequestMapping("/import")
    public R importExcel(@RequestParam("file") MultipartFile file){
        try {
            List<Rate> list = new ArrayList<Rate>();
            List<ErrorEntity> err = new ArrayList<ErrorEntity>();
            ExcelBoot.ImportBuilder(file.getInputStream(), Rate.class).importExcel(new ImportFunction() {
                //每一行回调
                @Override
                public void onProcess(int sheetIndex, int rowIndex, Object entity) {
                    list.add((Rate) entity);
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
//				for(Rate rate:list){
//					rateService.save(rate);
//				}
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
