package com.dcits.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author xuzzb
 * @Create 2022/5/19
 */
public class Course {
    private int cid;
    private String cname;
    private int userid;
    private String cstatus;

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public int getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public int getUserid() {
        return userid;
    }

    public String getCstatus() {
        return cstatus;
    }
}