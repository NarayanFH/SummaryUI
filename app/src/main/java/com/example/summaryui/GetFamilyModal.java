package com.example.summaryui;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GetFamilyModal {

    @SerializedName("data")
    public List<dataListClass> dataList;

 private int parent_user_id;
 private int fp_log_id;

    public int getParent_user_id() {
        return parent_user_id;
    }

    public void setParent_user_id(int parent_user_id) {
        this.parent_user_id = parent_user_id;
    }

    public int getFp_log_id() {
        return fp_log_id;
    }

    public void setFp_log_id(int fp_log_id) {
        this.fp_log_id = fp_log_id;
    }

    public class dataListClass {

        @SerializedName("first_name")
        public String first_name;

        @SerializedName("relationname")
        public String relationname;


    }



}
