package com.mos7af.hijri;
/**
 *
 * @author http://www.cepmuvakkit.com
 */
public class EclipticPosition {

    //------------------------------------------------------------------------------
//
// MiniSun: Computes the Sun's RA and declination using a low precision
//          analytical series
//
// Input:
//
//   T         Time in Julian centuries since J2000
//
// Output:
//
//   L        ecliptic longitude  of the Sun in [rad]
//------------------------------------------------------------------------------
    static double getMiniSunLongitude(double T) {
        double pi2 = Math.PI * 2;
        double M;

        // Mean anomaly and ecliptic longitude
        M = pi2 * APC_Math.Frac(0.993133 + 99.997361 * T);
        return pi2 * APC_Math.Frac(0.7859453 + M / pi2 + (6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M) + 6191.2 * T) / 1296.0e3);

    }

//------------------------------------------------------------------------------
//
// MiniMoon: Computes the Moon's REcliptic longitude and latitude using a low precision
//           analytical series
//
// Input:
//
//   T         Time in Julian centuries since J2000
//
// Output:
//
//   l_Moon       Ecliptic longitude of the Moon in [rad]
//   b_Moon       Ecliptic latitude of the Moon in [rad]
//
//------------------------------------------------------------------------------
    static double[] getMiniMoon(double T) {

        double[] moonLongLat = new double[2];
        double pi2 = 2 * Math.PI;
        double Arcs = 3600.0 * 180.0 / Math.PI;
        // Variables
        double L_0, l, ls, F, D, dL, S, h, N;
        // Mean elements of lunar orbit

        L_0 = APC_Math.Frac(0.606433 + 1336.855225 * T);       // mean longitude [rev]

        l = pi2 * APC_Math.Frac(0.374897 + 1325.552410 * T);  // Moon's mean anomaly
        ls = pi2 * APC_Math.Frac(0.993133 + 99.997361 * T);  // Sun's mean anomaly
        D = pi2 * APC_Math.Frac(0.827361 + 1236.853086 * T);  // Diff. long. Moon-Sun
        F = pi2 * APC_Math.Frac(0.259086 + 1342.227825 * T);  // Dist. from ascending node


        // Perturbations in longitude and latitude
        dL = +22640 * Math.sin(l) - 4586 * Math.sin(l - 2 * D) + 2370 * Math.sin(2 * D) + 769 * Math.sin(2 * l) - 668 * Math.sin(ls) - 412 * Math.sin(2 * F) - 212 * Math.sin(2 * l - 2 * D) - 206 * Math.sin(l + ls - 2 * D) + 192 * Math.sin(l + 2 * D) - 165 * Math.sin(ls - 2 * D) - 125 * Math.sin(D) - 110 * Math.sin(l + ls) + 148 * Math.sin(l - ls) - 55 * Math.sin(2 * F - 2 * D);
        S = F + (dL + 412 * Math.sin(2 * F) + 541 * Math.sin(ls)) / Arcs;
        h = F - 2 * D;
        N = -526 * Math.sin(h) + 44 * Math.sin(l + h) - 31 * Math.sin(-l + h) - 23 * Math.sin(ls + h) + 11 * Math.sin(-ls + h) - 25 * Math.sin(-2 * l + F) + 21 * Math.sin(-l + F);


        // Ecliptic longitude and latitude
        moonLongLat[0] = pi2 * APC_Math.Frac(L_0 + dL / 1296.0e3);
        moonLongLat[1] = (18520.0 * Math.sin(S) + N) / Arcs;

        return moonLongLat;

    }
}
