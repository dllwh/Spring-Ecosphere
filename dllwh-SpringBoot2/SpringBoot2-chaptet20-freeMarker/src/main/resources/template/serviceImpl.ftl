package ${basePackageServiceImpl};

import java.util.List;

import ${basePackageDao}.${tableNameUpper}Mapper;
import ${basePackageModel}.${tableNameUpper};
import ${basePackageService}.${tableNameUpper}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: ${tableNameUpper}Service接口实现类
 * @创建者: ${author}
 * @创建时间: ${date}
 * @版本: V1.0.1
 * @since: JDK 1.8
 */

@Service
public class ${tableNameUpper}ServiceImpl implements ${tableNameUpper}Service {
	
	@Autowired
	private ${tableNameUpper}Mapper ${tableNameLower}Mapper;
	
	
	public ${tableNameUpper} insert(${tableNameUpper} ${tableNameLower}) throws Exception {
		${tableNameLower}Mapper.insert(${tableNameLower});
		return	${tableNameLower};
	}
	
	public int delete${tableNameUpper}ById(Integer id) throws Exception {
		return ${tableNameLower}Mapper.deleteByPrimaryKey(id);
	}
	
	public int update${tableNameUpper}(${tableNameUpper} ${tableNameLower}) throws Exception {
		return ${tableNameLower}Mapper.updateByPrimaryKey(${tableNameLower});
	}
	
	public ${tableNameUpper} get${tableNameUpper}ById(Integer id) throws Exception {
		return ${tableNameLower}Mapper.selectByPrimaryKey(id);
	}
	
	public List<${tableNameUpper}> get${tableNameUpper}List(${tableNameUpper} ${tableNameLower}) throws Exception {
		return  ${tableNameLower}Mapper.selectAll();
	}
	
	public int count${tableNameUpper}(${tableNameUpper} ${tableNameLower}) throws Exception {
		return 0;
	}
}