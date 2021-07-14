/* *****************************************************************************
 * Name: Hasit Nanda
 * NetID:
 * Precept:
 *
 * Description: This program uses the BeadTracker Program on runs of images
 * to estimate the values of Avogadro's Constant and Boltzmann's Constant.
 **************************************************************************** */
public class Avogadro {
    public static void main(String[] args) {
        double[] inputs = new double[0];
        while (!StdIn.isEmpty()) {
            inputs = StdIn.readAllDoubles();
        }

        double squareSum = 0;
        // Pixels to meters conversion constant
        double CONVERT = 0.000000175;
        for (int i = 0; i < inputs.length; i++) {
            squareSum += Math.pow(inputs[i] * CONVERT, 2);
        }
        double DIFFUSION_CONSTANT = squareSum / (2 * inputs.length);

        double PI = Math.PI;
        double VISCOSITY = 0.0009135;
        double RADIUS = 0.0000005;
        double TEMP = 297;
        double GAS_CONSTANT = 8.31446;

        double boltzmann = (6 * DIFFUSION_CONSTANT * PI * RADIUS * VISCOSITY) / TEMP;
        double avogadro = GAS_CONSTANT / boltzmann;

        StdOut.println("Boltzmann = " + String.format("%.4e", boltzmann));
        StdOut.println("Avogadro = " + String.format("%.4e", avogadro));

    }
}
