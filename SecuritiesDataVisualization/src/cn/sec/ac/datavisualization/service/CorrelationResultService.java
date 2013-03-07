package cn.sec.ac.datavisualization.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sec.ac.datavisualization.model.CorrelationResult;

public interface CorrelationResultService {

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
