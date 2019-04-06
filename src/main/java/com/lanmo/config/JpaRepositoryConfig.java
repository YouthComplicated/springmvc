package com.lanmo.config;

import com.lanmo.dao.JpaRepository.TeacherSpringDataDao;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(
//        entityManagerFactoryRef =  "userEntityManagerFactory",
        basePackageClasses = TeacherSpringDataDao.class)
public class JpaRepositoryConfig {
}
