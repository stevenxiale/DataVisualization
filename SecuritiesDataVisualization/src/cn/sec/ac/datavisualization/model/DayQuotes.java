package cn.sec.ac.datavisualization.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DayQuotes implements Serializable{
	private static final long serialVersionUID = 1L;
	private String marketcode;
	private String stockcode;
	private String begindate;
	private String enddate;
	private String param;


	public String getMarketcode() {
		return marketcode;
	}
	public void setMarketcode(String marketcode) {
		this.marketcode = marketcode;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}


	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}



}
