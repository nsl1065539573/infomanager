package com.nansl.infomanager.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Info {
    // id 主键
    @Id
    @GeneratedValue
    private Integer id;

    // 修改的文件
    private String fileName;

    // 为什么修改
    private String description;

    // 做了什么修改
    private String details;

    // 操作类型  0 新增  1 修改
    private Integer opt_type;

    // 创建时间
    private Date createTime;

    // 最后一次更新时间
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOpt_type() {
        return opt_type;
    }

    public void setOpt_type(Integer opt_type) {
        this.opt_type = opt_type;
    }

}
