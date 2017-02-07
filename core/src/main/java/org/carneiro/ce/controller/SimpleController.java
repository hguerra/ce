package org.carneiro.ce.controller;

import org.carneiro.ce.model.Nota;
import org.carneiro.ce.model.Saque;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
/**
 * @author heitor
 * @since 02/02/17
 */
@RestController
@RequestMapping("/transacao")
public class SimpleController {

	@RequestMapping(value = {"/", ""}, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Saque listarUsuarios() {
		Saque saque = new Saque();
		saque.setUsuario("UsuarioTest");
		saque.setCaixaEletronico("CaixaTest");
		saque.setValor(200);
		saque.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));

		return saque;
	}
}
