package com.example.summaryui;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetModel {

    @SerializedName("data")
    public Data data;

    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public class Data {
        @SerializedName("networth")
        public int networth;

        @SerializedName("portfolio_value")
        public String portfolio_value;

        @SerializedName("liability_value")
        public String liability_value;

        @SerializedName("life_insurance")
        public String life_insurance;

        @SerializedName("name")
        public String goal_name;

        @SerializedName("goalsummary")
        public GoalSummary goalSummary;
    }

    public class GoalSummary {
        @SerializedName("total_count")
        public String totalGoalCount;
        @SerializedName("acheivable")
        public String acheivable;

        @SerializedName("all_goals")
        public ArrayList<AllGoals> allGoals;


    }

    public class AllGoals {
        @SerializedName("name")
        public String goalName;
        @SerializedName("present_value")
        public String presentGoalValue;
        @SerializedName("short_fall")
        public String shortFallValue;

    }
}


//        @SerializedName("total_count")
//        public String totalGoalCount;
//        @SerializedName("acheivable")
//        public String acheivableGoal;
//
//        @SerializedName("present_value")
//        public String currentGoalValue;
//
//        @SerializedName("short_fall")
//        public String shortFallValue;

