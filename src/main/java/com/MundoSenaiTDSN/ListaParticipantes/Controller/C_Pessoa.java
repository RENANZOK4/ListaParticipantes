package com.MundoSenaiTDSN.ListaParticipantes.Controller;

import com.MundoSenaiTDSN.ListaParticipantes.Model.M_Resposta;
import com.MundoSenaiTDSN.ListaParticipantes.Service.S_Login;
import com.MundoSenaiTDSN.ListaParticipantes.Service.S_Pessoa;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.web.ServerProperties;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class C_Pessoa {

    @GetMapping("/")
    public String landPage(){
        return "Login/login";
    }

    @PostMapping("/")
    public String loginPessoa(@RequestParam("usuario") String usuario,
                              @RequestParam("senha") String senha,
                              HttpSession session){
        session.setAttribute("usuario",S_Login.validalogin(usuario, senha));
        if (session.getAttribute("usuario")!= null);
        return "Home/home";
    }

    @GetMapping("/cadastro")
    public String getCadastro(){
        return "Cad_pessoa/cad_pessoa";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@RequestParam("nome") String nome,
                               @RequestParam("cpf") String cpf,
                               @RequestParam("telefone") String telefone,
                               @RequestParam("email") String email,
                               @RequestParam("senha") String senha,
                               @RequestParam("confSenha") String confSenha, Model model,
                                RedirectAttributes redirectAttributes){
        M_Resposta m_resposta = S_Pessoa.cadastrarPessoa(nome,cpf,email,telefone,senha, confSenha);
        if(m_resposta.getStatus()) {
            redirectAttributes.addFlashAttribute("mensagem",m_resposta.getMensagem());
            return "redirect:/";
        }else{
            redirectAttributes.addFlashAttribute("mensagem",m_resposta.getMensagem());
            redirectAttributes.addFlashAttribute("nome", nome);
            redirectAttributes.addFlashAttribute("cpf", cpf);
            redirectAttributes.addFlashAttribute("telefone", telefone);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("senha", senha);
            return "redirect:/cadastro";
        }
    }

}
