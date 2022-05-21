package com.dcits.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author xuzzb
 * @Create 2022/5/19
 */
@TableName("course")
public class Course {
    private int cid;
    private String cname;
    @TableField("userid")
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