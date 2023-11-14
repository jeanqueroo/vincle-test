public class ShowNumber {

    public static void main(String[] args) {
        showNumber(100);
    }

    private static void showNumber(Integer num) {
        for (int i = 0; i <= num; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.print("VINCLE\n");
            } else if (i % 3 == 0) {
                System.out.print("VIN\n");
            } else if (i % 5 == 0) {
                System.out.print("CLE\n");
            } else {
                System.out.print(i + "\n");
            }

        }
    }
}

