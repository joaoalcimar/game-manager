package com.inatel.gamemanager;

public class TestUtils {

    public static String returnValidRequestBody(){
        return "{\n" +
                " \"publisherId\": \"nintendo\",\n" +
                " \"name\": \"Mario\",\n" +
                " \"timePlayed\": {\n" +
                " \"2023-05-01\": 10,\n" +
                " \"2023-05-02\": 2,\n" +
                " \"2023-05-03\": 3,\n" +
                " \"2023-05-04\": 4\n" +
                " }\n" +
                "}";
    }

    public static String returnValidResponseBody(){
        return "[{\"id\":\"00000000-0000-0001-0000-000000000002\",\"publisherId\":\"capcom\",\"name\":\"megaman\",\"timePlayed\":\"\\\"2023-05-01\\\": 10,\\n \\\"2023-05-02\\\": 2,\\n \\\"2023-05-03\\\": 3,\\n \\\"2023-05-04\\\": 4 \\n\"}]";
    }

    public static String returnValidTimePlayedString(){
        return "\"2023-05-01\": 10,\n" +
                " \"2023-05-02\": 2,\n" +
                " \"2023-05-03\": 3,\n" +
                " \"2023-05-04\": 4 \n";
    }
}
