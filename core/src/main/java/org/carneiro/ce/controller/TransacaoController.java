package org.carneiro.ce.controller;

import org.carneiro.ce.model.Transacao;
import org.carneiro.ce.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
/**
 * @author heitor
 * @since 02/02/17
 */
@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	@Autowired
	private TransacaoService transacaoService;

	public void setTransacaoService(TransacaoService transacaoService) {
		this.transacaoService = transacaoService;
	}

	@RequestMapping(value = {"/", ""}, method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Transacao sacar(@Valid @RequestBody Transacao transacao) {
		return this.transacaoService.sacar(transacao);
	}
}
