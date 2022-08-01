package models;

import java.util.HashMap;

/** Словари */
public class Dictionaries {

    private static final HashMap<Integer, String> priorities = new HashMap<>();
    private static final HashMap<Integer, String> queues = new HashMap<>();

    static {
        priorities.put(1, "1. Critical");
        priorities.put(2, "2. High");
        priorities.put(3, "3. Normal");
        priorities.put(4, "4. Low");
        priorities.put(5, "5. Very Low");

        queues.put(1, "Django Helpdesk");
        queues.put(2, "Some Product");
    }

    public static String getPriority(int priority) {
        return priorities.get(priority);
    }

    public static String getQueue(int queue) {
        return queues.get(queue);
    }
}
