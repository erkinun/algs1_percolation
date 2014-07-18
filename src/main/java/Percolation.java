/**
 * Created by ERKIN on 13/07/2014.
 */
public class Percolation {

    //TODO specify which exceptions to be thrown?
    //grid size
    private int gridSize;
    private int rowLength;
    private boolean[] openSites;
    //connected sites
    private WeightedQuickUnionUF quickUnionUF;

    public static void main(String[] args){
        StdOut.println("using StdOut");
        Percolation percolation = new Percolation(5);
        StdOut.println(percolation.xyTo1D(1, 1));
        StdOut.println(percolation.xyTo1D(5,5));

        StdOut.println("1,2 open: " + percolation.isOpen(1,1));
        StdOut.println("1,3 open: " + percolation.isOpen(1, 2));
        StdOut.println("1,4 open: " + percolation.isOpen(1, 3));
        StdOut.println("1,5 open: " + percolation.isOpen(1, 4));
        StdOut.println("1,6 open: " + percolation.isOpen(1, 5));

        StdOut.println("(1,1) and (1,2) connected: " +
                percolation.quickUnionUF.connected(percolation.xyTo1D(1,1), percolation.xyTo1D(1,2)));
    }

    public Percolation(int N){
        gridSize = N*N;

        //open sites and virtual sites are being opened
        openSites = new boolean[gridSize + 2];
        openSites[0] = true;
        openSites[gridSize + 1] = true;

        rowLength = N;
        //weightedUF const 1 for virtual top 0 and 1 for virtual bottom N + 1
        quickUnionUF = new WeightedQuickUnionUF(gridSize + 2);
        for(int i = 1; i <= N; i++){
            //connect top
            quickUnionUF.union(0,i);
            openSites[i] = true;
            //connect bottom
            int bottomRow = N * (N-1);
            quickUnionUF.union(gridSize-1, bottomRow + i );
            openSites[bottomRow + i] = true;
        }

    }
    public void open(int i, int j) {
        int oneDimension = xyTo1D(i,j);
        openSites[oneDimension] = true;

        //who are my neighbors?
        //left
        int leftI = i - 1;
        if ( leftI >= 1 && isOpen(leftI, j) ){
            int left1Dimension = xyTo1D(leftI, j);
            quickUnionUF.union(oneDimension, left1Dimension);
        }
        //right
        int rightI = i + 1;
        if ( rightI <= rowLength && isOpen(rightI, j)){
            int right1Dimension = xyTo1D(rightI, j);
            quickUnionUF.union(oneDimension, right1Dimension);
        }
        //top
        int topJ = j - 1;
        if (topJ >= 1 && isOpen(i, topJ)){
            int top1Dimension = xyTo1D(i, topJ);
            quickUnionUF.union(oneDimension, top1Dimension);
        }
        //bottom
        int bottomJ = j + 1;
        if (bottomJ <= rowLength && isOpen(i, bottomJ)){
            int bottom1Dimension = xyTo1D(i, bottomJ);
            quickUnionUF.union(oneDimension, bottom1Dimension);
        }

    }
    public boolean isOpen(int i, int j){
        //return quickUnionUF.connected(i, j);
        return openSites[xyTo1D(i,j)];
    }
    public boolean isFull(int i, int j){

        int oneDimension = xyTo1D (i,j);

        return isOpen(i,j) && quickUnionUF.connected(0, oneDimension);
    }
    public boolean percolates(){
        return quickUnionUF.connected(0, gridSize + 1); //top and bottom connected?
    }


    private int xyTo1D(int x, int y){
        //works for 1 to N

        validateIndices(x, y);

        x = x - 1;
        y = y - 1;

        return y + (x * rowLength) + 1;
    }

    private void validateIndices(int x, int y) {
        if ( x < 1 || x > rowLength + 1) { throw new IndexOutOfBoundsException("x was out bounds: " + x); }
        if ( y < 1 || y > rowLength + 1) { throw new IndexOutOfBoundsException("y was out bounds: " + y); }
    }

}
