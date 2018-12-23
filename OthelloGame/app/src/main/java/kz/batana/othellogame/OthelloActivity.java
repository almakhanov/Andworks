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
//                book.toggleFavorite();
                Log.d("accepted", cell.toString());
                Toast.makeText(OthelloActivity.this, cell.row+"x"+cell.column, Toast.LENGTH_SHORT).show();

                adapter.notifyDataSetChanged();
            }
        });
    }

    private ArrayList<Cell> getCells(){
        ArrayList<Cell> mCells = new ArrayList<>();
        for(int i = 0; i < 64; i++){
            mCells.add(new Cell(i + 1, -1, false, i / 8 + 1, i % 8 + 1));
        }
        mCells.get(27).inside = 1;
        mCells.get(28).inside = 0;
        mCells.get(35).inside = 0;
        mCells.get(36).inside = 1;
        return mCells;
    }
}
