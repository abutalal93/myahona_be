package com.decoders.leaves.service;


import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.SystemUser;
import org.springframework.data.domain.Page;

public interface LeaveService {

    public Leave save(Leave leave , SystemUser systemUser);
    public Leave update(Leave leave , SystemUser systemUser);
    public Page<Leave> findAll(Leave leave, Integer page, Integer size);

}
