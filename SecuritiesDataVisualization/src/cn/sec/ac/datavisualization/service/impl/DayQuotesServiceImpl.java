package cn.sec.ac.datavisualization.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sec.ac.datavisualization.mapper.DayQuotesMapper;
import cn.sec.ac.datavisualization.model.BoardStockQuotes;
import cn.sec.ac.datavisualization.model.Data;
import cn.sec.ac.datavisualization.model.Data3;
import cn.sec.ac.datavisualization.model.DayQuotes;
import cn.sec.ac.datavisualization.service.DayQuotesService;


@Service("dayQuotesService")
public class DayQuotesServiceImpl implements DayQuotesService{

	private static Logger logger = Logger.getLogger(DayQuotesServiceImpl.class);

    @Autowired
    private DayQuotesMapper  dayQuotesMapper;


	@Override
	public List<Data> findDayQuotesClose(DayQuotes dayquotes) {
		// TODO Auto-generated method stub
		return dayQuotesMapper.findCloseByDate(dayquotes);
	}

	public List<Data> findDayQuotesVolume(DayQuotes dayquotes){
		return dayQuotesMapper.findVolumeByDate(dayquotes);
	}

	@Override
	public List<Data> findDayQuotesLClose(DayQuotes dayquotes) {
		// TODO Auto-generated method stub
		return dayQuotesMapper.findLCloseByDate(dayquotes);
	}

	@Override
	public List<Data> findDayQuotesOpen(DayQuotes dayquotes) {
		// TODO Auto-generated method stub
		return dayQuotesMapper.findOpenByDate(dayquotes);
	}

	@Override
	public List<Data3> findBoardStockByDate(BoardStockQuotes boardstockquotes) {
		// TODO Auto-generated method stub
		return dayQuotesMapper.findStockByDate(boardstockquotes);
	}

	@Override
	public List<Data3> findBoardGUARByName(BoardStockQuotes boardstockquotes) {
		// TODO Auto-generated method stub
		return dayQuotesMapper.findBoardGuarByName(boardstockquotes);
	}

	@Override
	public List<Data3> findBoardStockFinaByDate(BoardStockQuotes boardstockquotes){
		return dayQuotesMapper.findBoardFinaByDate(boardstockquotes);
	}



}
