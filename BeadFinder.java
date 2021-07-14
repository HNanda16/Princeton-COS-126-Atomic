/* *****************************************************************************
 * Name: Hasit Nanda
 * NetID:
 * Precept:
 *
 * Description: This program uses the Blob helper data type to identify and store
 * Beads in a photograph
 **************************************************************************** */
public class BeadFinder {

    // Integer variable to keep track of size of blob array
    private int blobPointer;
    // Integer variable to keep track of size of bead array
    private int beadPointer;
    // Array to store all Blobs
    private Queue<Blob> blobList;
    // 2 dimensional boolean area keeps track of which pixels have been visited
    private boolean[][] visit;

    // Beadfinder constructor
    public BeadFinder(Picture picture, double tau) {
        blobList = new Queue<Blob>();
        visit = new boolean[picture.height()][picture.width()];
        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                visit[i][j] = false;
            }
        }

        blobPointer = 0;
        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                if (visit[i][j] == false && Luminance.intensity(picture.get(j, i)) > tau) {
                    Blob newBlob = new Blob();
                    blobList.enqueue(depthFirstSearch(picture, tau, newBlob, i, j));
                    blobPointer += 1;
                }
                visit[i][j] = true;
            }
        }

    }

    // Function to perform Depth First Search
    private Blob depthFirstSearch(Picture picture, double tau, Blob blob,
                                  int i, int j) {
        blob.add(j, i);
        visit[i][j] = true;
        if (i < picture.height() - 1) {
            if (visit[i + 1][j] == false && Luminance.intensity(picture.get(j, i + 1)) > tau) {
                visit[i + 1][j] = true;
                blob = depthFirstSearch(picture, tau, blob, i + 1, j);
            }
        }
        if (i > 0) {
            if (visit[i - 1][j] == false && Luminance.intensity(picture.get(j, i - 1)) > tau) {
                visit[i - 1][j] = true;
                blob = depthFirstSearch(picture, tau, blob, i - 1, j);
            }
        }
        if (j < picture.width() - 1) {
            if (visit[i][j + 1] == false && Luminance.intensity(picture.get(j + 1, i)) > tau) {
                visit[i][j + 1] = true;
                blob = depthFirstSearch(picture, tau, blob, i, j + 1);
            }
        }
        if (j > 0) {
            if (visit[i][j - 1] == false && Luminance.intensity(picture.get(j - 1, i)) > tau) {
                visit[i][j - 1] = true;
                blob = depthFirstSearch(picture, tau, blob, i, j - 1);
            }
        }

        return blob;
    }

    //  Function that finds all blobs in the specified picture using luminance threshold tau


    // Function that returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {
        beadPointer = 0;
        Blob[] temp = new Blob[blobPointer];
        for (int i = 0; i < blobPointer; i++) {
            Blob current = blobList.dequeue();
            if (current.mass() >= min) {
                temp[beadPointer] = current;
                beadPointer += 1;
            }
        }
        Blob[] beadList = new Blob[beadPointer];
        for (int i = 0; i < beadPointer; i++) {
            beadList[i] = temp[i];
        }

        return beadList;
    }

    //  test client, as described below
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        Picture myPicture = new Picture(args[2]);
        BeadFinder beadFinder = new BeadFinder(myPicture, tau);
        Blob[] beadList = beadFinder.getBeads(min);

        for (int i = 0; i < beadFinder.beadPointer; i++) {
            StdOut.println(beadList[i]);
        }
    }
}
