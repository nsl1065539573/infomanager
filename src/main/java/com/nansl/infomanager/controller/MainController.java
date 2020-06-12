package com.nansl.infomanager.controller;

import com.nansl.infomanager.ResponseData;
import com.nansl.infomanager.pojo.Info;

import com.nansl.infomanager.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    private InfoService service;

    @Autowired
    public MainController(InfoService service){
        this.service = service;
    }


    @GetMapping("index")
    public String hello(){
        return "index";
    }



    /**
     * 获取修改信息列表
     */
    @GetMapping("findAll")
    @ResponseBody
    public ResponseData<List<Info>> findAll(){
        ResponseData<List<Info>> data = new ResponseData<>();
        data.setCode(0);
        List<Info> list = service.findAll();
        int count = list.size();
        data.setData(service.findAll());
        data.setCount(count);
        data.setDesc("success");
        return data;
    }

    /**
     * 插入一条修改记录
     */
    @PostMapping("create")
    @ResponseBody
    public Info create(@RequestParam("fileName") String fileName,
                       @RequestParam("description") String description,
                       @RequestParam("details") String details,
                       @RequestParam("opt_type")Integer opt_type){
        Info info = new Info();
        info.setDescription(description);
        info.setDetails(details);
        info.setFileName(fileName);
        info.setOpt_type(opt_type);
        info.setUpdateTime(new Date());
        info.setCreateTime(new Date());
        return service.save(info);
    }

    /**
     * 根据id查询修改记录
     */
    @GetMapping("findById")
    @ResponseBody
    public Info findById(@RequestParam("id") Integer id){
        return service.findById(id);
    }

    /**
     * 根据id修改记录
     */
    @PutMapping("update")
    @ResponseBody
    public Info update(@RequestParam("id") Integer id,
                       @RequestParam(value = "fileName", required = false) String fileName,
                       @RequestParam(value = "description", required = false) String description,
                       @RequestParam(value = "details", required = false) String details,
                       @RequestParam(value = "opt_type", required = false)Integer opt_type){
        Info info = service.findById(id);
        if (fileName != null && !"".equals(fileName)){
            info.setFileName(fileName);
        }
        if (description != null && !"".equals(description)){
            info.setDescription(description);
        }
        if (details != null && !"".equals(details)){
            info.setDetails(details);
        }
        if (opt_type != null && (0 == opt_type || 1 == opt_type)){
            info.setOpt_type(opt_type);
        }
        info.setUpdateTime(new Date());
        return service.save(info);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id")int id){
        return String.valueOf(service.delete(id));
    }
}
