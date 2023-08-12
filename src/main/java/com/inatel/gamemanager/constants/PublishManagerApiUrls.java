package com.inatel.gamemanager.constants;

public final class PublishManagerApiUrls {

    private PublishManagerApiUrls() {
    }

    // Develop Environment

    public static final String BASE_URL = "http://localhost:";
    public static final String PORT = "8080";
    public static final String PUBLISHER_ENDPOINT = BASE_URL + PORT + "/publisher";
    public static final String NOTIFICATION_ENDPOINT = BASE_URL + PORT + "/notification";
    public static final String PUBLISHER_CACHE_ENDPOINT = BASE_URL + PORT + "/publishercache";
}
