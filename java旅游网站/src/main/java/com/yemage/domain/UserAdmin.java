package com.yemage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class UserAdmin implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;  //  主键
    private String email;
    private String password;  // 密码， 使用 md5 + 掩码
    private String salt; // 掩码
    private String confirmCode; // 确认码
    private LocalDateTime activationTime; // 激活失效时间
    private Byte isValid; // 0 失效 1 有效 是否可用
}
