package com.decoders.leaves.repository;

import com.decoders.leaves.entities.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusRepo extends CrudRepository<Status,Long> {
    public Status findStatusByCode(String code);
    public Status findStatusById(Long id);

    @Query("select sts from Status sts where sts.id <=:statusId and sts.code like CONCAT ('LEAVE','%') order by sts.id asc")
    public List<Status> findStausTrack(@Param("statusId") Long statusId);

    @Query("select sts from Status sts where  sts.code like CONCAT (:code,'%')")
    public List<Status> findStatus(@Param("code") String code);
}
