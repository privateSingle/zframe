package platform.app.kl.repay.controller;

import java.util.Arrays;
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

import platform.app.kl.rate.entity.Rate;
import platform.app.kl.rate.service.RateService;
import platform.app.kl.repay.vo.RepayVo;
import platform.zframe.common.enumresource.StateEnum;
import platform.zframe.common.log.SysLog;
import platform.zframe.common.utils.*;
import platform.zframe.common.support.office.excel.ExcelBoot;
import platform.zframe.common.support.office.excel.entity.ErrorEntity;
import platform.zframe.common.support.office.excel.function.ImportFunction;
import platform.zframe.common.support.office.excel.function.ExportFunction;

import platform.app.kl.repay.entity.Repay;
import platform.app.kl.repay.service.RepayService;
import platform.zframe.common.utils.PageUtils;
import platform.zframe.common.utils.Query;
import platform.zframe.common.utils.R;
import platform.zframe.controller.AbstractController;


/**
 * 还贷助手表
 *
 * @author dalele
 * @email 895016320@qq.com
 * @date 2020-04-27 13:29:30
 */
@Controller
@RequestMapping("app/kl/repay")
public class RepayController extends AbstractController {
	@Autowired
	private RepayService repayService;

	@Autowired
	private RateService rateService;

    /**
     * 跳转到列表页
     */
    @RequestMapping("/list")
    @RequiresPermissions("repay:list")
    public String list() {
        return "app/kl/repay/list";
    }

	/**
	 * 列表数据
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("repay:list")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Repay> repayList = repayService.getList(query);
		List<RepayVo> collect = repayList.stream().filter(repay -> repay.getUserId().equals(getUserId())).map(repay -> {
			RepayVo repayVo = new RepayVo();
			repayVo.setId(repay.getId());
			repayVo.setTotalAmount(repay.getTotalAmount());
			repayVo.setDeadline(repay.getDeadline());
			repayVo.setRefundDate(DateUtils.format(repay.getRefundDate()));
			return repayVo;

		}).collect(Collectors.toList());


		int total = collect.size();

		PageUtils pageUtil = new PageUtils(collect, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("repay:save")
    public String add(){
        return "app/kl/repay/add";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("repay:update")
    public String edit(Model model, @PathVariable("id") String id){
		Repay repay = repayService.get(id);
        model.addAttribute("model",repay);
        return "app/kl/repay/edit";
    }

	/**
	 * 信息json
	 */
    @ResponseBody
    @RequestMapping("/infojson/{id}")
    @RequiresPermissions("repay:info")
    public R info(@PathVariable("id") String id){
        Repay repay = repayService.get(id);
		return R.ok().put("repay", repay);
    }

    /**
     * 信息 view
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("repay:info")
    public String info(Model model,@PathVariable("id") String id){
        Repay repay = repayService.get(id);
		RepayVo repayVo = ConvertUtils.sourceToTarget(repay, RepayVo.class);
		repayVo.setRefundDate(DateUtils.format(repay.getRefundDate()));
        model.addAttribute("model",repayVo);
        return "app/kl/repay/info";
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存还贷助手表")
	@RequestMapping("/save")
	@RequiresPermissions("repay:save")
	public R save(@RequestBody Repay repay){
    	repay.setUserId(getUserId());
		repayService.save(repay);
		return R.ok();
	}

	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改还贷助手表")
	@RequestMapping("/update")
	@RequiresPermissions("repay:update")
	public R update(@RequestBody Repay repay){
		repayService.update(repay);

		return R.ok();
	}

	@ResponseBody
	@RequestMapping("/jisuan/{id}")
	public String jisuan(@PathVariable("id") String id){
		Repay repay = repayService.get(id);
		Rate rate = rateService.get("1");
		String[] strings = NiTui.calculateEqualPrincipalAndInterest(new Double(repay.getTotalAmount()), new Integer(repay.getDeadline()), new Double(rate.getRate()));
		/*List<String> collect = stringList.stream().map(ss -> {
			return ss.split(".")[0];
		}).collect(Collectors.toList());*/

		String reound="每月需要还款"+strings[3]+"元利息"+strings[2]+"元利息,共计"+strings[0]+"元";
		return reound;

	}





	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除还贷助手表")
	@RequestMapping("/delete")
	@RequiresPermissions("repay:delete")
	public R delete(@RequestBody String[] ids){
		repayService.deleteBatch(ids);

		return R.ok();
	}
    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response,@RequestParam Map<String, Object> params){
        ExcelBoot.ExportBuilder(response,"还贷助手表",Repay.class).exportResponse(params, new ExportFunction<Map<String, Object>, Repay>() {
            @Override
            public List<Repay> pageQuery(Map<String, Object> queryQaram, int pageNum, int pageSize) {
                List<Repay> repayList =null;
                if("tmpl".equals(params.get("opr"))){

                }else if("data".equals(params.get("opr"))){
                        Query query = new Query(params);
                        repayList= repayService.getList(query);
                }
                return repayList;
            }
            //每个对象会回调这个方法
            @Override
            public Repay convert(Repay repay) {
                return repay;
            }
        });
    }

    @ResponseBody
    @RequestMapping("/import")
    public R importExcel(@RequestParam("file") MultipartFile file){
        try {
            List<Repay> list = new ArrayList<Repay>();
            List<ErrorEntity> err = new ArrayList<ErrorEntity>();
            ExcelBoot.ImportBuilder(file.getInputStream(), Repay.class).importExcel(new ImportFunction() {
                //每一行回调
                @Override
                public void onProcess(int sheetIndex, int rowIndex, Object entity) {
                    list.add((Repay) entity);
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
//				for(Repay repay:list){
//					repayService.save(repay);
//				}
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }
}
