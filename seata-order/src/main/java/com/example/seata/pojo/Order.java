package com.example.seata.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hqf
 * @Date: 2023/4/24 14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order")
public class Order {
    @TableId
    private Long id;
    private Integer productId;
}
