package com.training.security.security.identity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataUsageDao extends JpaRepository<DataUsage,String> {
}
