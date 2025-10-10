package io.github.Psyllo.libraryapi.controller;

import io.github.Psyllo.libraryapi.Security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Authenticator;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String paginaHome(Authentication authentication){
        if(authentication instanceof CustomAuthentication customAuthentication){
            System.out.println(customAuthentication.getUsuario());
        }
        return "Eae doidão" + authentication.getName();
    }
}
