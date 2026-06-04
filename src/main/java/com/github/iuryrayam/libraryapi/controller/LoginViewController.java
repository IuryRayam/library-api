package com.github.iuryrayam.libraryapi.controller;

import com.github.iuryrayam.libraryapi.security.CustomAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Tag(name = "Login")
public class LoginViewController {

    @GetMapping("/login")
    @Operation(summary = "Salvar", description = "Cadastrar novo login")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Login já está cadastrado.")
    })
    public String paginaLogin(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    @Operation(summary = "Obter Login", description = "Retorna os dados do login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login encontrado."),
            @ApiResponse(responseCode = "404", description = "Login não encontrado.")
    })
    public String paginaHome(Authentication authentication){
        if(authentication instanceof CustomAuthentication customAuth){
            System.out.println(customAuth.getUsuario());
        }
        return "Olá " + authentication.getName();
    }

    @GetMapping("/authorized")
    @ResponseBody
    @Operation(summary = "Obter Codigo de Autorização", description = "Retorna o codigo de autorização")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Codigo encontrado."),
            @ApiResponse(responseCode = "404", description = "Codigo não encontrado.")
    })
    public String getAuthorizationCode(@RequestParam("code") String code){
        return "Seu authorization code: " + code;
    }
}
