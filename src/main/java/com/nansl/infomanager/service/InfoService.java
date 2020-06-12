package com.nansl.infomanager.service;

import com.nansl.infomanager.pojo.Info;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InfoService {
    List<Info> findAll();
    Info save(Info info);
    Info findById(Integer id);
    int delete(Integer id);
}
