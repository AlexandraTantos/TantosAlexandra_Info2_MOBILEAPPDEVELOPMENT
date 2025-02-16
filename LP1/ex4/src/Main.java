import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Given path: ");
        String path = scanner.nextLine().toUpperCase();
        int result = ValleyCounter.countValleys(path);
        System.out.println(result);
        scanner.close();
    }
}