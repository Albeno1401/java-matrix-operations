import java.util.Scanner;

/**
 * Basic matrix operations with a simple menu.
 * Compile: javac BasicMatrix.java
 * Run:     java BasicMatrix
 */
public class BasicMatrix {

    static Scanner sc = new Scanner(System.in);

    // Read a matrix from the user
    static int[][] readMatrix(String name) {
        System.out.print("Enter rows of " + name + ": ");
        int r = sc.nextInt();
        System.out.print("Enter columns of " + name + ": ");
        int c = sc.nextInt();

        int[][] m = new int[r][c];
        System.out.println("Enter elements of " + name + ":");
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                m[i][j] = sc.nextInt();
        return m;
    }

    // Print a matrix
    static void print(int[][] m) {
        for (int[] row : m) {
            for (int v : row) System.out.print(v + "\t");
            System.out.println();
        }
    }

    // Addition
    static int[][] add(int[][] a, int[][] b) {
        int[][] res = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                res[i][j] = a[i][j] + b[i][j];
        return res;
    }

    // Subtraction
    static int[][] subtract(int[][] a, int[][] b) {
        int[][] res = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                res[i][j] = a[i][j] - b[i][j];
        return res;
    }

    // Multiplication
    static int[][] multiply(int[][] a, int[][] b) {
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < b.length; k++)
                    res[i][j] += a[i][k] * b[k][j];
        return res;
    }

    // Transpose
    static int[][] transpose(int[][] a) {
        int[][] res = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                res[j][i] = a[i][j];
        return res;
    }

    // Determinant (recursive, works for any square matrix)
    static int determinant(int[][] m) {
        int n = m.length;
        if (n == 1) return m[0][0];
        if (n == 2) return m[0][0] * m[1][1] - m[0][1] * m[1][0];

        int det = 0;
        for (int col = 0; col < n; col++) {
            int[][] sub = new int[n - 1][n - 1];
            for (int i = 1; i < n; i++) {
                int c = 0;
                for (int j = 0; j < n; j++) {
                    if (j == col) continue;
                    sub[i - 1][c++] = m[i][j];
                }
            }
            det += (col % 2 == 0 ? 1 : -1) * m[0][col] * determinant(sub);
        }
        return det;
    }

    // Trace (sum of diagonal)
    static int trace(int[][] m) {
        int t = 0;
        for (int i = 0; i < m.length; i++) t += m[i][i];
        return t;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MATRIX MENU =====");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Transpose");
            System.out.println("5. Determinant");
            System.out.println("6. Trace");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("Bye!");
                break;
            }

            switch (choice) {
                case 1: {
                    int[][] a = readMatrix("A");
                    int[][] b = readMatrix("B");
                    if (a.length != b.length || a[0].length != b[0].length)
                        System.out.println("Sizes must be the same!");
                    else {
                        System.out.println("A + B =");
                        print(add(a, b));
                    }
                    break;
                }
                case 2: {
                    int[][] a = readMatrix("A");
                    int[][] b = readMatrix("B");
                    if (a.length != b.length || a[0].length != b[0].length)
                        System.out.println("Sizes must be the same!");
                    else {
                        System.out.println("A - B =");
                        print(subtract(a, b));
                    }
                    break;
                }
                case 3: {
                    int[][] a = readMatrix("A");
                    int[][] b = readMatrix("B");
                    if (a[0].length != b.length)
                        System.out.println("Columns of A must equal rows of B!");
                    else {
                        System.out.println("A x B =");
                        print(multiply(a, b));
                    }
                    break;
                }
                case 4: {
                    int[][] a = readMatrix("A");
                    System.out.println("Transpose =");
                    print(transpose(a));
                    break;
                }
                case 5: {
                    int[][] a = readMatrix("A");
                    if (a.length != a[0].length)
                        System.out.println("Matrix must be square!");
                    else
                        System.out.println("Determinant = " + determinant(a));
                    break;
                }
                case 6: {
                    int[][] a = readMatrix("A");
                    if (a.length != a[0].length)
                        System.out.println("Matrix must be square!");
                    else
                        System.out.println("Trace = " + trace(a));
                    break;
                }
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
