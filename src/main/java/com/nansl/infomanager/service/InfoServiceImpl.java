package com.nansl.infomanager.service;

import com.nansl.infomanager.pojo.Info;
import com.nansl.infomanager.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InfoServiceImpl implements InfoService {

    private InfoRepository repository;

    @Autowired
    public InfoServiceImpl(InfoRepository repository){
        this.repository = repository;
    }

    /**
     * 获取所有的改动集合
     * @return 所有改动信息的集合
     */
    @Override
    public List<Info> findAll() {
        return repository.findAll();
    }

    @Override
    public Info save(Info info) {
        return repository.save(info);
    }

    @Override
    public Info findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public int delete(Integer id) {
        repository.deleteById(id);
        return 0;
    }
}
