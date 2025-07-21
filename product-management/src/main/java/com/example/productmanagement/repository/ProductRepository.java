package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepository implements IProductRepository{

    @Override
    public List<Product> findAll() {
        return BaseRepository.entityManager.createQuery("select p from Product p",Product.class).getResultList();
    }

    @Override
    public void save(Product product) {
        BaseRepository.entityManager.getTransaction().begin();
        BaseRepository.entityManager.persist(product);
        BaseRepository.entityManager.getTransaction().commit();
    }

    @Override
    public Product findById(int id) {
        return BaseRepository.entityManager.find(Product.class, id);
    }

    @Override
    public void update(int id, Product product) {
        BaseRepository.entityManager.getTransaction().begin();
        Product existing = findById(id);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setDescription(product.getDescription());
            existing.setManufacturer(product.getManufacturer());
            BaseRepository.entityManager.merge(existing);
        }
        BaseRepository.entityManager.getTransaction().commit();
    }

    @Override
    public void remove(int id) {
        BaseRepository.entityManager.getTransaction().begin();
        Product product = findById(id);
        if (product != null) {
            BaseRepository.entityManager.remove(product);
        }
        BaseRepository.entityManager.getTransaction().commit();
    }

    @Override
    public List<Product> searchByName(String name) {
        return BaseRepository.entityManager.createQuery(
            "SELECT p FROM Product p WHERE lower(p.name) LIKE :name", Product.class)
            .setParameter("name", "%" + name.toLowerCase() + "%")
            .getResultList();
    }
}
