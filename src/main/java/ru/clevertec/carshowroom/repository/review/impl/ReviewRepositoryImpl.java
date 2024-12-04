package ru.clevertec.carshowroom.repository.review.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.clevertec.carshowroom.entity.CarEntity;
import ru.clevertec.carshowroom.entity.ClientEntity;
import ru.clevertec.carshowroom.entity.ReviewEntity;
import ru.clevertec.carshowroom.exception.CarNotFoundException;
import ru.clevertec.carshowroom.exception.ClientNotFoundException;
import ru.clevertec.carshowroom.repository.review.ReviewRepository;
import ru.clevertec.carshowroom.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class ReviewRepositoryImpl implements ReviewRepository {
    @Override
    public ReviewEntity addReview(ReviewEntity reviewEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            CarEntity carEntity = session.find(CarEntity.class, reviewEntity.getCarEntity().getId());
            if (carEntity == null) {
                throw CarNotFoundException.byId(reviewEntity.getCarEntity().getId());
            }

            ClientEntity clientEntity = session.find(ClientEntity.class, reviewEntity.getClientEntity().getId());
            if (clientEntity == null) {
                throw ClientNotFoundException.byId(reviewEntity.getClientEntity().getId());
            }

            Hibernate.initialize(clientEntity.getContacts());

            reviewEntity.setCarEntity(carEntity);
            reviewEntity.setClientEntity(clientEntity);

            session.persist(reviewEntity);
            session.getTransaction().commit();
        }
        return reviewEntity;
    }

    @Override
    public List<ReviewEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<ReviewEntity> query = session.createQuery(
                    "SELECT c FROM ReviewEntity c " +
                            "JOIN FETCH c.carEntity " +
                            "JOIN FETCH c.clientEntity",
                    ReviewEntity.class
            );

            List<ReviewEntity> reviewEntities = query.getResultList();
            session.getTransaction().commit();
            return reviewEntities;
        }
    }

    @Override
    public Optional<ReviewEntity> findReviewById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<ReviewEntity> query = session.createQuery(
                    "SELECT c FROM ReviewEntity c " +
                            "JOIN FETCH c.carEntity " +
                            "JOIN FETCH c.clientEntity " +
                            "WHERE c.id = :id",
                    ReviewEntity.class
            );
            query.setParameter("id", id);
            Optional<ReviewEntity> optionalReviewEntity = query.list().stream().findAny();
            session.getTransaction().commit();
            return optionalReviewEntity;
        }
    }

    @Override
    public Optional<ReviewEntity> update(ReviewEntity updatedReviewEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ReviewEntity existingReviewEntity = session.find(ReviewEntity.class, updatedReviewEntity.getId());
            if (existingReviewEntity == null) {
                session.getTransaction().rollback();
                return Optional.empty();
            }

            existingReviewEntity.setText(updatedReviewEntity.getText());
            existingReviewEntity.setRating(updatedReviewEntity.getRating());
            existingReviewEntity.setCarEntity(updatedReviewEntity.getCarEntity());
            existingReviewEntity.setClientEntity(updatedReviewEntity.getClientEntity());


            CarEntity carEntity = session.find(CarEntity.class, updatedReviewEntity.getCarEntity().getId());
            if (carEntity == null) {
                throw CarNotFoundException.byId(updatedReviewEntity.getCarEntity().getId());
            }


            ClientEntity clientEntity = session.find(ClientEntity.class, updatedReviewEntity.getClientEntity().getId());
            if (clientEntity == null) {
                throw ClientNotFoundException.byId(updatedReviewEntity.getClientEntity().getId());
            }

            Hibernate.initialize(clientEntity.getContacts());

            existingReviewEntity.setCarEntity(carEntity);
            existingReviewEntity.setClientEntity(clientEntity);

            session.merge(existingReviewEntity);
            session.getTransaction().commit();

            return Optional.of(existingReviewEntity);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ReviewEntity reviewEntity = session.find(ReviewEntity.class, id);
            if (reviewEntity != null) {
                session.remove(reviewEntity);
                session.getTransaction().commit();
                return true;
            }
            session.getTransaction().commit();
            return false;
        }
    }
}
