package org.carneiro.ce.controller;

import org.carneiro.ce.model.impl.CaixaEletronico;
import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.model.impl.Transacao;
import org.carneiro.ce.service.CaixaEletronicoService;
import org.carneiro.ce.service.SaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author heitor
 * @since 02/02/17
 */
@RestController
@RequestMapping("/caixa")
public class CaixaEletronicoController {
	@Autowired
	private CaixaEletronicoService caixaEletronicoService;

	@Autowired
	private SaqueService saqueService;

	public void setCaixaEletronicoService(CaixaEletronicoService caixaEletronicoService) {
		this.caixaEletronicoService = caixaEletronicoService;
	}

	public void setSaqueService(SaqueService saqueService) {
		this.saqueService = saqueService;
	}

	@RequestMapping(value = {"/", ""}, method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ResponseBody
	public CaixaEletronico cadastrarUsuario(@Valid @RequestBody CaixaEletronico caixa) {
		return this.caixaEletronicoService.cadastrar(caixa);
	}

	@RequestMapping(value = {"/", ""}, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<CaixaEletronico> listarCaixasEletronicos() {
		return this.caixaEletronicoService.listarCaixasEletronicos();
	}

	@RequestMapping(value = {"/{nome}"}, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public CaixaEletronico buscarCaixaEletronico(@PathVariable String nome) {
		return this.caixaEletronicoService.buscar(nome);
	}

	@RequestMapping(value = {"/{nome}/saque"}, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<Saque> listarSaques(@PathVariable String nome) {
		return this.caixaEletronicoService.listarSaques(nome);
	}

	@RequestMapping(value = {"/saque", ""}, method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Saque sacar(@Valid @RequestBody Transacao transacao) {
		return this.saqueService.sacar(transacao);
	}
}
