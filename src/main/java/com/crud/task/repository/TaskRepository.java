package com.crud.task.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();
}
