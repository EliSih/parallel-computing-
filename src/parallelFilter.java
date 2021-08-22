import java.util.Scanner;

public class parallelFilter {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Unfiltered Data");
        String data = keyboard.nextLine().replace(",","");
        data = data.substring(2);
        System.out.println("Enter filter size");
        int fSize = keyboard.nextInt();
        int dataSize = data.length();
    }
}
