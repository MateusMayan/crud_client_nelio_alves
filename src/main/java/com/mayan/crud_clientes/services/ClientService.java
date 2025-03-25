package com.mayan.crud_clientes.services;

import com.mayan.crud_clientes.dtos.ClientDTO;
import com.mayan.crud_clientes.entities.Client;
import com.mayan.crud_clientes.repositories.ClientRepository;
import com.mayan.crud_clientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(c -> new ClientDTO(c));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client result = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado.")
        );
        return new ClientDTO(result);
    }

    @Transactional
    public ClientDTO create(ClientDTO dto) {
        Client result = new Client();
        copyDtoToEntity(dto, result);
        result = repository.save(result);
        return new ClientDTO(result);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client result = repository.getReferenceById(id);
            copyDtoToEntity(dto, result);
            result = repository.save(result);
            return new ClientDTO(result);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado.");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Falha de integridade referencial.");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client result) {
        result.setName(dto.getName());
        result.setCpf(dto.getCpf());
        result.setIncome(dto.getIncome());
        result.setBirthDate(dto.getBirthDate());
        result.setChildren(dto.getChildren());
    }


}
