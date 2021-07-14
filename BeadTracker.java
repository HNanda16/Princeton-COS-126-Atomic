/* *****************************************************************************
 * Name: Hasit Nanda
 * NetID:
 * Precept:
 *
 * Description: This program takes runs of photos containing particles as inputs
 * and uses the BeadFinder class to track the movement of beads over time
 **************************************************************************** */
public class BeadTracker {
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);


        for (int i = 0; i < args.length - 4; i++) {

            Picture afterPicture = new Picture(args[i + 4]);
            Picture beforePicture = new Picture(args[i + 3]);
            BeadFinder after = new BeadFinder(afterPicture, tau);
            BeadFinder before = new BeadFinder(beforePicture, tau);
            Blob[] afterBeads = after.getBeads(min);
            Blob[] beforeBeads = before.getBeads(min);

            for (int j = 0; j < afterBeads.length; j++) {

                double minimum = Double.POSITIVE_INFINITY;
                int pointer = -1;
                for (int k = 0; k < beforeBeads.length; k++) {
                    if (afterBeads[j].distanceTo(beforeBeads[k]) < minimum && afterBeads[j]
                            .distanceTo(beforeBeads[k]) < delta) {

                        minimum = afterBeads[j].distanceTo(beforeBeads[k]);
                        pointer = k;
                    }
                }
                if (pointer != -1) {
                    StdOut.println(String.format("%.4f", minimum));
                }
            }
            StdOut.println("");
        }
    }
}
