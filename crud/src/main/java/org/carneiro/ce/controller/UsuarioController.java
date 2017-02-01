package org.carneiro.ce.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author heitor
 * @since 28/01/17
 */
@SpringBootApplication
@Controller
public class UsuarioController {
	@RequestMapping("/")
	@ResponseBody
	public String index(){
		return "Hora: " + LocalDateTime.now();
	}
}
