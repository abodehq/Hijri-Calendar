package com.mos7af.hijri;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author http://www.cepmuvakkit.com
 */
public class CrescentMoon extends MoonPhases {

    EclipticPosition eclipPos;
//------------------------------------------------------------------------------
//
// PhasesFunc: Goal function for search of phase events [-pi, pi]
//
// Input:
//
//   T         Ephemeris Time (Julian centuries since J2000)
//
// <return>:   Difference between the longitude of the Moon from the Sun
//             and the nominal value for a given phase (New Moon 0, First
//             Quarter pi/2, etc.) (in [rad])
//
// Global:
//
//   Phase     Flag for desired lunar phase
//
//------------------------------------------------------------------------------
    public double calculatePhase(double T) {
        //
        // Constants
        //
        double tau_Sun = 8.32 / (1440.0 * 36525.0);    // 8.32 min  [cy]
        double beta, l_Moon, l_Sun;
        double[] moonLongLat;
        double angle = 8;// 8 degree; crescent visibility criteria
        //
        // Variables
        //
        l_Sun = EclipticPosition.getMiniSunLongitude(T - tau_Sun);
        moonLongLat = EclipticPosition.getMiniMoon(T);
        l_Moon = moonLongLat[0];
        beta = moonLongLat[1];


        double LongDiff = l_Moon - l_Sun;
        double elongation = Math.sqrt(LongDiff * LongDiff + beta * beta);

        return (Math.PI * angle / 180.0 - elongation);
    }
}
