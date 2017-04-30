package puzzleSolver;

public class Utils {

    public static String toString(int[][] array) {
        if (!Utils.isNullOrEmpty(array)) {
            StringBuilder builder = new StringBuilder();
            String newLine = System.lineSeparator();

            for (int n = 0; n < array.length; n++) {

                for (int j = 0; j < array[n].length; j++) {
                    builder.append(array[n][j] + " ");
                }
                builder.append(newLine);
            }
            return builder.toString();
        } else {
            return "";
        }
    }

    public static boolean isNullOrEmpty(int[][] array) {
        if (array != null) {
            if (array.length > 0)
                return false;
        }
        return true;
    }

}
