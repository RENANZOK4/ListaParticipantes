package com.MundoSenaiTDSN.ListaParticipantes.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class C_home {
    @GetMapping("/Home")
    public String getHome(HttpSession session) {
        if (session.getAttribute("usuario") != null)
        {
            return "Home/home";
        }else{
            return "redirect:/";
        }
    }
}

//ARRUMAR O PUBLIC STRING //
