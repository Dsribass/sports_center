package org.example.domain.usecases.client;

import org.example.domain.entities.client.Client;
import org.example.domain.usecases.utils.EntityAlreadyExistsException;
import org.example.domain.usecases.utils.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class ClientUseCase {
    ClientDAO clientDAO;

    public ClientUseCase(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public String insert(Client client) {
        validatorClient(client);

        if(clientDAO.findOne(client.getCpf()).isPresent())
            throw new EntityAlreadyExistsException("Client already exists in database");

        return clientDAO.insert(client);
    }

    public boolean update(String cpf, Client client) {
        if(cpf == null)
            throw new IllegalArgumentException("CPF cannot be null");

        validatorClient(client);

        if(clientDAO.findOne(cpf).isEmpty())
            throw new EntityNotFoundException("Client was not found");

        return clientDAO.update(cpf,client);
    }

    public boolean delete(Client client){
        if(client == null)
            throw new IllegalArgumentException("Client cannot be null");
        return deleteByCPF(client.getCpf());
    }

    public boolean deleteByCPF(String cpf) {
        if(cpf == null)
            throw new IllegalArgumentException("CPF cannot be null");

        if(clientDAO.findOne(cpf).isEmpty())
            throw new EntityNotFoundException("Client was not found");

        return clientDAO.delete(cpf);
    }

    public List<Client> fetchAll() {
        return clientDAO.fetchAll();
    }

    public Optional<Client> findOne(String cpf) {
        if(cpf == null)
            throw new IllegalArgumentException("CPF cannot be null");
        return clientDAO.findOne(cpf);
    }

    private void validatorClient(Client client) {
        if(client == null)
            throw new IllegalArgumentException("Client cannot be null");
        if(client.getCpf() == null)
            throw new IllegalArgumentException("CPF cannot be null");
        if(client.getName() == null)
            throw new IllegalArgumentException("Name cannot be null");
        if(client.getAddress().getCity() == null)
            throw new IllegalArgumentException("City cannot be null");
        if(client.getAddress().getState() == null)
            throw new IllegalArgumentException("State cannot be null");
        if(client.getAddress().getRoad() == null)
            throw new IllegalArgumentException("Road cannot be null");
        if(client.getAddress().getNumber() == null)
            throw new IllegalArgumentException("House Number cannot be null");
    }
}
