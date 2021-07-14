/* *****************************************************************************
 * Name: Hasit Nanda
 * NetID:
 * Precept:
 *
 * Description: This program creates a helper data type - Blob - which will store
 * clusters of pixels in pictures together
 **************************************************************************** */
public class Blob {

    private int mass; // number of pixels
    private double cx; // sum of x-coordinates of pixels
    private double cy; // sum of y-coordinates of pixels

    //  creates an empty blob
    public Blob() {
        mass = 0;
        cx = 0;
        cy = 0;
    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        cx = (cx * mass + x) / (mass + 1);
        cy = (cy * mass + y) / (mass + 1);
        mass += 1;
    }

    //  number of pixels added to this blob
    public int mass() {
        return mass;
    }

    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        double xDelta = cx - that.cx;
        double yDelta = cy - that.cy;
        return Math.sqrt(Math.pow(xDelta, 2.0) + Math.pow(yDelta, 2.0));
    }

    //  string representation of this blob (see below)
    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", mass, cx, cy);

    }

    //  tests this class by directly calling all instance methods
    public static void main(String[] args) {
        Blob myBlob = new Blob();
        myBlob.add(2, 4);
        myBlob.add(3, 6);
        myBlob.add(4, 8);
        StdOut.println(myBlob);
        int blobMass = myBlob.mass();
        StdOut.println(blobMass);

        Blob thatBlob = new Blob();
        thatBlob.add(3, 4);

        StdOut.println(myBlob.distanceTo(thatBlob));
    }
}
