package org.example.repos;

import org.example.models.Author;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AuthorRepository {

    private final SessionFactory sessionFactory;

    public AuthorRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    private final Logger log = Logger.getLogger(String.valueOf(AuthorRepository.class));

    public List<Author> getAuthorList() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cr = cb.createQuery(Author.class);
        cr.from(Author.class);
        Query<Author> query = session.createQuery(cr);
        List<Author> results = query.getResultList();
        session.close();
        return results;
    }

    public Double getAverageSalary() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Double> cr = cb.createQuery(Double.class);
        Root<Author> from = cr.from(Author.class);
        cr.select(cb.avg(from.get("salary")));
        Query<Double> query = session.createQuery(cr);
        Double singleResult = query.getSingleResult();
        session.close();
        return singleResult;
    }

    public List<Author> getAuthorsWithSalaryGreaterThen(Integer salary) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cr = cb.createQuery(Author.class);
        Root<Author> author = cr.from(Author.class);
        cr.select(author).where(cb.gt(author.get("salary"), salary));
        Query<Author> query = session.createQuery(cr);
        List<Author> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    /*HQL*/
    @SuppressWarnings("unchecked")
    public List<Author> findAuthorsWithSalaryRange (Integer minSalary, Integer maxSalary) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select a from Author a where a.salary between :minSalary and :maxSalary");
        query.setParameter("minSalary", minSalary);
        query.setParameter("maxSalary", maxSalary);
        List list = query.list();
        session.close();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAuthorListWithSql() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM Author";
        NativeQuery<Author> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.addEntity(Author.class);
        List<Author> list = nativeQuery.list();
        session.close();
        return list;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }

    public Author addAuthor(Author author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
        log.log(Level.INFO, "user added: "+author);
        return author;
    }


    public Author updateAuthorsName(Long id, String newNew) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        String oldName = author.getName();
        author.setName(newNew);
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit(); //commit = flush + commit
        session.close();
        log.log(Level.INFO, "User "+author+" has changed the name from "+oldName+" to "+author.getName());
        return author;
    }
}
