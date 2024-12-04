package ru.clevertec.carshowroom.repository.category.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.clevertec.carshowroom.entity.CategoryEntity;
import ru.clevertec.carshowroom.repository.category.CategoryRepository;
import ru.clevertec.carshowroom.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(categoryEntity);
            session.getTransaction().commit();
        }
        return categoryEntity;
    }

    @Override
    public List<CategoryEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<CategoryEntity> query = session.createQuery(
                    "SELECT c FROM CategoryEntity c " +
                            "JOIN FETCH c.carEntities",
                    CategoryEntity.class
            );

            List<CategoryEntity> categoryEntities = query.getResultList();
            session.getTransaction().commit();
            return categoryEntities;
        }
    }

    @Override
    public Optional<CategoryEntity> findCategoryById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<CategoryEntity> query = session.createQuery(
                    "SELECT c FROM CategoryEntity c " +
                            "JOIN FETCH c.carEntities " +
                            "WHERE c.id = :id",
                    CategoryEntity.class
            );
            query.setParameter("id", id);
            Optional<CategoryEntity> optionalCategoryEntity = query.list().stream().findAny();
            session.getTransaction().commit();
            return optionalCategoryEntity;
        }
    }

    @Override
    public Optional<CategoryEntity> update(CategoryEntity updatedCategoryEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            CategoryEntity existingCategoryEntity =
                    session.find(CategoryEntity.class, updatedCategoryEntity.getId());
            if (existingCategoryEntity == null) {
                session.getTransaction().rollback();
                return Optional.empty();
            }

            existingCategoryEntity.setName(updatedCategoryEntity.getName());

            session.merge(existingCategoryEntity);
            session.getTransaction().commit();

            return Optional.of(existingCategoryEntity);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            CategoryEntity categoryEntity = session.find(CategoryEntity.class, id);
            if (categoryEntity != null) {
                session.remove(categoryEntity);
                session.getTransaction().commit();
                return true;
            }
            session.getTransaction().commit();
            return false;
        }
    }
}
