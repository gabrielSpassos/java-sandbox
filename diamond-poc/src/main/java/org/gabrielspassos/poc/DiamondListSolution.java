package org.gabrielspassos.poc;

import org.gabrielspassos.poc.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiamondListSolution {

    private static final String TAB = "\t";
    private static final String NEW_LINE = "\n";

    private final StringUtil stringUtil;

    public DiamondListSolution() {
        this.stringUtil = new StringUtil();
    }

    public String createDiamond(String inputLetter) {
        var splitAlphabet = createSplittedAlphabet(inputLetter);
        var tabStartDistance = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = splitAlphabet.size(); i-- > 0; ) {
            var tabDistance = i * 2;
            var letter = splitAlphabet.get(i);

            if (splitAlphabet.size() == (i + 1)) {
                var tabSeparation = stringUtil.repeat(TAB, tabDistance);
                var diamondLine = letter + tabSeparation + letter + NEW_LINE;
                stringBuilder.append(diamondLine);
            } else if (0 == i) {
                var tabStartSeparation = stringUtil.repeat(TAB, tabStartDistance);
                var diamondLine = tabStartSeparation + letter + tabStartSeparation + NEW_LINE;
                stringBuilder.insert(0, diamondLine);
                stringBuilder.append(diamondLine);
            } else {
                var tabStartSeparation = stringUtil.repeat(TAB, tabStartDistance);
                var tabMiddleSeparation = stringUtil.repeat(TAB, tabDistance);
                var diamondLine =  tabStartSeparation + letter + tabMiddleSeparation + letter + tabStartSeparation + NEW_LINE;
                stringBuilder.insert(0, diamondLine);
                stringBuilder.append(diamondLine);
            }

            tabStartDistance++;
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    private List<String> createSplittedAlphabet(String inputLetter) {
        var formattedInput = inputLetter.toUpperCase();
        var alphabetList = createAlphabetList();

        if (!alphabetList.contains(formattedInput)) {
            throw new IllegalArgumentException("Invalid input " + inputLetter);
        }

        var splitAlphabet = new ArrayList<String>();

        for (String letter : alphabetList) {
            splitAlphabet.add(letter);

            if (letter.equals(formattedInput)) {
                break;
            }
        }

        return splitAlphabet;
    }

    private HashSet<String> createAlphabetList() {
        return Stream.of("A", "B", "C", "D", "E", "F", "G", "H",
                        "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                        "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
                .collect(Collectors.toCollection(HashSet::new));
    }

}
