package org.dllwh.druiddatasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: 数据源测试，测试 spring.datasource.xx 的 druid 属性配置是否正常，数据库是否能连接上等等
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2023-02-28 00:48
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTest {
    /**
     * Spring Boot 默认已经配置好了数据源，可以直接 DI 注入然后使用即可
     */
    @Resource
    private DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());

        if (dataSource instanceof DruidDataSource) {
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
            System.out.println("version=" + druidDataSource.getVersion());
            System.out.println("name=" + druidDataSource.getName());
        }
        connection.close();
    }
}
