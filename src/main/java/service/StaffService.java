package service;

import model.Staff;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffService implements IStaffService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Staff findById(long id) {
        String queryStr = "SELECT staff FROM Staff AS staff WHERE staff.id =:id";
        Staff staff = entityManager.createQuery(queryStr, Staff.class).setParameter("id", id).getSingleResult();
        return staff;
    }

    @Override
    public Staff create(Staff staff) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(staff);
            transaction.commit();
            return staff;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Staff> getAllStaff() {
        String queryStr = "SELECT staff FROM Staff AS staff";
        TypedQuery<Staff> query = entityManager.createQuery(queryStr, Staff.class);
        return query.getResultList();
    }

    @Override
    public Staff update(Staff staff) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Staff editStaff = findById(staff.getId());
            editStaff.setName(staff.getName());
            editStaff.setBirthday(staff.getBirthday());
            editStaff.setAvatar(staff.getAvatar());
            editStaff.setGender(staff.getGender());
            session.saveOrUpdate(editStaff);
            transaction.commit();
            return editStaff;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public void delete(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Staff deleteStaff = findById(id);
            session.remove(deleteStaff);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Staff> findByName(String name) {
        String queryStr = "SELECT staff FROM Staff AS staff WHERE staff.name like :name";
        TypedQuery<Staff> query = entityManager.createQuery(queryStr, Staff.class).setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
