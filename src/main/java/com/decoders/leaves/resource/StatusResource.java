package com.decoders.leaves.resource;


import com.decoders.leaves.entities.Status;
import com.decoders.leaves.entities.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatusResource {

    private Long id;
    private String code;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static StatusResource toResource(Status status)
    {
        StatusResource statusResource = new StatusResource();

        statusResource.setId(status.getId());
        statusResource.setCode(status.getCode());
        statusResource.setDescription(status.getDescription());

        return statusResource;
    }

    public static List<StatusResource> toResource(List<Status> statusList){
        List<StatusResource> statusResourceList = new ArrayList<>();
        statusList.forEach(status -> {
            StatusResource statusResource = toResource(status);
            statusResourceList.add(statusResource);
        });
        return statusResourceList;
    }

    public Status toStatus(){
        Status status = new Status();
        status.setId(this.id);
        status.setCode(this.code);
        status.setDescription(this.description);

        return status;
    }
}
