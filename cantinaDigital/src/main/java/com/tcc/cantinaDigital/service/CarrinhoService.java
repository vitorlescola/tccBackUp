package com.tcc.cantinaDigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.cantinaDigital.model.Carrinho;
import com.tcc.cantinaDigital.model.Produto;
import com.tcc.cantinaDigital.model.Usuario;
import com.tcc.cantinaDigital.repository.CarrinhoRepository;
import com.tcc.cantinaDigital.repository.UsuarioRepository;

@Service
public class CarrinhoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public void adicionarAoCarrinho(Long usuarioId, Produto produto) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Carrinho carrinho = usuario.getCarrinho();

        if (carrinho == null) {
            carrinho = new Carrinho();
            usuario.setCarrinho(carrinho);
        }

        carrinho.getProdutos().merge(produto, 1, Integer::sum);
        usuarioRepository.save(usuario);
    }
}