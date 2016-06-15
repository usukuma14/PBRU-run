package pbru.it.sukuma.pbrurun;

/**
 * Created by lap324-12 on 6/13/2016 AD.
 */
public class Mydata {

    //explicit
    private int[] avatarInts = new int[]{R.drawable.bird48, R.drawable.doremon48,
    R.drawable.kon48, R.drawable.nobita48, R.drawable.rat48};
    private double latADouble = 13.071490, lngADouble = 99.976746;
    private double[] buildLatDoubles = new double[]{13.071426, 13.071456};
    private double[] buildLngDoubles = new double[]{99.976740, 99.976750};
    private int[] buildIconInts = new int[]{R.drawable.th, R.drawable.sg};

    public double[] getBuildLatDoubles() {
        return buildLatDoubles;
    }

    public double[] getBuildLngDoubles() {
        return buildLngDoubles;
    }

    public int[] getBuildIconInts() {
        return buildIconInts;
    }

    public double getLatADouble() {
        return latADouble;
    }

    public double getLngADouble() {
        return lngADouble;
    }

    public int[] getAvatarInts() {


        return avatarInts;


    }
}   //main class
