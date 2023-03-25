package com.gabrielspassos.poc.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

public class CollectionsUtils {

    public String joinStrings(List<String> strings) {
        return Joiner.on(";").join(strings);
    }

    public List<String> split() {
        String input = "apple - banana - orange";

        return Splitter.on("-").trimResults().splitToList(input);
    }

}
