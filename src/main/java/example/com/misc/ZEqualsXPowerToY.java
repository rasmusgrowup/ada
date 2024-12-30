package example.com.misc;

public class ZEqualsXPowerToY {
    public static int[] power(int Z) {
        // Pseudokode //
        // Hvis Z > 100.000, print en fejl
        // Hvis Z = X power to Y, returnér værdierne
        // Hvis ikke, returnér default værdi {-1, -1}

        int[] dummyValues = {-1, -1};
        // Hvis Z > 100.000, print en fejl, og returnér dummy værdier
        // Hvis Z<3^3, returnér dummy værdier
        if (Z > 100000 || Z < 27) {
            System.out.println("Z must be between 27 and 100.000");
            return dummyValues;
        }

        // Variabler bruges til at tracke det bedste resultat
        int bestX = -1;
        int bestY = -1;

        // Iterér over mulige værdier af X, startende med X = 3
        for (int X = 3; Math.pow(X, 3) <= Z; X++) {  // Loop stopper hvis X^3 overstiger Z
            // Iterér over mulige værdier af Y, startende med Y = 3
            for (int Y = 3; Math.pow(X, Y) <= Z; Y++) { // Loop stopper hvis X^Y overstiger Z
                int powerValue = (int) Math.pow(X, Y);

                // Hvis X^Y = Z, returnér værdierne
                if (powerValue == Z) {
                    bestX = X;
                    bestY = Y;
                }
            }
        }

        // Hvis vi finder et validt par, returneres dette
        if (bestX != -1 && bestY != -1) {
            System.out.println("Best match found for X^Y=Z: X = " + bestX + ", Y = " + bestY);
            return new int[]{bestX, bestY};
        }

        // Hvis ikke, returneres dummy værdierne
        System.out.println("{" + dummyValues[0] + "," + dummyValues[1] + "}");
        return dummyValues;
    }

    public static void main(String[] args) {
        int tooBig = 100001;
        int tooSmall = 22;
        int testInt = 6561;
        int testInt2 = 3125;
        int testInt3 = 2475;
        power(tooBig);
        power(tooSmall);
        power(testInt);
        power(testInt2);
        power(testInt3);
    }
}
