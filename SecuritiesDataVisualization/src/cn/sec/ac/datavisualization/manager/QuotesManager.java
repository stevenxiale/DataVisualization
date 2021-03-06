package cn.sec.ac.datavisualization.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.stream.JsonWriter;

import cn.sec.ac.datavisualization.model.BoardStockQuotes;
import cn.sec.ac.datavisualization.model.Data;
import cn.sec.ac.datavisualization.model.Data3;
import cn.sec.ac.datavisualization.model.DayQuotes;
import cn.sec.ac.datavisualization.model.IndicatorData;
import cn.sec.ac.datavisualization.model.StockData;
import cn.sec.ac.datavisualization.service.DayQuotesService;



@Service("quotesManager")
public class QuotesManager {


    @Autowired
    private DayQuotesService dayQuotesService;


    public DayQuotesService getStockDayService() {
		return dayQuotesService;
	}

	public void setStockDayService(DayQuotesService stockDayService) {
		this.dayQuotesService = stockDayService;
	}

	public String generatorDayQuotes(String stockcode,String params,String rangdate) throws IOException, ParseException {
		StringWriter json = new StringWriter();
        JsonWriter writer = new JsonWriter(json);

        String[] rdates = rangdate.split("-");
        String startdate = rdates[0];
        String enddate = rdates[1];
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        Date sdate = sdf.parse(startdate);
        Date edate = sdf.parse(enddate);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/mm/dd");
        startdate = sdf1.format(sdate);
        enddate = sdf1.format(edate);


        List<IndicatorData> results = new ArrayList<IndicatorData>();
        String[] ps = params.split(",");
        for(int i = 0; i<ps.length;i++){
        	String tempParam = ps[i];
        	List<Data> list;
        	IndicatorData indata = new IndicatorData();
        	indata.setKey(tempParam);
        	if(0==i){
        		indata.setBar(true);
        	}else{
        		indata.setBar(false);
        	}
        	DayQuotes dayQuotes = new DayQuotes();
        	dayQuotes.setStockcode(stockcode);
        	dayQuotes.setParam(tempParam);
        	dayQuotes.setBegindate(startdate);
        	dayQuotes.setEnddate(enddate);
        	if(tempParam.equals("close")){
        		list = dayQuotesService.findDayQuotesClose(dayQuotes);
        	}else if(tempParam.equals("volume")){
        		list = dayQuotesService.findDayQuotesVolume(dayQuotes);
        	}else if(tempParam.equals("lclose")){
        		list = dayQuotesService.findDayQuotesLClose(dayQuotes);
        	}else if(tempParam.equals("open")){
        		list = dayQuotesService.findDayQuotesOpen(dayQuotes);
        	}else{
        		list = null;
        	}
        	indata.setValues(list);
        	results.add(indata);
        }

        writeIndiList(writer,results);
        writer.close();

        return json.getBuffer().toString();
    }

	public String generatorMulStockQuotes(String codes,String param,String rangdate) throws IOException, ParseException {
		StringWriter json = new StringWriter();
        JsonWriter writer = new JsonWriter(json);

        String[] rdates = rangdate.split("-");
        String startdate = rdates[0];
        String enddate = rdates[1];
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        Date sdate = sdf.parse(startdate);
        Date edate = sdf.parse(enddate);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/mm/dd");
        startdate = sdf1.format(sdate);
        enddate = sdf1.format(edate);


        List<StockData> results = new ArrayList<StockData>();
        String[] ps = codes.split(",");
        for(int i = 0; i<ps.length;i++){
        	String tempCode = ps[i];
        	List<Data> list;
        	StockData sdata = new StockData();
        	sdata.setCode(tempCode);

        	DayQuotes dayQuotes = new DayQuotes();
        	dayQuotes.setStockcode(tempCode);
        	dayQuotes.setParam(param);
        	dayQuotes.setBegindate(startdate);
        	dayQuotes.setEnddate(enddate);
        	if(param.equals("close")){
        		list = dayQuotesService.findDayQuotesClose(dayQuotes);
        	}else if(param.equals("volume")){
        		list = dayQuotesService.findDayQuotesVolume(dayQuotes);
        	}else if(param.equals("lclose")){
        		list = dayQuotesService.findDayQuotesLClose(dayQuotes);
        	}else if(param.equals("open")){
        		list = dayQuotesService.findDayQuotesOpen(dayQuotes);
        	}else{
        		list = null;
        	}
        	sdata.setValues(list);
        	results.add(sdata);
        }

        writeStockList(writer,results);
        writer.close();

        return json.getBuffer().toString();
    }


	public String generatorBoardStockSQuotes(String boardcode,String date) throws IOException{
		StringWriter json = new StringWriter();
        JsonWriter writer = new JsonWriter(json);
        BoardStockQuotes quotes = new BoardStockQuotes();
        quotes.setBoardcode("5");
        quotes.setDate("2013-02-14");
        List<Data3> list = dayQuotesService.findBoardStockByDate(quotes);
        writerBoardStock(writer,list);
        writer.close();
		return json.getBuffer().toString();
	}


	public String generatorBoardStockFinaQuotes(String industry,String date) throws IOException{
		StringWriter json = new StringWriter();
        JsonWriter writer = new JsonWriter(json);
        String[] industries = industry.split(", ");
        List<StockData> results = new ArrayList<StockData>();
        for(String ind:industries){
        	BoardStockQuotes quotes = new BoardStockQuotes();
        	quotes.setBoardcode(ind);
        	quotes.setDate(date);
        	List<Data3> datalist = dayQuotesService.findBoardStockFinaByDate(quotes);
        	StockData sdata = new StockData();
        	sdata.setCode(ind);
        	sdata.setValues(datalist);
        	results.add(sdata);
        }

        writerBoardStockFinals(writer,results);

        writer.close();
		return json.getBuffer().toString();
	}

	public String generatorTreemapJson() throws IOException{
		StringWriter json = new StringWriter();
        JsonWriter writer = new JsonWriter(json);
        String boards = "交运设备,交通运输,信息服务,信息设备,公用事业,农林牧渔,化工,医药生物,商业贸易,家用电器,建筑建材,房地产,有色金属,机械设备,电子,纺织服装,综合,轻工制造,采掘,金融服务,食品饮料,餐饮旅游,黑色金属";
        String[] boardlist = boards.split(",");
        //开始根目录
        writer.beginObject();
        writer.name("name").value("根目录");
        writer.name("children");
        //开始行业列表
        writer.beginArray();
        for(String board:boardlist){
        	writer.beginObject();
        	writer.name("name").value(board);
        	writer.name("children");
        	//开始行业的每个年份
        	BoardStockQuotes quotes = new BoardStockQuotes();
        	quotes.setBoardcode(board);
        	quotes.setDate("2013-02-14");
        	List<Data3> list = dayQuotesService.findBoardGUARByName(quotes);
        	writer.beginArray();
        	for(Data3 data:list){
        		writer.beginObject();
        		writer.name("name").value(data.getValuet());
        		writer.name("size").value(data.getValuese());
        		writer.endObject();
        	}
        	writer.endArray();
        	//结束行业的每个年份

        	writer.endObject();
        }
        writer.endArray();
        //结束行业列表

        writer.endObject();
        //结束根目录

        writer.close();
        File file = new File("D://hello.json");
        System.out.println("path:"+file.getAbsolutePath());
        FileWriter fw = new FileWriter(file);
        fw.write(json.getBuffer().toString());
        fw.flush();
        fw.close();

		return json.getBuffer().toString();
	}

	private void writerBoardStockFinals(JsonWriter writer, List<StockData> list) throws IOException{
		writer.beginArray();
		for(StockData sdata:list){
			writer.beginObject();
			writer.name("key").value(sdata.getCode());
			writer.name("values");
			writeBoardStockDetails(writer,sdata.getValues());
			writer.endObject();
		}
		writer.endArray();

	}

	private void writeBoardStockDetails(JsonWriter writer, List<Data3> list) throws IOException {
        writer.beginArray();
        for (Data3 data : list) {
        	writeData3(writer,data);
        }
        writer.endArray();
    }



	private void writerBoardStock(JsonWriter writer, List<Data3> list) throws IOException{
		writer.beginArray();
		writeStockofBoard(writer,list);
		writer.endArray();
	}

	private void writeStockofBoard (JsonWriter writer, List<Data3> list) throws IOException{
		writer.beginObject();
		writer.name("key").value("房地产业");
		writer.name("values");
		writeData3List(writer,list);
		writer.endObject();
	}

	 private void writeData3List(JsonWriter writer, List<Data3> list) throws IOException {
	        writer.beginArray();
	        for (Data3 data : list) {
	          writeData3(writer, data);
	        }
	        writer.endArray();
	 }

	 private void writeData3(JsonWriter writer,  Data3 data) throws IOException {
		 writer.beginObject();
		 writer.name("x").value(data.getValuef());
		 writer.name("y").value(data.getValuese());
		 writer.name("size").value(data.getValuet());
		 writer.name("shape").value("circle");
		 writer.name("code").value(data.getStockcode());
		 writer.endObject();
	 }


	private void writeIndiList(JsonWriter writer, List<IndicatorData> indis) throws IOException{
		writer.beginArray();
		for(IndicatorData indi : indis ){
			writeIndi(writer,indi);
		}
		writer.endArray();
	}

	private void writeStockList(JsonWriter writer, List<StockData> indis) throws IOException{
		writer.beginArray();
		for(StockData indi : indis ){
			writeStock(writer,indi);
		}
		writer.endArray();
	}

	private void writeIndi(JsonWriter writer, IndicatorData indi) throws IOException{
		writer.beginObject();
		writer.name("key").value(indi.getKey());
		if(true == indi.getBar()){
			writer.name("bar").value(indi.getBar());
		}
		writer.name("values");
		writeDataList(writer,indi.getValues());
		writer.endObject();
	}

	private void writeStock(JsonWriter writer, StockData indi) throws IOException{
		writer.beginObject();
		writer.name("key").value(indi.getCode());
		writer.name("values");
		writeDataList(writer,indi.getValues());
		writer.endObject();
	}


    private void writeDataList(JsonWriter writer, List<Data> dataList) throws IOException {
        writer.beginArray();
        for (Data data : dataList) {
          writeData(writer, data);
        }
        writer.endArray();
    }

    private void writeData(JsonWriter writer,  Data data) throws IOException {
//    	List ls = new ArrayList();
//    	ls.add(data.getTime().getTime());
//    	ls.add(data.getValue());
    	writer.beginArray();
//    	for(int i=0;i<ls.size();i++){
//    		writer.value(ls.get(i).toString());
//    	}
    	writer.value(data.getTime().getTime());
    	writer.value(data.getValue());
    	writer.endArray();
//        writer.beginObject();
//        writer.name(data.getTime()).value(data.getValue());
//        writer.endObject();
    }

}
