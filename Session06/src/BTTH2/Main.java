package BTTH2;

public class Main {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();

        // Tạo Thread lẻ
        Thread oddThread = new Thread(() -> printer.printOdd());

        // Tạo Thread chẵn
        Thread evenThread = new Thread(() -> printer.printEven());

        oddThread.start();
        evenThread.start();
    }
}
