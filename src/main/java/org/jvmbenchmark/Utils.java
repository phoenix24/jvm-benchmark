package org.jvmbenchmark;

public class Utils {
    public static String getVersion() {
        return System.getProperty("java.version");
    }

    public static String getReportName() {
        String version = getVersion();
        return String.format("jmh_report_%s.csv", version);
    }
}
