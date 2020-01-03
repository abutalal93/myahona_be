package com.decoders.leaves.service.impl;


import com.decoders.leaves.entities.Status;
import com.decoders.leaves.repository.StatusRepo;
import com.decoders.leaves.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepo statusRepo;

    @Override
    public Status findStatusByCode(String code) {
        return statusRepo.findStatusByCode(code);
    }
}
