package sonnguyen.findpathwithlowercost.CommonUtils;

import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class Utils {
    public static void printArray(int matrix[][]) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }

    public static int min(int a,int b, int c){
        int l = Math.min(a, b);
        return Math.min(l, c);
    }

    public static int getIntegerNumberFromEditText( EditText editText){
        int number =0;
        if(editText!=null && editText.getText().toString().length()>0){
            try {
              number=   Integer.parseInt( editText.getText().toString() );
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return number;

    }

    public static String formatPath(Path path) {
        StringBuilder builder = new StringBuilder();
        List<Integer> rows = path.getRowsTraversed();

        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }

        return builder.toString();
    }
}
