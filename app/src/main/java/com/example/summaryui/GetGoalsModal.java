package com.example.summaryui;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetGoalsModal {

        @SerializedName("data")
        public List<GetGoalsModal.dataGoalClass> dataGoal;

        private int user_id;
        private int fp_log_id;

        public int get_user_id() {
            return user_id;
        }

        public void set_user_id(int ser_id) {
            this.user_id = user_id;
        }

        public int getFp_log_id() {
            return fp_log_id;
        }

        public void setFp_log_id(int fp_log_id) {
            this.fp_log_id = fp_log_id;
        }

        public class dataGoalClass {

            @SerializedName("goal_name")
            public String goal_name;


        }



}
