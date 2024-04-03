package com.cloudengine;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class LeadsConfiguration {


    @Bean
    public Docket createRestfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(appInfo())
                .groupName("leads")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloudengine.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                .title("cloud engine leads")
                .description("cloud engine leads api management")
                .version("1.0.0")
                .build();
    }

    @Value("{spring.datasource.url}")
    private String url;

    @Value("{spring.datasource.username}")
    private String username;

    @Value("{spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setPassword(password);
        mysqlDataSource.setUser(username);
        mysqlDataSource.setURL(url);
        return mysqlDataSource;
    }

    @Value("${mybatis.typeAliasPackage}")
    private String typeAliasPackage;

    @Value("${mybatis.mapperLocation}")
    private String mapperLocation;

    @Value("${mybatis.configLocation}")
    private String configLocation;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        List<Resource> rList = Arrays.asList(new PathMatchingResourcePatternResolver().getResource(mapperLocation));
        sessionFactoryBean.setMapperLocations(rList.toArray(new Resource[rList.size()]));
//        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

        return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
