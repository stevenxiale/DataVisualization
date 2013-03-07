package cn.sec.ac.datavisualization.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sec.ac.datavisualization.model.CorrelationResult;


@Repository("correlationResultMapper")
public interface CorrelationResultMapper {

    /**
     * 返回结果根据查询条件
     *
     * @param correlationResult
     * @return
     */
    List<CorrelationResult> findResultByCondition(CorrelationResult correlationResult);

    /**
     * 返回结果根据查询条件
     *
     * @param correlationResult
     * @return
     */
    List<CorrelationResult> findItemsByCondition(CorrelationResult correlationResult);
}
