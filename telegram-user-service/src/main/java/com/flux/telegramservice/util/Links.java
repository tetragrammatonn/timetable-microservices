package com.flux.telegramservice.util;

/**
 * Class with only util links.
 */
public class Links {

    private Links() {}

    // LOGISTIC-SERVICE API's
    public static final String LOGISTIC_SERVICE = "http://LOGISTIC-SERVICE/logistic-api";
    public static final String SAVE_USER = "/addUser";
    public static final String GET_USER_BY_CHAT_ID = "/getUser?chatId={chatId}";
    public static final String FIND_GROUP = "/findGroup?groupName={groupName}";
    public static final String LESSON_BY_GROUP = "/lessonByGroup?groupJson={groupJson}";
    public static final String SAVE_HISTORY = "/saveHistory";

    // Util
    public static final String NULL = "null";
}