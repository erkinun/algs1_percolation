/**
 * Created by ERKIN on 13/07/2014.
 */
public class PercolationStats {

    public static void main(String[] args){}

    public PercolationStats(int N, int T){

        if ( N <= 0 ){
            throw new IllegalArgumentException("N must be bigger than 0");
        }
        if ( T <= 0 ){
            throw new IllegalArgumentException("T must be bigger than 0");
        }

        //do the experiment for T times using NxN grids
        int experimentCount = 0;
        while ( experimentCount < T ){
            Percolation percolation = new Percolation(N);

            int openSiteCount = 0;
            while ( !percolation.percolates() ){
                //choose a random site
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;

                percolation.open(i,j);

                //open the site
                openSiteCount++;
            }

            double percolationThreshold = openSiteCount / (N*N);

            //do further calculations
        }
    }
    public double mean(){
        throw new RuntimeException("Not Impl");
    }
    public double stddev(){
        throw new RuntimeException("Not Impl");
    }
    public double confidenceLo(){
        throw new RuntimeException("Not Impl");
    }
    public double confidenceHi(){
        throw new RuntimeException("Not Impl");
    }
}
