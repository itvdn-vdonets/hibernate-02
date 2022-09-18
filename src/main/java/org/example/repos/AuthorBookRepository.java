package org.example.repos;

import org.example.models.AuthorBookDto;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Logger;


public class AuthorBookRepository {

    private final SessionFactory sessionFactory;

    public AuthorBookRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    private final Logger log = Logger.getLogger(String.valueOf(AuthorBookRepository.class));

    @SuppressWarnings("unchecked")
    public void addAuthorToBook(Integer authorId, Integer bookId) {
        AuthorBookDto authorBookDto = new AuthorBookDto(authorId, bookId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(authorBookDto);
        session.getTransaction().commit();
        session.close();
    }

    public List<AuthorBookDto> getAuthorBookDtoByAuthorId(Integer id) {
        Session session = sessionFactory.openSession();
        Query<AuthorBookDto> query = session.createQuery("select ab from AuthorBookDto ab where ab.authorId = :id");
        query.setParameter("id", id);
        List<AuthorBookDto> list = query.list();
        session.close();
        return list;
    }

    public List<AuthorBookDto> getAuthorBookDtoByBookId(Integer id) {
        Session session = sessionFactory.openSession();
        Query<AuthorBookDto> query = session.createQuery("select ab from AuthorBookDto ab where ab.bookId = :id");
        query.setParameter("id", id);
        List<AuthorBookDto> list = query.list();
        session.close();
        return list;
    }



}
