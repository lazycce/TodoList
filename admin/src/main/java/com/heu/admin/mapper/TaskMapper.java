package com.heu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heu.admin.entity.TaskList;
import com.heu.admin.entity.vo.TaskListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-16 21:13
 */
@Repository
public interface TaskMapper extends BaseMapper<TaskList> {
    /**
     * 根据userId查询任务清单
     * @author qiuao
     * @date 2024/4/22 21:05
     * @param userId
     * @return List<TaskList>
    */
    List<TaskListVo> selectTaskList(@Param("userId") Long userId);
}
