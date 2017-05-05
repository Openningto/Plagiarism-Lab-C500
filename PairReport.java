import java.util.ArrayList;

public class PairReport implements Comparable<PairReport> {
    private String cleanerFile1;
    private String cleanerFile2;
    private Detector detector;

    public PairReport(String file1, String file2, Detector dec) {
        cleanerFile1 = file1;
        cleanerFile2 = file2;
        detector = dec;
    }

    public int compareTo(PairReport other) {
        double thisScore = this.detector.getSimilarityScore();
        double otherScore = other.detector.getSimilarityScore();
        if (thisScore > otherScore) {
            return 1;
        } else if (thisScore < otherScore) {
            return -1;
        }
        return 0;
    }

    public String getCleaner1() {
        return cleanerFile1;
    }

    public String getCleaner2() {
        return cleanerFile2;
    }

    public String getString1() {
        return detector.getString1();
    }

    public String getString2() {
        return detector.getString2();
    }

    public double getSimilarity() {
        return detector.getSimilarityScore();
    }

    public ArrayList<String> getCList() {
        return detector.getCList();
    }

    public Detector getDetector() {
        return detector;
    }
}
