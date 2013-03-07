package cn.sec.ac.datavisualization.service;

import java.util.List;

import cn.sec.ac.datavisualization.model.BoardStockQuotes;
import cn.sec.ac.datavisualization.model.Data;
import cn.sec.ac.datavisualization.model.Data3;
import cn.sec.ac.datavisualization.model.DayQuotes;


public interface DayQuotesService {
	List<Data> findDayQuotesClose(DayQuotes dayquotes);
	List<Data> findDayQuotesVolume(DayQuotes dayquotes);
	List<Data> findDayQuotesOpen(DayQuotes dayquotes);
	List<Data> findDayQuotesLClose(DayQuotes dayquotes);
	List<Data3> findBoardStockByDate(BoardStockQuotes boardstockquotes);
	List<Data3> findBoardGUARByName(BoardStockQuotes boardstockquotes);
	List<Data3> findBoardStockFinaByDate(BoardStockQuotes boardstockquotes);

}
