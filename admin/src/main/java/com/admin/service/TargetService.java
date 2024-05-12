package com.admin.service;

import com.admin.entity.Target;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-28 20:54
 */
public interface TargetService extends IService<Target> {
    /**
     * 新增目标
     * @author admin
     * @date 2024/4/28 21:21
     * @param target
     * @param userId
     * @return Long
    */
    Long addTarget(Target target, Long userId);

    /**
     * 删除目标记录
     * @author admin
     * @date 2024/4/28 21:43
     * @param targetIds
    */
    void delete(Long[] targetIds);

}
