package com.dusol.thelearnerscommunity.utils;

import java.util.Calendar;

public class ExamSeasonManager {

    // Firebase node keys — must match exactly what's in the database
    public static final String KEY_SEM_2468 = "sem2468";
    public static final String KEY_SEM_1357 = "sem1357";

    // Semester group labels for UI display
    public static final String LABEL_SEM_2468 = "2nd / 4th / 6th / 8th Semester";
    public static final String LABEL_SEM_1357 = "1st / 3rd / 5th / 7th Semester";

    /**
     * Returns the Firebase node key for the currently active/upcoming exam semester.
     *
     * Date → Semester mapping (covers all 12 months, no gaps):
     *   April–August   (months 4–8)  → sem2468  [exams are happening NOW]
     *   March          (month 3)      → sem2468  [April exams approaching — show upcoming]
     *   October–Feb    (months 10–12, 1–2) → sem1357  [exams are happening NOW]
     *   September      (month 9)      → sem1357  [October exams approaching — show upcoming]
     */
    public static String getActiveSemesterKey() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1; // Calendar.MONTH is 0-indexed

        if (month >= 3 && month <= 8) {
            // March through August → sem2468 (April exam season, or approaching it)
            return KEY_SEM_2468;
        } else {
            // September through February → sem1357 (October exam season, or approaching it)
            return KEY_SEM_1357;
        }
    }

    /** Returns the human-readable label for the active semester group. */
    public static String getActiveSemesterLabel() {
        return getActiveSemesterKey().equals(KEY_SEM_2468) ? LABEL_SEM_2468 : LABEL_SEM_1357;
    }

    /** Returns true if we're in a transition month (March or September — exams approaching but not started) */
    public static boolean isUpcomingExamPeriod() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return month == 3 || month == 9;
    }

    /** Returns true if exams are currently ongoing (not a transition month) */
    public static boolean isActiveExamPeriod() {
        return !isUpcomingExamPeriod();
    }
}
