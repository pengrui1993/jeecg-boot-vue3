package org.jeecg.modules.demo.biz_demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.biz_demo.entity.BizDemo;
import org.jeecg.modules.demo.biz_demo.service.IBizDemoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 测试建表
 * @Author: jeecg-boot
 * @Date:   2025-01-18
 * @Version: V1.0
 */
@Api(tags="测试建表")
@RestController
@RequestMapping("/biz_demo/bizDemo")
@Slf4j
public class BizDemoController extends JeecgController<BizDemo, IBizDemoService> {
	@Autowired
	private IBizDemoService bizDemoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bizDemo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "测试建表-分页列表查询")
	@ApiOperation(value="测试建表-分页列表查询", notes="测试建表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BizDemo>> queryPageList(BizDemo bizDemo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<BizDemo> queryWrapper = QueryGenerator.initQueryWrapper(bizDemo, req.getParameterMap());
		Page<BizDemo> page = new Page<BizDemo>(pageNo, pageSize);
		IPage<BizDemo> pageList = bizDemoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bizDemo
	 * @return
	 */
	@AutoLog(value = "测试建表-添加")
	@ApiOperation(value="测试建表-添加", notes="测试建表-添加")
	@RequiresPermissions("biz_demo:biz_demo:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BizDemo bizDemo) {
		bizDemoService.save(bizDemo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bizDemo
	 * @return
	 */
	@AutoLog(value = "测试建表-编辑")
	@ApiOperation(value="测试建表-编辑", notes="测试建表-编辑")
	@RequiresPermissions("biz_demo:biz_demo:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BizDemo bizDemo) {
		bizDemoService.updateById(bizDemo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试建表-通过id删除")
	@ApiOperation(value="测试建表-通过id删除", notes="测试建表-通过id删除")
	@RequiresPermissions("biz_demo:biz_demo:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bizDemoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试建表-批量删除")
	@ApiOperation(value="测试建表-批量删除", notes="测试建表-批量删除")
	@RequiresPermissions("biz_demo:biz_demo:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bizDemoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "测试建表-通过id查询")
	@ApiOperation(value="测试建表-通过id查询", notes="测试建表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BizDemo> queryById(@RequestParam(name="id",required=true) String id) {
		BizDemo bizDemo = bizDemoService.getById(id);
		if(bizDemo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bizDemo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bizDemo
    */
    @RequiresPermissions("biz_demo:biz_demo:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BizDemo bizDemo) {
        return super.exportXls(request, bizDemo, BizDemo.class, "测试建表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("biz_demo:biz_demo:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BizDemo.class);
    }

}
