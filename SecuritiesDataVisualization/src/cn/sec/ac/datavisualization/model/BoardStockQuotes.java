package cn.sec.ac.datavisualization.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BoardStockQuotes implements Serializable{
	private static final long serialVersionUID = 1L;
	private String boardcode;
	private String date;
	public String getBoardcode() {
		return boardcode;
	}
	public void setBoardcode(String boardcode) {
		this.boardcode = boardcode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
