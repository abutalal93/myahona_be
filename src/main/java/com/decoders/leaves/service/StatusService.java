package com.decoders.leaves.service;

import com.decoders.leaves.entities.Status;

import java.util.List;

public interface StatusService {
    public Status findStatusByCode(String code);
    public List<Status> findStatus(String prefix);
}
