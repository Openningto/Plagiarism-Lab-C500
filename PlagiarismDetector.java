import java.io.File;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;


public class PlagiarismDetector {

    private String submissions;
    private String sources;

    private final static double PLAGIARISM_THRESHOLD = 0.1;
    private final static String DIRECTORY = new File("").getAbsolutePath();
    private ArrayList<PairReport> reports;

    public PlagiarismDetector(String sub, String sou) {
        submissions = sub;
        sources = sou;
        reports = new ArrayList<>();
        generateReports();
    }

    private void generateReports() {
        File[] subFiles = new File(submissions).listFiles();
        File[] sourceFiles = new File(sources).listFiles();
        reports = new ArrayList<>();

        ArrayList<File> toTest = new ArrayList<>();

        for (File sub : subFiles) {
            for (File sou : sourceFiles) {
                if (sub.getName().contains(".docx") && sou.getName().contains(".docx")) {
                    DocXReader sourceReader = new DocXReader(sou.getAbsolutePath());
                    DocXReader subReader = new DocXReader(sub.getAbsolutePath());

                    String sourceText = sourceReader.readFile();
                    String subText = subReader.readFile();

                    sourceText = Cleaner.cleanFull(Cleaner.cleanXML(sourceText));
                    subText = Cleaner.cleanFull(Cleaner.cleanXML(subText));

                    Detector detector = new Detector(sourceText, subText);
                    reports.add(new PairReport(sub.getName(), sou.getName(), detector));
                }
            }
        }
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

    public static void main(String[] args) {
        PlagiarismDetector pd = new PlagiarismDetector(DIRECTORY+"/submissions", DIRECTORY+"/sources");
        pd.generateReports();
        pd.getAllPotentialOffenderrs();
        pd.printReports();
    }
}
