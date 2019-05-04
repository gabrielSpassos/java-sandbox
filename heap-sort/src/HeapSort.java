public class HeapSort {

    public int[] sort(int[] array) {
        for (int i = getSortStartIndex(array); i >= 0 ; i--) {
            buildHeapTree(array, i, array.length);
        }
        for (int i = array.length-1; i >= 1 ; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            buildHeapTree(array, 0, i);
        }
        return array;
    }

    private int getSortStartIndex(int[] array) {
        return (array.length / 2) -1;
    }

    private void buildHeapTree(int[] array, int index, int arrayLength) {
        int largest = index;
        int leftLeaf = getLeftLeaf(index);
        int rightLeaf = getRightLeaf(index);

        if(isLeftLeafBiggerThanLargest(leftLeaf, largest, arrayLength, array)) {
            largest = leftLeaf;
        }

        if(isRightLeafBiggerThanLargest(rightLeaf, largest, arrayLength, array)) {
            largest = rightLeaf;
        }

        if(largest != index) {
            int temp = array[index];
            array[index] = array[largest];
            array[largest] = temp;
            buildHeapTree(array, largest, arrayLength);
        }
    }

    private int getLeftLeaf(int index) {
        return 2 * index + 1;
    }

    private int getRightLeaf(int index) {
        return 2 * index + 2;
    }

    private Boolean isLeftLeafBiggerThanLargest(int leftLeaf, int largest, int arrayLength, int[] array) {
        return leftLeaf < arrayLength && array[leftLeaf] > array[largest];
    }

    private Boolean isRightLeafBiggerThanLargest(int rightLeaf, int largest, int arrayLength, int[] array) {
        return rightLeaf < arrayLength && array[rightLeaf] > array[largest];
    }
}
