package com.example.demo.Service.implement;

import com.example.demo.Entity.Account;
import com.example.demo.Exception.CRUDException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository.findByUsername(username);
        if (account == null) {
            return null;
        }
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole()));


        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                authorities
        );
    }

    @Override
    public boolean create(Account user) {
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<Account> getAll() {
        return List.of();
    }

    @Override
    public Account getById(Integer integer) {
        return null;
    }

    @Override
    public Account update(Integer integer, Account user) {
        return null;
    }
}
