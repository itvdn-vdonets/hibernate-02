package org.example2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClientRepository clientRepository = new ClientRepository();
        Client client = new Client("Victor", 30, "3809524552");
        clientRepository.add(client);
        Client clientFromDb = clientRepository.getById(client.getId());

        System.out.println(client.getId() + " " + client.getName() + " "
                    + client.getAge() + " " + client.getPhone());



        List<Client> clients = clientRepository.getAll();

        for (Client client1 : clients) {
            System.out.println(client1.getId    () + " " + client1.getName() + " "
                    + client1.getAge() + " " + client1.getPhone());
        }

    }

}
