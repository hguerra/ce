package org.carneiro.ce.controller;

import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.model.impl.Usuario;
import org.carneiro.ce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author heitor
 * @since 28/01/17
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@RequestMapping(value = {"/", ""}, method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Usuario cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		return this.usuarioService.cadastrar(usuario);
	}

	@RequestMapping(value = {"/", ""}, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<Usuario> listarUsuarios() {
		return this.usuarioService.listarUsuarios();
	}

	@RequestMapping(value = {"/{nome}"}, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Usuario buscarUsuario(@PathVariable String nome) {
		return this.usuarioService.buscar(nome);
	}

	@RequestMapping(value = {"/{nome}/saque"}, method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<Saque> listarSaques(@PathVariable String nome) {
		return this.usuarioService.listarSaques(nome);
	}
}
