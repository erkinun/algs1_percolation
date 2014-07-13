/**
 * Created by ERKIN on 13/07/2014.
 */
public class PercolationStats {

    private double[] percolationResults;

    public static void main(String[] args){

        StdOut.println("Running Monte Carlo Experiment for Percolation");

        if ( args.length < 2 ){
            StdOut.println("You have to supply both N grid size and T experiment size");
            return;
        }

        PercolationStats stats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]) );

        StdOut.println("mean = " + stats.mean());
        StdOut.println("stddev = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }

    public PercolationStats(int N, int T){

        if ( N <= 0 ){
            throw new IllegalArgumentException("N must be bigger than 0");
        }
        if ( T <= 0 ){
            throw new IllegalArgumentException("T must be bigger than 0");
        }

        percolationResults = new double[T];

        //do the experiment for T times using NxN grids
        int experimentCount = 0;
        while ( experimentCount < T ){
            Percolation percolation = new Percolation(N);

            int openSiteCount = 0;
            while ( !percolation.percolates() ){
                //choose a random site
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;

                StdOut.println("opening another site at: " + i + ", " + j);

                percolation.open(i,j);

                //open the site
                openSiteCount++;
            }

            double percolationThreshold = openSiteCount / (N*N);
            percolationResults[experimentCount] = percolationThreshold;
            experimentCount++;
        }

    }
    public double mean(){
        double sum = 0.0;
        for ( int i = 0; i < percolationResults.length; i++ ){
            sum += percolationResults[i];
        }

        return sum / percolationResults.length;
    }
    public double stddev(){

        double mean = mean();
        double devSum = 0.0;
        for ( int i = 0; i < percolationResults.length; i++ ){
            double deviation = Math.pow(percolationResults[i] - mean, 2);
            devSum += deviation;
        }

        double devSq = devSum / percolationResults.length - 1;
        return Math.sqrt(devSq);
    }
    public double confidenceLo(){
        double mean = mean();
        double stdDev = stddev();

        return mean - ((1.96*stdDev) / Math.sqrt(percolationResults.length) );
    }
    public double confidenceHi(){
        double mean = mean();
        double stdDev = stddev();

        return mean + ((1.96*stdDev) / Math.sqrt(percolationResults.length) );
    }
}
