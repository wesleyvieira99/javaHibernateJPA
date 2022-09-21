package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class,id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p"; //JPQL
		return em.createQuery(jpql).getResultList(); 
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome"; //JPQL
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList(); 
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome"; //JPQL com relacionamento
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList(); 
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome (String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome"; //JPQL
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult(); 
	}
}
