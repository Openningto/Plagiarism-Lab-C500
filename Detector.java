import java.util.ArrayList;

public class Detector{
  final String string1;
  final String string2;
  final double similarityScore;
  private ArrayList<String> cList;

  public String getString1() {
    return string1;
  }

  public String getString2() {
    return string2;
  }

  public ArrayList<String> getCList() {
    return cList;
  }

  public Detector(String orig, String cand){
    string1 = orig;
    string2 = cand;
    this.cList = new ArrayList<>();
    similarityScore = (double)(score(string1, string2)) / (string1.length() + string2.length());
  }

  public double getSimilarityScore(){
    return similarityScore;
  }

  public int score(String a, String b){
    for(int i = a.length(); i > 0; i--){
      for(int start = 0; start <= a.length() - i; start++){
        String substring = a.substring(start, start + i);
        if(b.indexOf(substring) != -1){
          cList.add(substring);
          return substring.length()*2 + split(substring, a, b);
        }
      }
    }
    return 0;
  }

  public int split(String i,String a, String b) {
    String lA = a.substring(0, a.indexOf(i));
    String lB = b.substring(0, b.indexOf(i));

    String rA = a.substring(a.indexOf(i) + i.length());
    String rB = b.substring(b.indexOf(i) + i.length());

    return score(lA, lB) + score(rA, rB);
  }
}
