/**
 * Created by ERKIN on 13/07/2014.
 */
public class Percolation {

    //TODO specify which exceptions to be thrown?
    //grid size
    private int gridSize;
    private int rowLength;
    //open sites?
    //connected sites
    private WeightedQuickUnionUF quickUnionUF;

    public static void main(String[] args){
       StdOut.println("using StdOut");
    }

    private int xyTo1D(int x, int y){
        return x + (y * rowLength) + 1;
    }

    public Percolation(int N){
        gridSize = N*N;
        rowLength = N;
        //weightedUF const 1 for virtual top 0 and 1 for virtual bottom N + 1
        quickUnionUF = new WeightedQuickUnionUF(gridSize + 2);
        //create NxN, create bottom and top virtuals put them on open sites
    }
    public void open(int i, int j) {
        throw new RuntimeException("Not Impl");
    }
    public boolean isOpen(int i, int j){
        throw new RuntimeException("Not Impl");
    }
    public boolean isFull(int i, int j){
        throw new RuntimeException("Not Impl");
    }
    public boolean percolates(){
        throw new RuntimeException("Not Impl");
    }

}
