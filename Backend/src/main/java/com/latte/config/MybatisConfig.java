package com.latte.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/*
 * Mybatis 설정 관련 클래스
 * */
@Configuration
@MapperScan(basePackages = { "com.latte.dao" })
public class MybatisConfig {

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//mybatis-config.xml setting
		sqlSessionFactoryBean.setConfigLocation(
				(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")));
		//SQL mapper 관련 xml setting
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

}
