package org.carneiro.ce.service;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.model.impl.Usuario;
import org.carneiro.ce.repository.SaqueRepository;
import org.carneiro.ce.repository.UsuarioRepository;
import org.carneiro.ce.service.exception.ArgumentosObrigatoriosException;
import org.carneiro.ce.service.exception.CadastroExistenteException;
import org.carneiro.ce.service.exception.SaldoInvalidoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author heitor
 * @since 02/02/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class UsuarioServiceTest {
	private Usuario usuario;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SaqueRepository saqueRepository;

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void setSaqueRepository(SaqueRepository saqueRepository) {
		this.saqueRepository = saqueRepository;
	}

	@Before
	public void setUp() throws Exception {
		this.usuarioRepository.deleteAll();
		this.usuario = new Usuario();
		this.usuario.setNome("UsuarioTest");
		this.usuario.setSaldo(1000);
	}

	@After
	public void tearDown() throws Exception {
		this.usuarioService.deletar(this.usuario);
		this.usuario = null;
	}

	@Test
	public void serviceNotNull() throws Exception {
		Assert.assertNotNull(this.usuarioService);
	}

	@Test
	public void cadastrar() throws Exception {
		Usuario cadastrado = this.usuarioService.cadastrar(this.usuario);

		Assert.assertNotNull(cadastrado);
		Assert.assertNotNull(cadastrado.getId());
		Assert.assertEquals("UsuarioTest", cadastrado.getNome());
		Assert.assertEquals(new Integer(1000), cadastrado.getSaldo());
	}

	@Test(expected = SaldoInvalidoException.class)
	public void cadastrarSaldoNegativoErro() throws Exception {
		this.usuario.setSaldo(-10);
		this.usuarioService.cadastrar(this.usuario);
	}

	@Test(expected = ArgumentosObrigatoriosException.class)
	public void cadastrarCampoNullErro() throws Exception {
		this.usuario.setSaldo(null);
		this.usuarioService.cadastrar(this.usuario);
	}

	@Test(expected = CadastroExistenteException.class)
	public void cadastrarUsuarioDuplicadoErro() throws Exception {
		this.usuarioService.cadastrar(this.usuario);

		Usuario duplicado = new Usuario();
		duplicado.setNome(this.usuario.getNome());
		duplicado.setSaldo(this.usuario.getSaldo());

		this.usuarioService.cadastrar(duplicado);
	}

	@Test
	public void buscar() throws Exception {
		this.usuarioService.cadastrar(this.usuario);
		Usuario encontrado = this.usuarioService.buscar(this.usuario.getNome());

		Assert.assertNotNull(encontrado);
		Assert.assertEquals(this.usuario, encontrado);
	}

	@Test
	public void listarUsuarios() throws Exception {
		this.usuarioService.cadastrar(this.usuario);
		Collection<Usuario> usuarios = this.usuarioService.listarUsuarios();

		Assert.assertNotNull(usuarios);
		Assert.assertEquals(1, usuarios.size());
	}

	@Test
	public void listarSaques() throws Exception {
		this.saqueRepository.deleteAll();

		Saque saque = new Saque();
		saque.setUsuario(this.usuario.getNome());
		saque.setCaixaEletronico("CaixaTest");
		saque.setData(LocalDateTime.now());
		saque.setValor(20);
		this.saqueRepository.save(saque);

		Collection<Saque> encontrados = this.usuarioService.listarSaques(this.usuario.getNome());

		Assert.assertNotNull(encontrados);
		Assert.assertEquals(1, encontrados.size());

		saque = encontrados.stream().findFirst().get();
		Assert.assertEquals(this.usuario.getNome(), saque.getUsuario());
		Assert.assertEquals("CaixaTest", saque.getCaixaEletronico());
	}
}