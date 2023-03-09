package org.gabrielspassos.poc;

import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiamondStackService {

    private static final String TAB = "\t";
    private static final String NEW_LINE = "\n";

    private final StringService stringService;

    public DiamondStackService() {
        this.stringService = new StringService();
    }

    public String createDiamond(String inputLetter) {
        var splitAlphabet = createSplittedAlphabet(inputLetter);
        var tabStartDistance = 0;
        var stackMaxSize = splitAlphabet.size();
        var diamond = "";

        while (splitAlphabet.iterator().hasNext()) {
            var tabDistance = (splitAlphabet.size() - 1) * 2;
            var isFirstItem = isFirstItem(splitAlphabet, stackMaxSize);
            var isLastItem = isLastItem(splitAlphabet);

            var letter = splitAlphabet.pop();
            var tabStartSeparation = buildTheTabSeparation(tabStartDistance);
            var tabMiddleSeparation = buildTheTabSeparation(tabDistance);

            if (isFirstItem) {
                diamond = tabStartSeparation + letter + tabMiddleSeparation + letter + tabStartSeparation + NEW_LINE;
            } else {
                var optionalLetter = isLastItem ? "" : letter;

                var diamondLine = tabStartSeparation + letter + tabMiddleSeparation + optionalLetter + tabStartSeparation + NEW_LINE;
                diamond = stringService.includeBeforeAndAfter(diamond, diamondLine, diamondLine);
            }

            tabStartDistance++;
        }

        System.out.println(diamond);
        return diamond;
    }

    private Stack<String> createSplittedAlphabet(String inputLetter) {
        var formattedInput = inputLetter.toUpperCase();
        var alphabetList = createAlphabetList();

        if (!alphabetList.contains(formattedInput)) {
            throw new IllegalArgumentException("Invalid input " + inputLetter);
        }

        var splitAlphabet = new Stack<String>();

        for (String letter : alphabetList) {
            splitAlphabet.push(letter);

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

    private String buildTheTabSeparation(Integer distance) {
        return TAB.repeat(Math.max(0, distance));
    }

    private boolean isFirstItem(Stack<String> splitAlphabet, int stackMaxSize) {
        return splitAlphabet.size() == stackMaxSize;
    }

    private boolean isLastItem(Stack<String> splitAlphabet) {
        return splitAlphabet.size() == 1;
    }

}
