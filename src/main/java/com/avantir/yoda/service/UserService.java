package com.avantir.yoda.service;

import com.avantir.yoda.model.User;
import com.avantir.yoda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lekanomotayo on 05/03/2018.
 */

@Service("userService")
public class UserService  {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    public void delete(long id) {
        userRepository.delete(id);
    }


    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public User findByUserId(Long userId) {

        try
        {
            return userRepository.findByUserId(userId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
