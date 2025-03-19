package com.example.demo.Controller;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Request.LoginDTO;
import com.example.demo.DTO.Response.ResponseUtil;
import com.example.demo.Config.JwtService;
import com.example.demo.Entity.Account;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @PostMapping("register")
    public ResponseEntity<Object>register(@RequestBody Account account) {
        try {
            if(userService.create(account)) {
                ApiResponse apiResponse=ResponseUtil.SuccessNotData("Register Success");
                return ResponseEntity.ok(apiResponse);

            }else{
                ApiResponse apiResponse=ResponseUtil.Error("Register Fail");
                return ResponseEntity.badRequest().body(apiResponse);
            }
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse apiResponse = ResponseUtil.Error("Login Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(role -> role.replace("ROLE_", ""))
                    .collect(Collectors.toList());
            String token = jwtService.generateToken(authentication.getName(), roles);
            ApiResponse apiResponse = ResponseUtil.SuccessData("Login Success", token);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse apiResponse = ResponseUtil.Error("Login Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }

    }


}
