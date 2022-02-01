package com.spring.repository;

import com.spring.dao.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

//    List<TaskEntity> findAllByTaskOwner(UserEntity userEntity);


}
