package sonnguyen.findpathwithlowercost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_row)
    EditText editRow;
    @BindView(R.id.edit_column)
    EditText editColumn;
    @BindView(R.id.ln_group_input)
    LinearLayout lnGroupInput;
    @BindView(R.id.tv_havePath)
    TextView tvHavePath;
    @BindView(R.id.tv_cost)
    TextView tvCost;
    @BindView(R.id.tv_path)
    TextView tvPath;
    @BindView(R.id.tableMatrix)
    TableLayout tableMatrix;


    int[][] matrixToFindPath;

//    ArrayList<Integer> arrayListPath = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> arrayListPath = new ArrayList<ArrayList<Integer>>();

    ArrayList<String> arrayListPathString = new ArrayList<String>();

    int row,column;

    String path ="";


    private final int [][] MATRIX_EXAMPLE1 =
            {{3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 8, 6, 4}};
    private final int [][] MATRIX_EXAMPLE2 =
                    {{3, 4, 1, 2, 8, 6},
                    {6, 1, 8, 2, 7, 4},
                    {5, 9, 3, 9, 9, 5},
                    {8, 4, 1, 3, 2, 6},
                    {3, 7, 2, 1, 2, 3}};
    private final int [][] MATRIX_EXAMPLE3 =
                {{19, 10, 19, 10, 19},
                 {21, 23, 20, 19, 12},
                 {20, 12, 20, 11, 10}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    private void fillTable(final int r,final int c, final int [][] matrix, TableLayout table) {
        setResultTheResult(0,false,"");
        table.removeAllViews();
        for (int i = 0; i < r; i++) {
            TableRow row = new TableRow(MainActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < c; j++) {
                EditText edit = new EditText(MainActivity.this);
                edit.setTag(i+"x"+j);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                if(matrix!=null){
                    edit.setText(Integer.toString(matrix[i][j]));
                }else {
                    edit.setHint("0");
                }
                row.addView(edit);
            }
            table.addView(row);
        }
    }

    private void setResultTheResult(int cost, boolean havepath, String path){
        tvCost.setText(String.format(getString(R.string.cost),cost> 0? cost+"":""));
        tvHavePath.setText(String.format(getString(R.string.have_path),havepath? "True":"False"));
        tvPath.setText(String.format(getString(R.string.path),path));
    }

    private void handleInputClick(){
        row = getNumberFromEditText(editRow);
        column = getNumberFromEditText(editColumn);
        if((row >=1 && column>=5) && (row<=10 && column<=100)){
            fillTable(row,column,null,tableMatrix);
        }else{
            Toast.makeText(this,"Please check you input minimum of 1 row and 5 columns up to 10 rows and 100 columns.",Toast.LENGTH_SHORT).show();
        }
    }

    private int getNumberFromEditText( EditText editText){
        if(editText!=null && editText.getText().toString().length()>0){
            return Integer.parseInt( editText.getText().toString() );
        }
        return 0;

    }

    public int minCost(int cost[][], int m, int n)
    {
        int r = cost.length;
        int c =  cost[1].length;
        if (n < 0 || m < 0)
            return Integer.MAX_VALUE;
        else if ((m == r-1 && n == c-1)|| n+1>=c){
            return cost[m][n];
        }
        else{
            int pe_result = cost[m][n];
            Log.v("NCS","pe_result : " +pe_result);
            int min = min( minCost(cost, m+1>=r?r-1:m+1,n+1),
                    minCost(cost, m,n+1),
                    minCost(cost, m-1>=0?m-1:r-1,n+1));

            int result = pe_result+min;
            return result;
        }

    }


      public int minCostSolutionWithTempMatrix(int [][]cost,int m,int n){
         int temp[][] = new int[m+1][n+1];
         int sum = 0;
         for(int i=0; i <= n; i++){
             temp[0][i] = sum + cost[0][i];
             sum = temp[0][i];
         }
         sum = 0;
         for(int i=0; i <= m; i++){
             temp[i][0] = sum + cost[i][0];
             sum = temp[i][0];
         }

         for(int i=1; i <= m; i++){
             for(int j=1; j <= n; j++){
                 temp[i][j] = cost[i][j] + min(temp[i-1][j-1],temp[i-1][j],temp[i][j-1]);
             }
         }
         return temp[m][n];
     }





    public static void printArray(int matrix[][]) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }


    private int min(int a,int b, int c){
        int l = Math.min(a, b);
        return Math.min(l, c);
    }


    private void getValueInMatrix(){
        matrixToFindPath = new int[row][column];
        for (int i = 0; i < row; i++) {
            TableRow RowView  = (TableRow) tableMatrix.getChildAt(i);
            for (int j = 0; j < column; j++) {
                EditText editText = (EditText) RowView.getChildAt(j);
                int value = getNumberFromEditText(editText);
                matrixToFindPath[i][j] = value;

            }
        }
    }



    private void findTheLowestPath(){
        getValueInMatrix();
       if(matrixToFindPath.length <= 0){
           Toast.makeText(this,"Please check you input MaTrix.",Toast.LENGTH_SHORT).show();
       }else{
           int cost  = minCost(matrixToFindPath,0,0);
            setResultTheResult(cost,cost<58,"");
       }
    }


    // Print all path can come from the top let to bottom right
    public void getAllPath(int currentRow, int currentColumn, String path) {
        if (currentRow == row - 1) {
            for (int i = currentColumn; i < column; i++) {
                if(path.isEmpty()){
                    path += ""+matrixToFindPath[currentRow][i];
                }else{
                    path += "-" + matrixToFindPath[currentRow][i];
                }

            }
            arrayListPathString.add(path);
            return;
        }
        if (currentColumn == column - 1) {
            for (int i = currentRow; i <= row - 1; i++) {
                if(path.isEmpty()){
                    path += ""+matrixToFindPath[i][currentColumn];
                }else{
                    path += "-" + matrixToFindPath[i][currentColumn];
                }
            }
            arrayListPathString.add(path);
            return;
        }

        if(path.isEmpty()){
            path += ""+matrixToFindPath[currentRow][currentColumn];
        }else{
            path += "-" + matrixToFindPath[currentRow][currentColumn];
        }
        getAllPath(currentRow + 1, currentColumn, path);
        getAllPath(currentRow, currentColumn + 1, path);
        getAllPath(currentRow + 1, currentColumn + 1, path);

    }




    public void printAllPath(){
        for (String path: arrayListPathString){
            Log.v("NCS","Path: " + path);
        }
    }

    public int getWeightPath(String result[]){
            int sum =0;
        int sizec = result.length;
        for (int j=0;j<sizec;j++){
            int value = Integer.valueOf(result[j]);
            sum = sum +value;
        }
            return sum;

        }


    public void getPathLowestPath(){
        if(matrixToFindPath.length <= 0){
            Toast.makeText(this,"Please check you input MaTrix.",Toast.LENGTH_SHORT).show();
        }else {
            getAllPath(0, 0, "");
            int size = arrayListPathString.size();
            int currentMin = Integer.MAX_VALUE;
            int currentMinIndex = 0;
            for (int i = 0; i < size; i++) {
                String path = arrayListPathString.get(i);
                if (!path.isEmpty()) {
                    String[] c = path.split("-");
                    int sum = getWeightPath(c);
                    if (currentMin > sum) {
                        Log.v("NCS", "Current Min" + currentMin + "currentMinIndex :" + currentMinIndex);
                        currentMin = sum;
                        currentMinIndex = i;
                    }
                }
            }
            printAllPath();
            if (currentMinIndex < size) {
                path = arrayListPathString.get(currentMinIndex);
                tvPath.setText(path);
            }
        }
    }




    @OnClick({R.id.btn_example1, R.id.btn_example2, R.id.btn_example3, R.id.btn_input, R.id.btn_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_example1:
                row = 5;
                column = 6;
                fillTable(row,column,MATRIX_EXAMPLE1,tableMatrix);
                break;
            case R.id.btn_example2:
                row = 5;
                column = 6;
                fillTable(row,column,MATRIX_EXAMPLE2,tableMatrix);
                break;
            case R.id.btn_example3:
                row = 3;
                column = 5;
                fillTable(row,column,MATRIX_EXAMPLE3,tableMatrix);
                break;
            case R.id.btn_input:
                handleInputClick();
                break;
            case R.id.btn_result:
                findTheLowestPath();
                getPathLowestPath();


                break;
        }
    }


}
