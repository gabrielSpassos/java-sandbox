package com.gabrielspassos.challenge16;

import java.util.List;

public interface Character {

    Pair<List<Character>, Character[][]> move(Character[][] board);

}
