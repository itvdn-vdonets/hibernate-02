package com.itvdn.jdbcandhibernate.ex001_jpa_crud;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClientRepository cr = new ClientRepository();
        Client client = cr.getById(7);
//
        System.out.println(client.getId() + " " + client.getName() + " "
                    + client.getAge() + " " + client.getPhone());
//
        cr.remove(client);
//        client.setName("Petro");
//        client.setAge(24);
//        client.setPhone("+380503117088");
//
//        cr.add(client);

        List<Client> clients = cr.getAll();

        for (Client client1 : clients) {
            System.out.println(client1.getId() + " " + client1.getName() + " "
                    + client1.getAge() + " " + client1.getPhone());
        }

    }

}
