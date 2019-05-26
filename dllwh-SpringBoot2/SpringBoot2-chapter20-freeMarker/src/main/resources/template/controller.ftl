package ${basePackageController};

import java.util.List;

import ${basePackageModel}.${tableNameUpper};
import ${basePackageService}.${tableNameUpper}Service;

import org.dllwh.constant.RestResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: ${tableNameUpper}Controller类
 * @创建者: ${author}
 * @创建时间: ${date}
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@RestController
@RequestMapping("${baseRequestMapping}")
@Api
@Slf4j
public class ${tableNameUpper}Controller {


	@Autowired
	private ${tableNameUpper}Service ${tableNameLower}Service;

	@RequestMapping(value = {"","init","index"})
	@ApiOperation(value = "", notes = "")
	public ModelAndView index(ModelAndView mv){
		mv.setViewName("index");
		return mv;
	}
	
	@PutMapping("insert")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	@ApiOperation(value = "创建", notes = "创建")
	public RestResult insert(${tableNameUpper} ${tableNameLower}){
		try {
			${tableNameUpper} result = ${tableNameLower}Service.insert(${tableNameLower});
			return RestResult.success();
		} catch (Exception e) {
			return RestResult.error();
		}
	} 	
	
	@DeleteMapping("delete")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	@ApiOperation(value = "删除", notes = "删除")
	public RestResult delete(@RequestParam Integer id) throws Exception {
		${tableNameUpper} ${tableNameLower} = ${tableNameLower}Service.get${tableNameUpper}ById(id);
		if (${tableNameLower} == null) {
			return RestResult.error(400,"数据不存在");
		} 
		
		if (${tableNameLower}Service.delete${tableNameUpper}ById(id) >= 0){
			return RestResult.success();
		}
		
		return RestResult.error();
	} 	
	
	@PutMapping("update")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	@ApiOperation(value = "更新", notes = "更新")
	public RestResult update(${tableNameUpper} ${tableNameLower}){
		try {
			${tableNameLower}Service.update${tableNameUpper}(${tableNameLower});
			return RestResult.success();
		} catch (Exception e){
			log.error(null, e);
			return RestResult.error();
		}
	} 	
	
	@RequestMapping("getList")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	@ApiOperation(value = "列表", notes = "列表")
	public List<${tableNameUpper}> getList(${tableNameUpper} ${tableNameLower}){
		try {
			return ${tableNameLower}Service.get${tableNameUpper}List(${tableNameLower});
		} catch (Exception e) {
			log.error(null, e);
			return null;
		}
	} 	
}