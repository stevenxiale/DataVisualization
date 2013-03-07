package cn.sec.ac.datavisualization.action;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sec.ac.datavisualization.manager.CorrelationManager;
import cn.sec.ac.datavisualization.model.CorrelationResult;

import com.opensymphony.xwork2.ActionSupport;

public class CorrelationAction extends ActionSupport{

    private String result;

    @Autowired
    private CorrelationManager correlationManager;

    public String displayCorrelation() throws IOException, ParseException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String industry = request.getParameter("industry");
        industry = new String(industry.getBytes("iso-8859-1"),"utf-8");
        String year = request.getParameter("year");

        CorrelationResult correlationResult = new CorrelationResult();


        correlationResult.setYear(year);
        correlationResult.setIndustry(industry);

        result = correlationManager.generator(correlationResult);
        System.out.println("result:  "+result.toString());
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CorrelationManager getCorrelationManager() {
        return correlationManager;
    }

    public void setCorrelationManager(CorrelationManager correlationManager) {
        this.correlationManager = correlationManager;
    }

}
