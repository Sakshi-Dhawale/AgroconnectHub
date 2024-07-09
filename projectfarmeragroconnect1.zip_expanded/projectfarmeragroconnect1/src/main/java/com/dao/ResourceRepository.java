package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Resources;

@Repository
public interface ResourceRepository extends JpaRepository<Resources, Integer> {

}
