package org.carneiro.ce.controller;

import org.carneiro.ce.model.Nota;
import org.carneiro.ce.model.Transacao;
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
	public Transacao listarUsuarios() {
		Transacao transacao = new Transacao();
		transacao.setUsuario("UsuarioTest");
		transacao.setCaixaEletronico("CaixaTest");
		transacao.setValor(200);
		transacao.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));

		return transacao;
	}
}
