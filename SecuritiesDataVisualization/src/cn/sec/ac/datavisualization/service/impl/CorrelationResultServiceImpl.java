package cn.sec.ac.datavisualization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sec.ac.datavisualization.mapper.CorrelationResultMapper;
import cn.sec.ac.datavisualization.model.CorrelationResult;
import cn.sec.ac.datavisualization.service.CorrelationResultService;

@Service("correlationResultService")
public class CorrelationResultServiceImpl implements CorrelationResultService {

    @Autowired
    private CorrelationResultMapper correlationResultMapper;

    @Override
    public List<CorrelationResult> findResultByCondition(
            CorrelationResult correlationResult) {
        return correlationResultMapper.findResultByCondition(correlationResult);
    }

    public CorrelationResultMapper getCorrelationResultMapper() {
        return correlationResultMapper;
    }

    public void setCorrelationResultMapper(
            CorrelationResultMapper correlationResultMapper) {
        this.correlationResultMapper = correlationResultMapper;
    }

    @Override
    public List<CorrelationResult> findItemsByCondition(
            CorrelationResult correlationResult) {
        return correlationResultMapper.findItemsByCondition(correlationResult);
    }
}
