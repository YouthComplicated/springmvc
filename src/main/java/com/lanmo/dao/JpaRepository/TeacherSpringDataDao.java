package com.lanmo.dao.JpaRepository;


import com.lanmo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用JpaRepository(二次封装好的api 18个方法(写好的方法))
 */
public interface TeacherSpringDataDao extends JpaRepository<Teacher,Integer> {
    /**
     * 自定义方法，或者建立自己的实现类 名称+impl(原理代码需要研究一下)
     * @return
     */
    @Query("select s from Teacher s ")
    List<Teacher> findAllTeacher();
}
