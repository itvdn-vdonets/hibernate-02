package org.example.repos;

import org.example.models.Book;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BooksRepository {

    private final SessionFactory sessionFactory;

    public BooksRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    private final Logger log = Logger.getLogger(String.valueOf(BooksRepository.class));

    public List<Book> getBooksList() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cr = cb.createQuery(Book.class);
        cr.from(Book.class);
        Query<Book> query = session.createQuery(cr);
        List<Book> results = query.getResultList();
        session.close();
        return results;
    }

    public List<Book> getAllAuthorsBooks(Integer id) {
        Session session = sessionFactory.openSession();
        NativeQuery<Book> nativeQuery = session.createNativeQuery("SELECT * FROM Book b INNER JOIN Books_Authors ba ON b.id = ba.book_id WHERE ba.author_id = ?1");
        nativeQuery.setParameter(1, id);
        nativeQuery.addEntity(Book.class);
        List<Book> list = nativeQuery.list();
        session.close();
        return list;
    }

/*    public List<Author> findAuthorsWithSalaryRange (Integer minSalary, Integer maxSalary) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select a from Author a inner join ");
        query.setParameter("minSalary", minSalary);
        query.setParameter("maxSalary", maxSalary);
        List list = query.list();
        session.close();
        return list;
    }*/


    public Book getBookById(Integer id) {
        Session session = sessionFactory.openSession();
        Book author = session.get(Book.class, id);
        session.close();
        return author;
    }

    public Book addBook(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        log.log(Level.INFO, "user added: "+book);
        return book;
    }

    @SuppressWarnings("uncheked")
    public Boolean isBookExist(Integer id) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        Query<Book> query = session.createQuery("select b from Book b where id = :id");
        query.setParameter("id", id);
        boolean isExist = query.uniqueResult() != null;
        session.close();
        log.log(Level.INFO, "User with id"+id+" exist: "+isExist);
        return isExist;
    }
}
