package com.decoders.leaves.repository;

import com.decoders.leaves.entities.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepo extends CrudRepository<Status,Long> {
    public Status findStatusByCode(String code);
}
