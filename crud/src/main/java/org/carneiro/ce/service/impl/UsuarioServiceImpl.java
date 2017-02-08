package org.carneiro.ce.service.impl;

import org.carneiro.ce.model.impl.Saque;
import org.carneiro.ce.model.impl.Usuario;
import org.carneiro.ce.repository.UsuarioRepository;
import org.carneiro.ce.service.SaqueService;
import org.carneiro.ce.service.UsuarioService;
import org.carneiro.ce.service.exception.ArgumentosObrigatoriosException;
import org.carneiro.ce.service.exception.CadastroExistenteException;
import org.carneiro.ce.service.exception.SaldoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author heitor
 * @since 28/01/17
 */
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SaqueService saqueService;

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void setSaqueService(SaqueService saqueService) {
		this.saqueService = saqueService;
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {
		if (usuario.getNome() == null || usuario.getSaldo() == null) {
			throw new ArgumentosObrigatoriosException("nome", "saldo");
		}

		if (usuario.getSaldo() < 10) {
			throw new SaldoInvalidoException(usuario.getSaldo());
		}

		Usuario encontrado = this.buscar(usuario.getNome());
		if (encontrado != null) {
			throw new CadastroExistenteException("Usuario");
		}

		return this.usuarioRepository.save(usuario);
	}

	@Override
	public boolean deletar(Usuario usuario) {
		Usuario encontrado = this.buscar(usuario.getNome());
		if (encontrado == null) {
			return false;
		}

		this.usuarioRepository.delete(encontrado);
		return true;
	}

	@Override
	public Usuario buscar(String nome) {
		return this.usuarioRepository.findByNome(nome);
	}

	@Override
	public Collection<Usuario> listarUsuarios() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Collection<Saque> listarSaques(String nome) {
		return this.saqueService.buscarUsuarioSaques(nome);
	}
}
