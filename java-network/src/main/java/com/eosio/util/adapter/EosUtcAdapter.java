package com.eosio.util.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jc on 5/24/18.
 */
public class EosUtcAdapter extends TypeAdapter<Date> {

    static DateFormat utcFormat;

    static {
        utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null)
            out.nullValue();
        else
            out.value(utcFormat.format(value));
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in != null) {
            try {
                return utcFormat.parse(in.nextString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
