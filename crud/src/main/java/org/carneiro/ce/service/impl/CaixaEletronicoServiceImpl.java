package org.carneiro.ce.service.impl;

import org.carneiro.ce.model.impl.CaixaEletronico;
import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.repository.CaixaEletronicoRepository;
import org.carneiro.ce.service.CaixaEletronicoService;
import org.carneiro.ce.service.SaqueService;
import org.carneiro.ce.service.exception.ArgumentosObrigatoriosException;
import org.carneiro.ce.service.exception.CadastroExistenteException;
import org.carneiro.ce.service.exception.NotasInvalidasException;
import org.carneiro.ce.service.exception.SaldoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author heitor
 * @since 02/02/17
 */
@Service("caixaEletronicoService")
public class CaixaEletronicoServiceImpl implements CaixaEletronicoService {
	@Autowired
	private CaixaEletronicoRepository caixaEletronicoRepository;

	@Autowired
	private SaqueService saqueService;

	public void setCaixaEletronicoRepository(CaixaEletronicoRepository caixaEletronicoRepository) {
		this.caixaEletronicoRepository = caixaEletronicoRepository;
	}

	public void setSaqueService(SaqueService saqueService) {
		this.saqueService = saqueService;
	}

	@Override
	public CaixaEletronico cadastrar(CaixaEletronico caixaEletronico) {
		if (caixaEletronico.getNome() == null || caixaEletronico.getSaldo() == null || caixaEletronico.getNotas() == null) {
			throw new ArgumentosObrigatoriosException("nome", "saldo", "notas");
		}

		if (caixaEletronico.getSaldo() < 10) {
			throw new SaldoInvalidoException(caixaEletronico.getSaldo());
		}

		Integer saldoNotas = caixaEletronico.getNotas().stream().mapToInt(nota -> nota.getNota() * nota.getQuantidade()).sum();
		if (!saldoNotas.equals(caixaEletronico.getSaldo())) {
			throw new NotasInvalidasException(saldoNotas, caixaEletronico.getSaldo());
		}

		CaixaEletronico encontrado = this.buscar(caixaEletronico.getNome());
		if (encontrado != null) {
			throw new CadastroExistenteException("CaixaEletronico");
		}

		return this.caixaEletronicoRepository.save(caixaEletronico);
	}

	@Override
	public boolean deletar(CaixaEletronico caixaEletronico) {
		CaixaEletronico encontrado = this.buscar(caixaEletronico.getNome());
		if (encontrado == null) {
			return false;
		}

		this.caixaEletronicoRepository.delete(encontrado);
		return true;
	}

	@Override
	public CaixaEletronico buscar(String nome) {
		return this.caixaEletronicoRepository.findByNome(nome);
	}

	@Override
	public Collection<CaixaEletronico> listarCaixasEletronicos() {
		return this.caixaEletronicoRepository.findAll();
	}

	@Override
	public Collection<Saque> listarSaques(String nome) {
		return this.saqueService.buscarCaixaEletronicoSaques(nome);
	}
}
