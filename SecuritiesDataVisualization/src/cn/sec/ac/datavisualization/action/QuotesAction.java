package cn.sec.ac.datavisualization.action;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;



import cn.sec.ac.datavisualization.manager.QuotesManager;

import com.opensymphony.xwork2.ActionSupport;

public class QuotesAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private QuotesManager quotesManager;

    private String result;

    public String displayData() throws IOException, ParseException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String stockcode = request.getParameter("stockcode");
        String indicator = request.getParameter("indicator");
        String rangdate = request.getParameter("rangdate");
        //result = "{'chart':{'caption':'Yearly Sales', 'xaxisname':'Year', 'yaxisname':'Sales' },'data':[{ 'label':'2004', 'value':'37800' },{ 'label':'2005', 'value':'21900'  },{ 'label':'2006', 'value':'32900'  },{ 'label':'2007', 'value':'39800'  }]}";
        result = quotesManager.generatorDayQuotes(stockcode,indicator,rangdate);
        System.out.println("result:  "+result.toString());
        return SUCCESS;
    }

    public String displayMulStocksData() throws IOException, ParseException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String codes = request.getParameter("stockcode");
        String indicator = request.getParameter("indicator");
        String rangdate = request.getParameter("rangdate");
        //result = "{'chart':{'caption':'Yearly Sales', 'xaxisname':'Year', 'yaxisname':'Sales' },'data':[{ 'label':'2004', 'value':'37800' },{ 'label':'2005', 'value':'21900'  },{ 'label':'2006', 'value':'32900'  },{ 'label':'2007', 'value':'39800'  }]}";
        result = quotesManager.generatorMulStockQuotes(codes,indicator,rangdate);
        System.out.println("result:  "+result.toString());
        return SUCCESS;
    }

    public String displayBoardStocksData() throws IOException, ParseException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String boardcode = request.getParameter("boardcode");
        String date = request.getParameter("date");
        //result = "{'chart':{'caption':'Yearly Sales', 'xaxisname':'Year', 'yaxisname':'Sales' },'data':[{ 'label':'2004', 'value':'37800' },{ 'label':'2005', 'value':'21900'  },{ 'label':'2006', 'value':'32900'  },{ 'label':'2007', 'value':'39800'  }]}";
        result = quotesManager.generatorBoardStockSQuotes(boardcode,date);
        System.out.println("result:  "+result.toString());
        return SUCCESS;
    }

    public String displayBoardStocksFinaData() throws IOException, ParseException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String industry = request.getParameter("industry");
        String date = request.getParameter("date");
        //result = "{'chart':{'caption':'Yearly Sales', 'xaxisname':'Year', 'yaxisname':'Sales' },'data':[{ 'label':'2004', 'value':'37800' },{ 'label':'2005', 'value':'21900'  },{ 'label':'2006', 'value':'32900'  },{ 'label':'2007', 'value':'39800'  }]}";
        result = quotesManager.generatorBoardStockFinaQuotes(industry,date);
        System.out.println("result:  "+result.toString());
        return SUCCESS;
    }

    public String displayTreemapData() throws IOException, ParseException {
        //result = "{'chart':{'caption':'Yearly Sales', 'xaxisname':'Year', 'yaxisname':'Sales' },'data':[{ 'label':'2004', 'value':'37800' },{ 'label':'2005', 'value':'21900'  },{ 'label':'2006', 'value':'32900'  },{ 'label':'2007', 'value':'39800'  }]}";
        result = quotesManager.generatorTreemapJson();
        System.out.println("result:  "+result.toString());
        return SUCCESS;
    }



    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


	public QuotesManager getQuotesManager() {
		return quotesManager;
	}

	public void setQuotesManager(QuotesManager quotesManager) {
		this.quotesManager = quotesManager;
	}


}
