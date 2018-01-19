import java.util.ArrayList;
import java.util.List;

public class OrdenaStrings {
    public static void main(String[] args) {

        List<String> palavras = new ArrayList<>();
        palavras.add("teste");
        palavras.add("estudo");
        palavras.add("java");

        palavras.sort((s1,s2) -> Integer.compare(s1.length(),s2.length()));

        System.out.println(palavras);

        palavras.forEach(s -> System.out.println(s));

    }
}

