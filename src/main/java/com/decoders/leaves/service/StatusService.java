package com.decoders.leaves.service;

import com.decoders.leaves.entities.Status;

public interface StatusService {
    public Status findStatusByCode(String code);
}
