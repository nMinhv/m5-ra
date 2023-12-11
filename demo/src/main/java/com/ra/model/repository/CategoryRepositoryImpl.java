package com.ra.model.repository;
import com.ra.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            list = session.createQuery("from Category", Category.class).list();
        } catch ( Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return  list;
    }

    @Override
    public Category saveOrUpdate(Category category) {
        if (category == null) {
            return null;
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return category;
    }

    @Override
    public Boolean create(Category category) {
        if (category == null) {
            return false; // hoặc ném một ngoại lệ
        }

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(category);
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
    public Category findById(Integer id) {
        if (id == null || id <= 0) {
            return null; // hoặc ném một ngoại lệ
        }

        try (Session session = sessionFactory.openSession()) {
            Category category = session.get(Category.class, id);
            if (category == null) {
                // Đối tượng không tồn tại
            }
            return category;
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

                Category category = findById(id);
                if (category != null) {
                    session.delete(category);
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
