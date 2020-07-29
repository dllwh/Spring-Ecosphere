package org.dllwh.thymeleaf;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 项目启动
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020/7/29 20:55
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication

public class ThymeleafApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThymeleafApplication.class, args);

        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("/Users/dllwh/DevGit/Spring-Ecosphere/dllwh-SpringBoot2/SpringBoot2-chapter20-thymeleaf");
        // 项目名称
        config.setProjectName("ProjectName");
        // 声明该API的版本
        config.setApiVersion("V1.0");
        // 生成API 文档所在目录
        config.setDocsPath("/Users/dllwh/DevGit/Spring-Ecosphere/dllwh-SpringBoot2/SpringBoot2-chapter20-thymeleaf/");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}
