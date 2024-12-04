package ru.clevertec.carshowroom.repository.carshowroom.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.clevertec.carshowroom.entity.CarShowroomEntity;
import ru.clevertec.carshowroom.repository.carshowroom.CarShowroomRepository;
import ru.clevertec.carshowroom.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class CarShowroomRepositoryImpl implements CarShowroomRepository {

    @Override
    public CarShowroomEntity addCarShowroom(CarShowroomEntity carShowroomEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(carShowroomEntity);
            session.getTransaction().commit();
        }
        return carShowroomEntity;
    }

    @Override
    public List<CarShowroomEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<CarShowroomEntity> query = session.createQuery(
                    "SELECT c FROM CarShowroomEntity c " +
                            "LEFT JOIN FETCH c.carEntities",
                    CarShowroomEntity.class
            );

            List<CarShowroomEntity> carShowroomEntities = query.getResultList();
            session.getTransaction().commit();
            return carShowroomEntities;
        }
    }

    @Override
    public Optional<CarShowroomEntity> findCarShowroomById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<CarShowroomEntity> query = session.createQuery(
                    "SELECT c FROM CarShowroomEntity c " +
                            "LEFT JOIN FETCH c.carEntities " +
                            "WHERE c.id = :id",
                    CarShowroomEntity.class
            );
            query.setParameter("id", id);
            Optional<CarShowroomEntity> optionalCarEntity = query.list().stream().findAny();
            session.getTransaction().commit();
            return optionalCarEntity;
        }
    }

    @Override
    public Optional<CarShowroomEntity> update(CarShowroomEntity updatedCarShowroomEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            CarShowroomEntity existingCarShowroomEntity =
                    session.find(CarShowroomEntity.class, updatedCarShowroomEntity.getId());
            if (existingCarShowroomEntity == null) {
                session.getTransaction().rollback();
                return Optional.empty();
            }

            existingCarShowroomEntity.setTitle(updatedCarShowroomEntity.getTitle());
            existingCarShowroomEntity.setAddress(updatedCarShowroomEntity.getAddress());

            session.merge(existingCarShowroomEntity);
            session.getTransaction().commit();

            return Optional.of(existingCarShowroomEntity);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            CarShowroomEntity carShowroomEntity = session.find(CarShowroomEntity.class, id);
            if (carShowroomEntity != null) {
                session.remove(carShowroomEntity);
                session.getTransaction().commit();
                return true;
            }
            session.getTransaction().commit();
            return false;
        }
    }
}
