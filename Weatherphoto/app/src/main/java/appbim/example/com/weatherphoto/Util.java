package appbim.example.com.weatherphoto;

/**
 * Created by Administrator on 2018/5/22.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Util {

    public List<Map<String, Object>> getInformation(String jonString)
            throws Exception {
        List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
            JSONObject jsonObject = new JSONObject(jonString);
            JSONObject retData = jsonObject.getJSONObject("retData");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cityName", retData.optString("city"));
            map.put("weather", retData.optString("weather"));
        map.put("temp",retData.optString("temp"));
        map.put("l_temp", retData.optString("temp1"));
        map.put("h_temp", retData.optString("temp2"));
        all.add(map);
        return all;

    }

}
