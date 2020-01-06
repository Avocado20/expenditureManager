package service;

import java.util.ArrayList;
import java.util.List;

public class FeatureToggleService {

    public static boolean isEnabled(String toggle)
    {
        return enableToggles.contains(toggle);
    }

    private static List<String> enableToggles = new ArrayList<String>() {
        {
            add("WARNING01");
            add("CYCLICEXP");
        }
    };

}
