package org.example2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ClientRepository {
    EntityManagerFactory emf;
    EntityManager em;

    public ClientRepository() {
        emf = Persistence.createEntityManagerFactory("Demo");
        em = emf.createEntityManager();
    }

    public Client getById(int id) {
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        em.getTransaction().commit();
        return client;
    }

    public void add(Client c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    public void remove(Client c) {
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Client> getAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT clients FROM Client clients");
        List<Client> clients = query.getResultList();
        em.getTransaction().commit();
        return clients;
    }

}
