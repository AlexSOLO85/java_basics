package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Closeable;

public class SessionService implements Closeable {
    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession() {
        return HibernateSessionFactory.getSessionFactory().openSession();
    }

    public void openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
    }

    public void closeTransactionSession() {
        transaction.commit();
    }

    @Override
    public void close() throws HibernateException {
        if (session != null) {
            session.close();
            System.out.println("Session closed");
        }
    }
}