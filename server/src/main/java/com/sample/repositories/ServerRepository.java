package com.sample.repositories;

import com.sample.model.Server;
import org.springframework.data.repository.CrudRepository;

public interface ServerRepository extends CrudRepository<Server, Integer> {}

