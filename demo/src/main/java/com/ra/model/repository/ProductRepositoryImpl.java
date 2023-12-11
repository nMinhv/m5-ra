package com.ra.model.repository;

import com.ra.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("from Product ", Product.class).list();
            transaction.commit();

        } catch (Exception e ){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();

        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        if (product == null) {
            return null;
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }

    @Override
    public Boolean create(Product product) {
        if (product == null) {
            return false;
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Product findById(Integer id) {
        if (id == null || id <= 0) {
            return null; // hoặc ném một ngoại lệ
        }
        try (Session session = sessionFactory.openSession()) {
            Product product = session.get(Product.class, id);
            if (product == null) {
                // Đối tượng không tồn tại
            }
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Product product = findById(id);
                if (product != null) {
                    session.delete(product);
                } else {
                    // Đối tượng không tồn tại
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
