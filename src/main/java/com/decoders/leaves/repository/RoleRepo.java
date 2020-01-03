package com.decoders.leaves.repository;

import com.decoders.leaves.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Long> {
    public Role findByCode(String code);
}
