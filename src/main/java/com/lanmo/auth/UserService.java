package com.lanmo.auth;

import com.lanmo.dao.impl.HibernateUserRepository;
import com.lanmo.dao.UserDao;
import com.lanmo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义查询用户信息
 */
@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserDao userDao;

//    @Autowired
    private HibernateUserRepository hibernateUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUserName("aa");
        User user1 = userDao.findOne1(1);
        System.out.println(user1.toString());
        User user2 = userDao.findOne2(2);
        System.out.println(user2.toString());
        if(user != null){
            List<GrantedAuthority> auths = new ArrayList<>();
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(user.getUserName(), user.getPassword(),auths);
        }
        return null;
    }

    public void testHibernate(){
//        System.out.println(hibernateUserRepository.save(new User("王五", "f32f23f2f2")));
        System.out.println("User:"+ hibernateUserRepository.findOne1(1));
        System.out.println("count():"+ hibernateUserRepository.count());
        System.out.println("findAll():"+hibernateUserRepository.findAll());
        System.out.println("findByUserName():" + hibernateUserRepository.findUserByUserName("tom"));
    }


}
