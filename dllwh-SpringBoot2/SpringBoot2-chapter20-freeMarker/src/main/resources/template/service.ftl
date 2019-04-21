package ${basePackageService};

import java.util.List;

import ${basePackageModel}.${tableNameUpper};

public interface ${tableNameUpper}Service {

	${tableNameUpper} insert(${tableNameUpper} ${tableNameLower}) throws Exception;
	
	int delete${tableNameUpper}ById(Integer id) throws Exception;
	
	int update${tableNameUpper}(${tableNameUpper} ${tableNameLower}) throws Exception;
	
	${tableNameUpper} get${tableNameUpper}ById(Integer id) throws Exception;
	
	List<${tableNameUpper}> get${tableNameUpper}List(${tableNameUpper} ${tableNameLower}) throws Exception;
	
	int count${tableNameUpper}(${tableNameUpper} ${tableNameLower}) throws Exception;
}