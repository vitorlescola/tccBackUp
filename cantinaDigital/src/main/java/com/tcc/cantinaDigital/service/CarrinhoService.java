package com.tcc.cantinaDigital.service;

import java.util.List;
import java.util.Map;

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

        if (produto.getEstoque() > 0) {
            carrinho.getProdutos().merge(produto, 1, Integer::sum);
            produto.setEstoque(produto.getEstoque() - 1);
            usuarioRepository.save(usuario);
        } else {
            System.out.println("Produto fora de estoque!");
        }
    }
    
    public void removerProdutoDosCarrinhos(Long produtoId) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            Carrinho carrinho = usuario.getCarrinho();
            if (carrinho != null) {
                carrinho.getProdutos().keySet().removeIf(produto -> produto.getId().equals(produtoId));
                usuarioRepository.save(usuario);
            }
        }
    }
    
    public float calcularTotalCarrinho(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Carrinho carrinho = usuario.getCarrinho();
        
        if (carrinho == null || carrinho.getProdutos().isEmpty()) {
            return 0.0f;
        }

        float total = 0.0f;
        for (Map.Entry<Produto, Integer> entry : carrinho.getProdutos().entrySet()) {
            Produto produto = entry.getKey();
            Integer quantidade = entry.getValue();
            total += produto.getPreço() * quantidade;
        }
        return total;
    }
}