package com.mos7af.hijri;

/**
 *
 * @author http://www.cepmuvakkit.com
 */
public class APC_Math {
//------------------------------------------------------------------------------
//
// Frac: Gives the fractional part of a number
//
//------------------------------------------------------------------------------

    static double Frac(double x) {

        return x - (long) x;
    }

//------------------------------------------------------------------------------
//
// Ddd: Conversion of angular degrees, minutes and seconds of arc to decimal
//   representation of an angle 
//
// Input:
//
//   D        Angular degrees
//   M        Minutes of arc
//   S        Seconds of arc
//
// <return>:  Angle in decimal representation
//*BU*
//------------------------------------------------------------------------------
    static double Ddd(int D, int M, double S) {
        //
        // Variables
        //
        double sign;


        if ((D < 0) || (M < 0) || (S < 0)) {
            sign = -1.0;
        } else {
            sign = 1.0;
        }

        return sign * (Math.abs(D) + Math.abs(M) / 60.0 + Math.abs(S) / 3600.0);
    }
//------------------------------------------------------------------------------
//
// Pegasus: Root finder using the Pegasus method
//
// Input:
//
//   PegasusFunct  Pointer to the function to be examined
//
//   LowerBound    Lower bound of search interval
//   UpperBound    Upper bound of search interval
//   Accuracy      Desired accuracy for the root
//
// Output:
//
//   Root          Root found (valid only if Success is true)
//   Success       Flag indicating success of the routine
//
// References:
//
//   Dowell M., Jarratt P., 'A modified Regula Falsi Method for Computing
//     the root of an equation', BIT 11, p.168-174 (1971).
//   Dowell M., Jarratt P., 'The "PEGASUS Method for Computing the root
//     of an equation', BIT 12, p.503-508 (1972).
//   G.Engeln-Muellges, F.Reutter, 'Formelsammlung zur Numerischen
//     Mathematik mit FORTRAN77-Programmen', Bibliogr. Institut,
//     Zuerich (1986).
//
// Notes:
//
//   Pegasus assumes that the root to be found is bracketed in the interval
//   [LowerBound, UpperBound]. Ordinates for these abscissae must therefore
//   have different signs.
//
//------------------------------------------------------------------------------
    public static double Pegasus(MoonPhases moonPhase, double LowerBound, double UpperBound, double Accuracy, boolean[] Success) {


        double x1 = LowerBound;
        double x2 = UpperBound;
        double f1 = moonPhase.calculatePhase(x1);
        double f2 = moonPhase.calculatePhase(x2);
        double x3, f3, Root;
        int MaxIterat = 30;

        int Iterat = 0;

        // Initialization
        Success[0] = false;
        Root = x1;


        // Iteration
        if (f1 * f2 < 0.0) {
            do {
                // Approximation of the root by interpolation
                x3 = x2 - f2 / ((f2 - f1) / (x2 - x1));
                f3 = moonPhase.calculatePhase(x3);

                // Replace (x1,f2) and (x2,f2) by new values, such that
                // the root is again within the interval [x1,x2]
                if (f3 * f2 <= 0.0) {
                    // Root in [x2,x3]
                    x1 = x2;
                    f1 = f2; // Replace (x1,f1) by (x2,f2)
                    x2 = x3;
                    f2 = f3; // Replace (x2,f2) by (x3,f3)
                } else {
                    // Root in [x1,x3]
                    f1 = f1 * f2 / (f2 + f3); // Replace (x1,f1) by (x1,f1')
                    x2 = x3;
                    f2 = f3;     // Replace (x2,f2) by (x3,f3)
                }

                if (Math.abs(f1) < Math.abs(f2)) {
                    Root = x1;
                } else {
                    Root = x2;
                }

                Success[0] = (Math.abs(x2 - x1) <= Accuracy);
                Iterat++;
            } while (!Success[0] && (Iterat < MaxIterat));
        }

        return Root;
    }
}

     

