package com.nansl.infomanager.repository;

import com.nansl.infomanager.pojo.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {
}