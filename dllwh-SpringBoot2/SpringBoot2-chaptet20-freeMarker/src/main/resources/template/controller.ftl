package ${basePackageController};

import ${basePackageModel}.${tableNameUpper};
import ${basePackageService}.${tableNameUpper}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import io.swagger.annotations.*;


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
public class ${tableNameUpper}Controller {


	@Autowired
	private ${tableNameUpper}Service ${tableNameUpper}Service;

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
	@ApiOperation(value = "", notes = "")
	public void insert(){
	
	} 	
	
	@DeleteMapping("delete")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	@ApiOperation(value = "", notes = "")
	public void delete(){
	
	} 	
	
	@PutMapping("update")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	@ApiOperation(value = "", notes = "")
	public void update(){
	
	} 	
	
	@RequestMapping("getList")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	@ApiOperation(value = "", notes = "")
	public void getList(){
	
	} 	
}