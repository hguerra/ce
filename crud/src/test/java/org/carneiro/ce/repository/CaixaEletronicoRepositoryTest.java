package org.carneiro.ce.repository;

import org.carneiro.ce.Application;
import org.carneiro.ce.model.impl.CaixaEletronico;
import org.carneiro.ce.model.impl.Nota;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author heitor
 * @since 01/02/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class CaixaEletronicoRepositoryTest {
	private CaixaEletronico caixaEletronico;

	@Autowired
	private CaixaEletronicoRepository caixaEletronicoRepository;

	public void setCaixaEletronicoRepository(CaixaEletronicoRepository caixaEletronicoRepository) {
		this.caixaEletronicoRepository = caixaEletronicoRepository;
	}

	@Before
	public void setUp() throws Exception {
		this.caixaEletronico = new CaixaEletronico();
		this.caixaEletronico.setNome("CaixaTest");
		this.caixaEletronico.setSaldo(1000);
		this.caixaEletronico.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));
	}

	@After
	public void tearDown() throws Exception {
		this.caixaEletronico = null;
	}

	@Test
	public void repositoryNotNull() throws Exception {
		Assert.assertNotNull(caixaEletronicoRepository);
	}

	@Test
	public void save() throws Exception {
		this.caixaEletronico = new CaixaEletronico();
		this.caixaEletronico.setNome("CaixaTestSave");
		this.caixaEletronico.setSaldo(1000);
		this.caixaEletronico.setNotas(Arrays.asList(new Nota(10, 1), new Nota(20, 1),
				new Nota(50, 1), new Nota(100, 1)));

		caixaEletronicoRepository.save(this.caixaEletronico);

		Assert.assertNotNull(caixaEletronicoRepository);
		Assert.assertNotNull(caixaEletronico.getId());
		Assert.assertEquals(new Long(2), caixaEletronico.getId());
	}

	@Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
	public void saveError() throws Exception {
		caixaEletronicoRepository.save(this.caixaEletronico);

		Assert.assertNotNull(caixaEletronicoRepository);
		Assert.assertNotNull(caixaEletronico.getId());

		caixaEletronico.setId(null);
		Assert.assertNull(caixaEletronico.getId());

		caixaEletronicoRepository.save(caixaEletronico);
	}

	@Test
	public void findByNome() throws Exception {
		caixaEletronicoRepository.save(this.caixaEletronico);

		CaixaEletronico caixaEletronico = caixaEletronicoRepository.findByNome("CaixaTest");
		Assert.assertNotNull(caixaEletronicoRepository);
		Assert.assertEquals("CaixaTest", caixaEletronico.getNome());
		Assert.assertEquals(new Integer(1000), caixaEletronico.getSaldo());
	}
}