public class Workshop11{
    public static void main(String[] args) {
        int k = 1521;
        // tell if k is dividable by 3 or 5
        boolean myBoolean = (k % 3 == 0) || (k % 5 == 0);
        System.out.println(myBoolean);
    }
}