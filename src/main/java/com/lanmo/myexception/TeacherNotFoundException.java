package com.lanmo.myexception;

import com.lanmo.entity.Teacher;

public class TeacherNotFoundException extends RuntimeException{
    private Long teacherId;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherNotFoundException(Long id){
        this.teacherId = id;
    }
}
