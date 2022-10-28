package com.softlaboratory.hafyapi.constant;

public class AppConstant {

    private AppConstant() {
        //
    }

    public static final String APP_TIMEZONE = "GMT+7";
    public static final String KAFKA_TOPIC_NAME = "hafy-topic";
    public static final String KAFKA_GROUP_ID_ACC_TYPE = "group_acc_type";

    public enum Role {
        USER, ADMIN
    }

    public enum AccountType {
        GUEST, CUSTOMER, LAWYER, CONSULTANT, CREATOR
    }

}
