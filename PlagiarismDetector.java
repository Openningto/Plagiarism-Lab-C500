public class PlagiarismDetector {

    private String submissions;
    private String sources;
    private Detector detector;

    public PlagiarismDetector(Detector det, String sub, String sou) {
        detector = det;
        submissions = sub;
        sources = sou;
    }

    public List<String> getCList() {
        return det.getCList();
    }
}
