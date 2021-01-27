import java.util.*;

public class Main {
    private static final String input = UserInput.getLine();
    private static final List<String> arrayList = new ArrayList<>(CoolNumbers.generateCoolNumbers());
    private static final HashSet<String> hashSet = new HashSet<>(arrayList);
    private static final TreeSet<String> treeSet = new TreeSet<>(arrayList);

    public static void main(String[] args) {
        CoolNumbers.bruteForceSearchInList(arrayList, input);
        CoolNumbers.binarySearchInList(arrayList, input);
        CoolNumbers.searchInHashSet(hashSet, input);
        CoolNumbers.searchInTreeSet(treeSet, input);
    }
}