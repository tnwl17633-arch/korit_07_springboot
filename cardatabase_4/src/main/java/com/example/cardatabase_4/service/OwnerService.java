package com.example.cardatabase_4.service;

import com.example.cardatabase_4.domain.Car;
import com.example.cardatabase_4.domain.Owner;
import com.example.cardatabase_4.domain.OwnerRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) { this.ownerRepository = ownerRepository; }

    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Optional<Owner> getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId);
    }

    public boolean deleteOwner(Long ownerId) {
        if (ownerRepository.existsById(ownerId)) {
            ownerRepository.deleteById(ownerId);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Owner> updateOwner(Long ownerId, Owner ownerDetails) {
        return ownerRepository.findById(ownerId)
                .map(owner -> {
                    owner.setFirstName(ownerDetails.getFirstName());
                    owner.setLastName(ownerDetails.getLastName());
                    return owner;
                });
    }
}
