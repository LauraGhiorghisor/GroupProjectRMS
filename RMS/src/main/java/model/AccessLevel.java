package model;

public enum AccessLevel {
    SYSTEM_ADMIN, RECORDS_STAFF, COURSE_LEADER, TUTOR;

    public static AccessLevel fromInt(int userId) {
        switch (userId) {
            case 1: return SYSTEM_ADMIN;
            case 2: return RECORDS_STAFF;
            case 3: return COURSE_LEADER;
            case 4: return TUTOR;
            default: throw new IllegalArgumentException("Access ID not supported.");
        }
    }

    public boolean hasAccessToSysAdminView() {
        if (this == SYSTEM_ADMIN)
            return true;
        else
            return false;
    }
    public boolean hasAccessToRecView() {
        if (this == RECORDS_STAFF)
            return true;
        else
            return false;
    }
    public boolean hasAccessToCourseLeadView() {
        if (this == COURSE_LEADER)
            return true;
        else
            return false;
    }
    public boolean hasAccessToTutorView() {
        if (this == TUTOR)
            return true;
        else
            return false;
    }
}
