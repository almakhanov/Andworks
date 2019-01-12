package kz.batana.othellogame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class OthelloActivity extends AppCompatActivity {

    GridView gridView;
    OthelloAdapter adapter;
    boolean bl = true;
    Cell c;
    private ArrayList<Cell> cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othello);

        gridView = (GridView) findViewById(R.id.gridView);

        cells = getCells();

        adapter = new OthelloAdapter(this, cells);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Cell cell = cells.get(position);
                validRow(position);
                Log.d("accepted", cell.toString());
                Toast.makeText(OthelloActivity.this, cell.row + "x" + cell.column + " " + cell.isAvailable(), Toast.LENGTH_SHORT).show();
                adapter.draw(cells);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private ArrayList<Cell> getCells() {
        ArrayList<Cell> mCells = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            mCells.add(new Cell(i + 1, -1, false, i / 8 + 1, i % 8 + 1));
        }
        mCells.get(27).inside = 1;
        mCells.get(27).isAvailable = true;
        mCells.get(28).inside = 0;
        mCells.get(28).isAvailable = true;
        mCells.get(35).inside = 0;
        mCells.get(35).isAvailable = true;
        mCells.get(36).inside = 1;
        mCells.get(36).isAvailable = true;
        return mCells;
    }

    private void changeColor(int position) {
        Cell cell = cells.get(position);
        if (bl) {
            cell.inside = 0;
            bl = false;
        } else {
            cell.inside = 1;
            bl = true;
        }
    }

    private ArrayList<Cell> validRow(int pos) {

        if (!isValidPlace(pos)) return cells;

        Cell cell = cells.get(pos);
        int cnt = 0;
        int fist = 0;
        int fina = 0;
        for (int i = 0; i < 64; i++) {
            if (cell.getRow() == cells.get(i).getRow()) {
                fist = i;
                break;
            }
        }
        for (int i = 0; i < 64; i++) {
            if (cell.getRow() == cells.get(i).getRow()) {
                fina = i;
            }
        }

        if (bl) {
            cell.inside = 0;
            bl = false;
        } else {
            cell.inside = 1;
            bl = true;
        }

        for (int q = pos + 2; q <= fina; q++) {
            if ((cell.inside == 0 && cells.get(pos + 1).inside == 1) || (cell.inside == 1 && cells.get(pos + 1).inside == 0)) {
                while (cells.get(q).inside == cell.inside) {
                    for (int i = pos; i <= q; i++) {
                        cells.get(i).inside = cells.get(q).inside;
                    }
                    return cells;
                }
            }
        }

        for (int q = pos - 2; q >= fist; q--) {
            if ((cells.get(pos - 1).inside == 0 && cell.inside == 1) || (cells.get(pos - 1).inside == 1 && cell.inside == 0)) {
                while (cells.get(q).inside == cell.inside) {
                    for (int i = pos; i >= q; i--) {
                        cells.get(i).inside = cells.get(q).inside;
                    }
                    return cells;
                }
            }
        }
        return cells;
    }

    private Boolean isValidPlace(int pos) {
        Cell cell = cells.get(pos);
        Boolean isValid;

        isValid = cell.inside == -1;
        if(!isValid) return false;
        if(pos - 1 > 0) isValid = -1 != cells.get(pos - 1).inside;
        if(isValid) return true;
        if(pos + 1 < 64) isValid = -1 != cells.get(pos + 1).inside;
        if(isValid) return true;
        if(pos + 8 < 64) isValid = -1 != cells.get(pos + 8).inside;
        if(isValid) return true;
        if(pos - 8 > 0) isValid = -1 != cells.get(pos - 8).inside;
        if(isValid) return true;
        if (pos + 9 < 64) isValid = -1 != cells.get(pos + 9).inside;
        if(isValid) return true;
        if (pos + 7 < 64) isValid = -1 != cells.get(pos + 7).inside;
        if(isValid) return true;
        if (pos - 9 > 0) isValid = -1 != cells.get(pos - 9).inside;
        if(isValid) return true;
        if (pos - 7 > 0) isValid = -1 != cells.get(pos - 7).inside;
        if(isValid) return true;

        return false;
    }
}