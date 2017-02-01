package org.carneiro.ce.repository;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.impl.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author heitor
 * @since 01/02/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class UsuarioRepositoryTest {
	private Usuario usuario;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Before
	public void setUp() throws Exception {
		this.usuario = new Usuario();
		this.usuario.setNome("UsuarioTest");
		this.usuario.setSaldo(1000);
	}

	@After
	public void tearDown() throws Exception {
		this.usuario = null;
	}

	@Test
	public void repositoryNotNull() throws Exception {
		Assert.assertNotNull(usuarioRepository);
	}

	@Test
	public void findByNome() throws Exception {
		usuarioRepository.save(this.usuario);
		Usuario find = usuarioRepository.findByNome("UsuarioTest");

		Assert.assertNotNull(find);
		Assert.assertEquals("UsuarioTest", find.getNome());
		Assert.assertEquals(new Integer(1000), find.getSaldo());
	}
}