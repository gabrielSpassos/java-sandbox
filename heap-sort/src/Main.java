import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Realizando os testes com heap sort");
        HeapSort heapSort = new HeapSort();
        System.out.println("Pior Caso");
        LocalDateTime start = TimeUtils.now();
        int[] sortedArray = heapSort.sort(getDescArray());
        LocalDateTime finish = TimeUtils.now();
        System.out.println("Inicio: " + start);
        System.out.println("Fim: " + finish);
        System.out.println("Tempo demorado em ms: " + TimeUtils.getDifferenceBetweenDates(start, finish));
        System.out.println("Array ordenado " + Arrays.toString(sortedArray));
        System.out.println("*******************************************\n\n");
        System.out.println("Melhor Caso");
        start = TimeUtils.now();
        sortedArray = heapSort.sort(getAscArray());
        finish = TimeUtils.now();
        System.out.println("Inicio: " + start);
        System.out.println("Fim: " + finish);
        System.out.println("Tempo demorado em ms: " + TimeUtils.getDifferenceBetweenDates(start, finish));
        System.out.println("Array ordenado " + Arrays.toString(sortedArray));
    }

    private static int[] getAscArray() {
        int[] array = new int[20000];
        for (int i = 0; i < 20000; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] getDescArray() {
        int[] invertedArray = new int[20000];
        int value = 20000;
        for (int i = 0; i < 20000; i++) {
            invertedArray[i] = value;
            value --;
        }
        return invertedArray;
    }
}
