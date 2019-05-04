import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = { 12, 11, 13, 5, 6, 7 };
        System.out.println("Realizando os testes com heap sort");
        int[] sortedArray = heapSort.sort(array);
        System.out.println("Array ordenado " + Arrays.toString(sortedArray));
    }
}
