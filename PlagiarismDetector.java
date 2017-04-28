import java.io.File;


public class PlagiarismDetector {

    private String submissions;
    private String sources;

    final static double PLAGIARISM_THRESHOLD = 0.1;
    private ArrayList<PairReport> reports;

    public PlagiarismDetector(String sub, String sou) {
        submissions = sub;
        sources = sou;
        reports = new ArrayList<>();
        generateReports();
    }

    private void generateReports() {
        File[] subFile = new File(submissions).listFiles();
        File[] sourceFile
    }

    public ArrayList<PairReport> getAllPotentialOffenderrs( ){
        ArrayList<PairReport> result = new ArrayList<PairReport>();

        for (PairReport report : reports) {
            if (report.getSimilarity() > PLAGIARISM_THRESHOLD) {
                result.add(report);
            }
        }

        return result;
    }

    public void printReports() {
        ArrayList<PairReport> copy = new ArrayList<>(reports);
        Collections.sort(copy);
        System.out.println("----- PAIR REPORTS -----");
        int count = 1;
        for (PairReport report : copy) {
            System.out.println(count+". Score: "+report.getSimilarity()+" | File 1: "+report.getCleaner1()+" | File 2: "+report.getCleaner2());
            count++;
        }
    }

    public List<String> getCList() {
        return det.getCList();
    }
}
