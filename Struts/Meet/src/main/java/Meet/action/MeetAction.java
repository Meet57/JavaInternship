package Meet.action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private List<String> dataList = null;
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Map<String,String> meet=new HashMap<String, String>();

    public Map<String, String> getMeet() {
        return meet;
    }

    public void setMeet(Map<String, String> meet) {
        this.meet = meet;
    }

    @Override
    public String execute() throws Exception {
        dataList = new ArrayList<String>();
        dataList.add("India");
        dataList.add("USA");
        dataList.add("Russia");
        dataList.add("China");
        dataList.add("Japan");

        meet.put("1","one");
        meet.put("2","two");

        return SUCCESS;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
