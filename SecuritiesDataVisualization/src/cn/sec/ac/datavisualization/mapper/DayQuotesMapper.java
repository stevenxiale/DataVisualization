package cn.sec.ac.datavisualization.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.sec.ac.datavisualization.model.BoardStockQuotes;
import cn.sec.ac.datavisualization.model.Data;
import cn.sec.ac.datavisualization.model.Data3;
import cn.sec.ac.datavisualization.model.DayQuotes;


@Repository("dayQuotesMapper")
public interface DayQuotesMapper {
	List<Data> findCloseByDate(DayQuotes dayquotes);
	List<Data> findVolumeByDate(DayQuotes dayquotes);
	List<Data> findOpenByDate(DayQuotes dayquotes);
	List<Data> findLCloseByDate(DayQuotes dayquotes);
	List<Data3> findStockByDate(BoardStockQuotes boardstockquotes);
	List<Data3> findBoardGuarByName(BoardStockQuotes boardstockquotes);
	List<Data3> findBoardFinaByDate(BoardStockQuotes boardstockquotes);
}
