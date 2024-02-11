package br.com.ultraworks.erp.core.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ultraworks.erp.core.exception.EndPointNotFoundException;

@Controller
public class ErrorController {

    @RequestMapping("/**")
    @ResponseBody
    public String handleNotFound() {
        throw new EndPointNotFoundException();
    }
}
