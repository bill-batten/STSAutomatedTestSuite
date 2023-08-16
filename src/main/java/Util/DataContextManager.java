package Util;

public class DataContextManager {
    private static boolean isUserDataCreated = false;
    private static boolean isRoleDataCreated = false;

    public static boolean isPublicAuthorityDataCreated() {
        return isUserDataCreated;
    }

    public static boolean isSchemeDataCreated() {
        return isRoleDataCreated;
    }

    public static void setPublicAuthorityDataCreated(boolean value) {
        isUserDataCreated = value;
    }

    public static void setSchemeDataCreated(boolean value) {
        isRoleDataCreated = value;
    }
}