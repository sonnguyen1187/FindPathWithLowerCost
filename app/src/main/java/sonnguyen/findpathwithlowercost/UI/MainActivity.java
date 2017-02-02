package sonnguyen.findpathwithlowercost.UI;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sonnguyen.findpathwithlowercost.CommonUtils.DataConstants;
import sonnguyen.findpathwithlowercost.CommonUtils.Utils;
import sonnguyen.findpathwithlowercost.Manager.MatrixVisitor;
import sonnguyen.findpathwithlowercost.Models.Matrix;
import sonnguyen.findpathwithlowercost.Models.Path;
import sonnguyen.findpathwithlowercost.R;

/**
 *
 * This MainActivity class contains methods for processing the UI, display the grid input data and show the output values
 *
 */

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
    Matrix matrix;
    public int row,column;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * Method to create the Matrix based on number of rows and columns
     * @param r
     * @param c
     * @param matrix
     * @param table
     *
     */
    private void fillTable(final int r, final int c, final Matrix matrix, TableLayout table) {
        cleanResultTheResult();
        table.removeAllViews();
        for (int i = 1; i <= r; i++) {
            TableRow row = new TableRow(MainActivity.this);
            row.setTag(i);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            for (int j = 1; j <= c; j++) {
                EditText edit = new EditText(MainActivity.this);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                // matrix == null means to create the matrix with row and column without data, user inputs the values by themselves
                if(matrix!=null){
                    edit.setText(Integer.toString(matrix.getValueAtRowAndColumn(i,j)));
                }else {
                    edit.setHint("0");
                }
                row.addView(edit);
            }
            table.addView(row);
        }
        table.invalidate();
    }

    /**
     * Method to clean all result data
     */
    private void cleanResultTheResult(){
        tvCost.setText("");
        tvHavePath.setText("");
        tvPath.setText("");
    }

    /**
     * Method call when INPUT button clicked, it will check if invalid number of row and column then it will create the matrix
     */
    private void handleInputClick(){
        if(invalidateInput()){
            row = Utils.getIntegerNumberFromEditText(editRow);
            column = Utils.getIntegerNumberFromEditText(editColumn);
            fillTable(row,column,null,tableMatrix);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle(R.string.invalid_content)
                    .setMessage(R.string.error_message_invalid_matrix)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        }
    }

    /**
     * This method checks if 1 <= numberOfRow <= 10 and 5 <= numberOfColumn <= 100
     * @return
     */
    public boolean invalidateInput(){
        int numberRow = Utils.getIntegerNumberFromEditText(editRow);
        int numberColumn = Utils.getIntegerNumberFromEditText(editColumn);
      return  (numberRow >=1 && numberColumn>= 5) && (numberRow <= 10 && numberColumn <= 100);
    }

    /**
     *  Function to find the path with lowest cost
     */
    public void findPath(){
        getValueInMatrix();
        MatrixVisitor visitor = new MatrixVisitor(matrix);
        Path bestPath = visitor.getLowestCostPathForGrid();
        if (bestPath.isSuccessful()) {
            tvHavePath.setText(R.string.yes);
        } else {
            tvHavePath.setText(R.string.no);
        }
        tvCost.setText(Integer.toString(bestPath.getTotalCost()));
        tvPath.setText(Utils.formatPath(bestPath));
    }


    /**
     *  This method gets all input values in table and fill in the matrix
     */
    private void getValueInMatrix(){
        int input [][]= new int[row][column];
        for (int i = 0; i < row; i++) {
            TableRow RowView  = (TableRow) tableMatrix.getChildAt(i);
            if(RowView!=null) {
            for (int j = 0; j < column; j++) {
                    EditText editText = (EditText) RowView.getChildAt(j);
                    int value = Utils.getIntegerNumberFromEditText(editText);
                    input[i][j] = value;
                }
            }
        }
        matrix = new Matrix(input);
    }

    /**
     * This event handles methods for all button clicks namely EXAMPLE1, EXAMPLE2, EXAMPLE3 ...
     * @param view
     */

    @OnClick({R.id.btn_example1, R.id.btn_example2, R.id.btn_example3, R.id.btn_input, R.id.btn_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_example1:
                matrix = new Matrix(DataConstants.MATRIX_EXAMPLE1);
                row = matrix.getNoOfRows();
                column = matrix.getNoOfColumns();
                fillTable(row,column,matrix,tableMatrix);
                break;
            case R.id.btn_example2:
                matrix = new Matrix(DataConstants.MATRIX_EXAMPLE2);
                row = matrix.getNoOfRows();
                column = matrix.getNoOfColumns();
                fillTable(row,column,matrix,tableMatrix);
                break;
            case R.id.btn_example3:
                matrix = new Matrix(DataConstants.MATRIX_EXAMPLE3);
                row = matrix.getNoOfRows();
                column = matrix.getNoOfColumns();
                fillTable(row,column,matrix,tableMatrix);
                break;
            case R.id.btn_input:
                handleInputClick();
                break;
            case R.id.btn_result:
                findPath();
                break;
        }
    }


}
