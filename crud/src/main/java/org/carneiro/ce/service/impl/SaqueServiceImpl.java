package org.carneiro.ce.service.impl;

import org.carneiro.ce.model.impl.CaixaEletronico;
import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.model.impl.Transacao;
import org.carneiro.ce.model.impl.Usuario;
import org.carneiro.ce.repository.CaixaEletronicoRepository;
import org.carneiro.ce.repository.SaqueRepository;
import org.carneiro.ce.repository.UsuarioRepository;
import org.carneiro.ce.service.SaqueService;
import org.carneiro.ce.service.exception.TransacaoInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author heitor
 * @since 07/02/17
 */
@Service("saqueService")
public class SaqueServiceImpl implements SaqueService{
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CaixaEletronicoRepository caixaEletronicoRepository;

	@Autowired
	private SaqueRepository saqueRepository;

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void setCaixaEletronicoRepository(CaixaEletronicoRepository caixaEletronicoRepository) {
		this.caixaEletronicoRepository = caixaEletronicoRepository;
	}

	public void setSaqueRepository(SaqueRepository saqueRepository) {
		this.saqueRepository = saqueRepository;
	}

	@Override
	public Saque sacar(Transacao transacao) {
		Usuario usuario = this.usuarioRepository.findByNome(transacao.getUsuario());
		CaixaEletronico caixaEletronico = this.caixaEletronicoRepository.findByNome(transacao.getCaixaEletronico());

		if (usuario == null || caixaEletronico == null) {
			throw new TransacaoInvalidaException();
		}

		usuario.setSaldo(transacao.getUsuarioSaldo());
		caixaEletronico.setSaldo(transacao.getCaixaEletronicoSaldo());
		caixaEletronico.setNotas(transacao.getNotas());

		Saque saque = new Saque();
		saque.setData(LocalDateTime.now());
		saque.setUsuario(usuario.getNome());
		saque.setCaixaEletronico(caixaEletronico.getNome());
		saque.setValor(transacao.getValor());

		this.usuarioRepository.save(usuario);
		this.caixaEletronicoRepository.save(caixaEletronico);
		return this.saqueRepository.save(saque);
	}

	@Override
	public Collection<Saque> buscarUsuarioSaques(String usuario) {
		return this.saqueRepository.findByUsuario(usuario);
	}

	@Override
	public Collection<Saque> buscarCaixaEletronicoSaques(String caixaEletronico) {
		return this.saqueRepository.findByCaixaEletronico(caixaEletronico);
	}
}
