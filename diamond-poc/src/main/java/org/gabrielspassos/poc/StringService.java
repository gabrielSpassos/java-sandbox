package org.gabrielspassos.poc;

public class StringService {

    public String includeBeforeAndAfter(String stringToUpdate, String stringToIncludeBefore, String stringToIncludeAfter) {
        stringToUpdate = includeBefore(stringToUpdate, stringToIncludeBefore);
        stringToUpdate = includeAfter(stringToUpdate, stringToIncludeAfter);
        return stringToUpdate;
    }

    public String includeBefore(String stringToUpdate, String stringToInclude) {
        return stringToInclude + stringToUpdate;
    }

    public String includeAfter(String stringToUpdate, String stringToInclude) {
        return stringToUpdate + stringToInclude;
    }
}
