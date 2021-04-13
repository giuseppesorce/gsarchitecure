package com.giuseppesorce.network.converters;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Giuseppe Sorce
 */

public class StringResponseBodyConverter implements Converter<ResponseBody, JSONObject> {
    @Override
    public JSONObject convert(ResponseBody value) throws IOException {
        JSONObject json= null;

        try {
            json = new JSONObject(value.string());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return json;
        } finally {
            value.close();
        }
    }
}
