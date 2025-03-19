package com.example.demo.Service;
import com.example.demo.Entity.Account;
import com.example.demo.Generic.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, BaseService<Account,Integer> {
}
