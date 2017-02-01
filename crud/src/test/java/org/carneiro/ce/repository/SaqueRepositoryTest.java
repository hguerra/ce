package org.carneiro.ce.repository;

import org.carneiro.ce.Application;
import org.junit.Assert;
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
public class SaqueRepositoryTest {
	@Autowired
	private SaqueRepository saqueRepository;

	public void setSaqueRepository(SaqueRepository saqueRepository) {
		this.saqueRepository = saqueRepository;
	}

	@Test
	public void notNull() throws Exception {
		Assert.assertNotNull(saqueRepository);
	}
}