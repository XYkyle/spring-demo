package com.gxl.springdemo.pojo;

import lombok.Data;

@Data
public class WhiteDo {
    private String raw_query;
    private String lib_id;
    private String category;
    private String business_id;
    private String intent_name;
    private String segmented_query;

    @Override
    public String toString() {
        return "WhiteDo{" +
                "raw_query='" + raw_query + '\'' +
                ", lib_id='" + lib_id + '\'' +
                ", category='" + category + '\'' +
                ", business_id='" + business_id + '\'' +
                ", intent_name='" + intent_name + '\'' +
                ", segmented_query='" + segmented_query + '\'' +
                '}';
    }


}
