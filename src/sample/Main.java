package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private static XYChart.Series<String, Number> mergeGraph;
    private static XYChart.Series<String, Number> quickGraph;
    private static XYChart.Series<String, Number> heapGraph;
    private static XYChart.Series<String, Number> selectionGraph;
    private static XYChart.Series<String, Number> bubbleGraph;
    private static XYChart.Series<String, Number> insertionGraph;

    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox hBox = new HBox();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Size of Array");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Time in Milliseconds");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Time Performance of Sorting Algorithm");
        lineChart.setMinWidth(1700);
        lineChart.setStyle("-fx-font-size: " + 15 + "px;");

        lineChart.getData().add(mergeGraph);
        lineChart.getData().add(quickGraph);
        lineChart.getData().add(heapGraph);
        lineChart.getData().add(selectionGraph);
        lineChart.getData().add(bubbleGraph);
        lineChart.getData().add(insertionGraph);

        hBox.getChildren().add(lineChart);
        primaryStage.setTitle("Sorting");
        primaryStage.setScene(new Scene(hBox, 1700, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {

        mergeGraph = new XYChart.Series<>();
        mergeGraph.setName("Merge Sort");
        quickGraph = new XYChart.Series<>();
        quickGraph.setName("Quick Sort");
        heapGraph = new XYChart.Series<>();
        heapGraph.setName("Heap Sort");
        selectionGraph = new XYChart.Series<>();
        selectionGraph.setName("Selection Sort");
        bubbleGraph = new XYChart.Series<>();
        bubbleGraph.setName("Bubble Sort");
        insertionGraph = new XYChart.Series<>();
        insertionGraph.setName("Insertion Sort");

        Random rd = new Random(); // creating Random object

            for (int k = 10; k <=21000000; ) {
                int[] arr = new int[k];

                if(k<=10)
                    System.out.println("Original Array:");

                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rd.nextInt(k); // storing random integers in an array
                    if(k<=10)
                        System.out.println(arr[i]); // printing each array element
                }
                System.out.println("");

                int[] arrayUnsorted;

                for(int j=0;j<6;j++) {
                    arrayUnsorted=arr.clone();

                    if(k<=10) {
                        System.out.println("Unsorted Array:");
                        for (int i = 0; i < arrayUnsorted.length; i++) {
                            System.out.println(arrayUnsorted[i]); // printing each array element
                        }
                    }
                    System.out.println("");

                    long endTime;
                    long timeElapsed = 0;
                    String sortName="";

                    long startTime = System.currentTimeMillis();

                    if (j == 0) {
                        mergeSort(arrayUnsorted, arrayUnsorted.length);
                        endTime = System.currentTimeMillis();
                        timeElapsed = endTime - startTime;
                        mergeGraph.getData().add(new XYChart.Data<String, Number>(String.valueOf(k), timeElapsed));
                        sortName="Merge Sort";
                    } else if (j == 1) {
                        heapSort(arrayUnsorted);
                        endTime = System.currentTimeMillis();
                        timeElapsed = endTime - startTime;
                        heapGraph.getData().add(new XYChart.Data<String, Number>(String.valueOf(k), timeElapsed));
                        sortName="Heap Sort";
                    } else if (j == 2) {
                        quickSort(arrayUnsorted, 0, arrayUnsorted.length - 1);
                        endTime = System.currentTimeMillis();
                        timeElapsed = endTime - startTime;
                        quickGraph.getData().add(new XYChart.Data<String, Number>(String.valueOf(k), timeElapsed));
                        sortName="Quick Sort";
                    } else if (j == 3 && k<=200000) {
                        selectionSort(arrayUnsorted);
                        endTime = System.currentTimeMillis();
                        timeElapsed = endTime - startTime;
                        selectionGraph.getData().add(new XYChart.Data<String, Number>(String.valueOf(k), timeElapsed));
                        sortName="Selection Sort";
                    } else if (j == 4 && k<=100000) {
                        bubbleSort(arrayUnsorted, arrayUnsorted.length);
                        endTime = System.currentTimeMillis();
                        timeElapsed = endTime - startTime;
                        bubbleGraph.getData().add(new XYChart.Data<String, Number>(String.valueOf(k), timeElapsed));
                        sortName="Bubble Sort";
                    } else if (j == 5 && k<=300000) {
                        insertionSort(arrayUnsorted);
                        endTime = System.currentTimeMillis();
                        timeElapsed = endTime - startTime;
                        insertionGraph.getData().add(new XYChart.Data<String, Number>(String.valueOf(k), timeElapsed));
                        sortName="Insertion Sort";
                    }

                    if(k<=10) {
                        System.out.println("Sorted Array using " + sortName + ": ");
                        for (int i = 0; i < arrayUnsorted.length; i++) {
                            System.out.println(arrayUnsorted[i]); // printing each array element
                        }
                    }
                    System.out.println("Execution time of " + k + " elements using " + sortName + " in milliseconds: " + timeElapsed);
                    System.out.println("");
                }

                if (k >= 100000 && k < 1000000)
                    k += 100000;
                else if (k >= 1000000)
                    k += 2000000;
                else
                    k *= 10;
            }
        launch(args);


    }

    public static void selectionSort(int arr[])
    {
        int n = arr.length;

        for (int i = 0; i < n-1; i++)
        {

            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void bubbleSort(int arr[], int n)
    {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (arr[j] > arr[j + 1])
                {

                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false)
                break;
        }
    }

    public static void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void heapSort(int arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void heapify(int arr[], int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            if (arr[j] < pivot)
            {

                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
