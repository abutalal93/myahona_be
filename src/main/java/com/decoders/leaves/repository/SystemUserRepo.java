package com.decoders.leaves.repository;

import com.decoders.leaves.entities.SystemUser;
import org.springframework.data.repository.CrudRepository;

public interface SystemUserRepo extends CrudRepository<SystemUser,Long> {

    public SystemUser findByUsername(String username);
}
