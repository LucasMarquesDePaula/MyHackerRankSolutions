package algorithms.recursion;

/**
 * https://www.hackerrank.com/challenges/password-cracker/problem
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PasswordCracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            t--;
            int n = sc.nextInt();

            Tree root = new Tree();
            while (n > 0) {
                n--;

                // Build tree
                Tree tree = root;
                String password = sc.next();
                for (int i = 0, l = password.length(); i < l; i++) {
                    tree = tree.add(password.charAt(i));
                }

            }

            String loginAttempt = sc.next();

            int start = -1;
            int end = 0;
            StringBuilder out = new StringBuilder();

            while (start < end) {
                start = end;
                end = nextWord(loginAttempt, start, root);
                String word = loginAttempt.substring(start, end);
//                System.out.println(word);
                out.append(word);
                out.append(" ");
            }
//            System.out.println(out.toString());
//            System.out.println(end);
//            System.out.println(loginAttempt.length());
            System.out.println(end == loginAttempt.length() ? out.toString().trim() : "WRONG PASSWORD");

//            showTree(root);
        }
    }

    private static int nextWord(String words, int index, Tree tree) {
        try {
            return nextWord(words, index + 1, tree.get(words.charAt(index)));
        } catch (Exception ignored) {
            return index;
        }
    }

    private static void showTree(Tree tree) {
        showTree(tree, "");
    }

    private static void showTree(Tree tree, String prefix) {
        Set<Character> keySet = tree.getChildren().keySet();

        if (keySet.size() == 0) {
            System.out.println(prefix);
            return;
        }

        for (Character key : keySet) {
            showTree(tree.get(key), prefix + key);
        }

    }

    private static class Tree {

        private final Map<Character, Tree> children = new HashMap<>();

        public Tree add(char key) {
            if (!this.children.containsKey(key)) {
                this.children.put(key, new Tree());
            }
            return this.children.get(key);
        }

        public Tree get(char key) {
            if (this.children.containsKey(key)) {
                return this.children.get(key);
            }

            throw new RuntimeException("Key not found.");
        }

        public Map<Character, Tree> getChildren() {
            return this.children;
        }
    }
}
