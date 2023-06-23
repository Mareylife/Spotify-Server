package Models;

import org.example.Models.RequestType;

import java.util.HashMap;

public class Request {
    private RequestType action;
    private HashMap<String, Object> params = new HashMap<>();

    public RequestType getAction() {
        return action;
    }

    public void setAction(RequestType action) {
        this.action = action;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public void addData(String key, Object value) {
        this.params.put(key, value);
    }
}