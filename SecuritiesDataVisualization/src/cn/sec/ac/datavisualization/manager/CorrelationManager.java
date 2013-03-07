package cn.sec.ac.datavisualization.manager;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sec.ac.datavisualization.model.CorrelationItem;
import cn.sec.ac.datavisualization.model.CorrelationResult;
import cn.sec.ac.datavisualization.service.CorrelationResultService;

import com.google.gson.stream.JsonWriter;



@Service("correlationManager")
public class CorrelationManager {

    @Autowired
    private CorrelationResultService correlationResultService;


	public String generator(CorrelationResult correlationResult) throws IOException, ParseException {
		StringWriter json = new StringWriter();
        JsonWriter writer = new JsonWriter(json);

        List<CorrelationResult> itemList = correlationResultService.findItemsByCondition(correlationResult);

        List<CorrelationResult> list = correlationResultService.findResultByCondition(correlationResult);

        Set<CorrelationItem> set= new HashSet<CorrelationItem>();

        CorrelationItem item = null;
        CorrelationItem targetItem = null;

        for (CorrelationResult temp : itemList) {
            item = new CorrelationItem();
            item.setId(temp.getSource());
            item.setName(temp.getSourceName());
            item.setValue(13 + new Random().nextInt(20));

            targetItem = new CorrelationItem();
            targetItem.setId(temp.getTarget());
            targetItem.setName(temp.getTargetName());
            targetItem.setValue(10);

            set.add(item);
            set.add(targetItem);
        }

        writer.beginArray();
        writerItemHead(writer);
        writerItems(writer, set);
        writerHead(writer);
        writerResults(writer, list);
        writer.endArray();
        writer.close();

        System.out.println(json.getBuffer().toString());
        return json.getBuffer().toString();
    }

    private void writerResults(JsonWriter writer, List<CorrelationResult> list) throws IOException {
        for (CorrelationResult correlationResult : list) {
            writer.beginArray();
            writer.value(correlationResult.getSource());
            writer.value(correlationResult.getTarget());
            writer.value(correlationResult.getCount() * 20);
            writer.endArray();
        }
    }

    private void writerHead(JsonWriter writer) throws IOException{
        writer.beginArray();
        writer.value("Source");
        writer.value("Target");
        writer.value("Value");
        writer.endArray();
    }

    private void writerItems(JsonWriter writer, Set<CorrelationItem> itemSet) throws IOException{
        for (CorrelationItem correlationItem : itemSet) {
            writer.beginArray();
            writer.value(correlationItem.getId());
            writer.value(correlationItem.getName());
            writer.value(correlationItem.getValue());
            writer.endArray();
        }
    }

    private void writerItemHead(JsonWriter writer) throws IOException{
        writer.beginArray();
        writer.value("Id");
        writer.value("Name");
        writer.value("Value");
        writer.endArray();
    }

    public CorrelationResultService getCorrelationResultService() {
        return correlationResultService;
    }

    public void setCorrelationResultService(
            CorrelationResultService correlationResultService) {
        this.correlationResultService = correlationResultService;
    }
}
