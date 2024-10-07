package br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class indexController {
    
    @GetMapping
    public String hello() {
        return "Estou na raiz da API Pizzaria";

    }




}
