package com.raisedeel.cybermanager.repository;

import com.raisedeel.cybermanager.model.Computer;
import org.springframework.data.repository.CrudRepository;

public interface ComputerRepository extends CrudRepository<Computer, Long> {
}
