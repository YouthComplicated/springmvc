package com.lanmo.service.impl;

import com.lanmo.entity.Teacher;
import com.lanmo.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * spring中使用JmsTemplate  负责JMS连接、会话并代表发送者发送消息
 */
public class AlertServiceImpl implements AlertService {

    private JmsOperations jmsOperations;

    @Autowired
    public AlertServiceImpl(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void sendMessageToTeacher(final Teacher teacher) {
        jmsOperations.send("", new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(javax.jms.Session session) throws JMSException {
                return session.createObjectMessage(teacher);
            }
        });
    }


    public Teacher receiveTeacherAlert(){
        ObjectMessage receiveMessage = (ObjectMessage) jmsOperations.receive();
        try {
            return (Teacher) receiveMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
}
