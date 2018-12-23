package kz.batana.othellogame;

public class Cell {
    int id;
    int inside;
    boolean isAvailable;
    int row;
    int column;

    public Cell(int id, int inside, boolean isAvailable, int row, int column) {
        this.id = id;
        this.inside = inside;
        this.isAvailable = isAvailable;
        this.row = row;
        this.column = column;
    }

    public int getId() {
        return id;
    }

    public int getInside() {
        return inside;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInside(int inside) {
        this.inside = inside;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", inside=" + inside +
                ", isAvailable=" + isAvailable +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
