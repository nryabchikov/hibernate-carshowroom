package ru.clevertec.carshowroom.repository.client.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.clevertec.carshowroom.entity.CarEntity;
import ru.clevertec.carshowroom.entity.ClientEntity;
import ru.clevertec.carshowroom.repository.client.ClientRepository;
import ru.clevertec.carshowroom.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public ClientEntity addClient(ClientEntity clientEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(clientEntity);
            session.getTransaction().commit();
        }
        return clientEntity;
    }

    @Override
    public List<ClientEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<ClientEntity> query = session.createQuery(
                    "SELECT c FROM ClientEntity c " +
                            "LEFT JOIN FETCH c.carEntities " +
                            "LEFT JOIN FETCH c.contacts",
                    ClientEntity.class
            );

            List<ClientEntity> clientEntities = query.getResultList();

            clientEntities.forEach(carEntity -> Hibernate.initialize(carEntity.getReviewEntities()));

            session.getTransaction().commit();
            return clientEntities;
        }
    }

    @Override
    public Optional<ClientEntity> findClientById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<ClientEntity> query = session.createQuery(
                    "SELECT c FROM ClientEntity c " +
                            "LEFT JOIN FETCH c.carEntities " +
                            "LEFT JOIN FETCH c.contacts " +
                            "WHERE c.id = :id",
                    ClientEntity.class
            );
            query.setParameter("id", id);
            Optional<ClientEntity> optionalClientEntity = query.list().stream().findAny();
            optionalClientEntity.ifPresent(carEntity -> Hibernate.initialize(carEntity.getReviewEntities()));
            session.getTransaction().commit();
            return optionalClientEntity;
        }
    }

    @Override
    public Optional<ClientEntity> update(ClientEntity updatedClientEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ClientEntity existingClientEntity = session.find(ClientEntity.class, updatedClientEntity.getId());
            if (existingClientEntity == null) {
                session.getTransaction().rollback();
                return Optional.empty();
            }

            existingClientEntity.setName(updatedClientEntity.getName());
            existingClientEntity.setDateOfRegistration(updatedClientEntity.getDateOfRegistration());
            existingClientEntity.setContacts(updatedClientEntity.getContacts());

            session.merge(existingClientEntity);
            session.getTransaction().commit();

            return Optional.of(existingClientEntity);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ClientEntity clientEntity = session.find(ClientEntity.class, id);
            if (clientEntity != null) {
                session.remove(clientEntity);
                session.getTransaction().commit();
                return true;
            }
            session.getTransaction().commit();
            return false;
        }
    }

    @Override
    public void buyCar(ClientEntity clientEntity, CarEntity carEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            CarEntity persistentCar = session.find(CarEntity.class, carEntity.getId());
            ClientEntity persistentClient = session.find(ClientEntity.class, clientEntity.getId());

            persistentCar.getClientEntities().add(persistentClient);
            session.merge(persistentCar);

            session.getTransaction().commit();
        }
    }
}
