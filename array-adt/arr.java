import java.util.Arrays;

public class arr {

    static boolean isPrima(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static double avg(int n[]) {
        int sum = 0;
        for (int ar : n) {
            sum += ar;
        }
        return (double) sum / n.length;
    }

    static void to2D(int n[]) {
        int arrs[][] = new int[3][5];
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                arrs[i][j] = n[count];
                System.out.print(arrs[i][j] + "\t");
                count++;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int arr[] = { 30, 87, 90, 3, 1, 50, 23, 4, 25, 23, 40, 35, 47, 2, 33 };

        Arrays.sort(arr);
        System.out.println("Array urut:");
        for (int ar : arr) {
            System.out.print(ar + " ");
        }

        System.out.printf("\n\nRata-rata array: %.2f", avg(arr));

        int max = arr[0];
        int min = arr[0];

        for (int ar : arr) {
            if (ar > max) {
                max = ar;
            }

            if (ar < min) {
                min = ar;
            }
        }

        System.out.println("\n\nData maksimal: " + max);
        System.out.println("Data minimal: " + min);

        System.out.println("\nBilangan ganjil: ");
        for (int ar : arr) {
            if (ar % 2 != 0) {
                System.out.print(ar + " ");
            }
        }

        System.out.println("\n\nBilangan prima: ");
        for (int ar : arr) {
            if (isPrima(ar)) {
                System.out.print(ar + " ");
            }
        }

        System.out.println("\n\nArray 2 dimensi 3 x 5: ");
        to2D(arr);

    }
}
