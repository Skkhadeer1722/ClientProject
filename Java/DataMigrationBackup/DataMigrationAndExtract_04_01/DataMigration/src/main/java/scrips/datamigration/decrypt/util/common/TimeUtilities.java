/**
 @(#)TimeUtilities.java
 @version 0.0.1 23-Apr-2016

********************************************************************************

Copyright(c) 2016 BCS Information Systems Pte Ltd
11 Tampines Concourse 
#02-01/05,
Singapore 528729

All Right Reserved.

********************************************************************************
This software is the confidential and proprietary information of 
BCSIS Pte Ltd. ("Confidential Information"). You shall not disclose 
such Confidential Information and shall use it only in accordance with 
the terms of the license agreement you entered into with BCSIS.
********************************************************************************
*/


package scrips.datamigration.decrypt.util.common;

import java.util.concurrent.TimeUnit;

/**
 * TimeUtilities
 * Credit to David Blevins, answerer from stack overflow
 * http://stackoverflow.com/questions/3859288/how-to-calculate-time-ago-in-java/5062810#5062810
 * @version  1.0.0 12-May-2016
 * @author   Win Htut Aung
 */

public class TimeUtilities {
    /**
     * Converts time to a human readable format within the specified range
     *
     * @param duration the time in milliseconds to be converted
     * @param max      the highest time unit of interest
     * @param min      the lowest time unit of interest
     */
    public static String formatMillis(long duration, TimeUnit max, TimeUnit min) {
        StringBuilder res = new StringBuilder();

        TimeUnit current = max;

        while (duration > 0) {
            long temp = current.convert(duration, TimeUnit.MILLISECONDS);

            if (temp > 0) {
                duration -= current.toMillis(temp);
                res.append(temp).append(" ").append(current.name().toLowerCase());
                if (temp < 2) res.deleteCharAt(res.length() - 1);
                res.append(", ");
            }

            if (current == min) break;

            current = TimeUnit.values()[current.ordinal() - 1];
        }

        // clean up our formatting....

        // we never got a hit, the time is lower than we care about
        if (res.lastIndexOf(", ") < 0) return "0 " + min.name().toLowerCase();

        // yank trailing  ", "
        res.deleteCharAt(res.length() - 2);

        //  convert last ", " to " and"
        int i = res.lastIndexOf(", ");
        if (i > 0) {
            res.deleteCharAt(i);
            res.insert(i, " and");
        }

        return res.toString();
    }

}
