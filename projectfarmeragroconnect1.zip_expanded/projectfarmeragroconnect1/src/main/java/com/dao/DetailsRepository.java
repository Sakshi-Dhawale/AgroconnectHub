package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Details;

import java.util.List;

public interface DetailsRepository extends JpaRepository<Details, Long> {
    List<Details> findByContractor(String contractor);
}