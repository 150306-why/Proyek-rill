package del.ifs24024;

import java.util.Scanner;

import del.ifs24024.program1;

public class Test {

 //Expedition to the Ancient Network(PROGRAM 1)
    public static void main(String[] args) {

        String input =
            "6 8 2\n" +
            "3 15 5 10\n" +
            "1 2 10\n" +
            "1 3 5\n" +
            "3 2 4\n" +
            "3 4 8\n" +
            "2 4 3\n" +
            "4 5 2\n" +
            "5 6 7\n" +
            "2 6 30\n" +
            "1 6\n";

        System.out.println("=== INPUT ===");
        System.out.print(input);
        System.out.println("=== OUTPUT ===");

        Scanner scanner = new Scanner(input);
        program1.solve(scanner);
        scanner.close();

        System.out.println("=============");
        System.out.println("Expected: Minimum cost: 12");

        System.out.println("\n=== TEST 2 (no path) ===");
        String input2 =
            "4 2 0\n" +
            "1 2 5\n" +
            "3 4 3\n" +
            "1 4\n";

        System.out.println("INPUT:");
        System.out.print(input2);
        System.out.println("OUTPUT:");
        Scanner scanner2 = new Scanner(input2);
        program1.solve(scanner2);
        scanner2.close();
        System.out.println("Expected: Minimum cost: -1");

        System.out.println("\n=== TEST 3 (curse + portal interaction) ===");
        String input3 =
            "3 2 1\n" +
            "2 100\n" +
            "1 2 10\n" +
            "2 3 50\n" +
            "1 3\n  ";

        System.out.println("INPUT:");
        System.out.print(input3);
        System.out.println("OUTPUT:");
        Scanner scanner3 = new Scanner(input3);
        program1.solve(scanner3);
        scanner3.close();
        System.out.println("Expected: Minimum cost: 60");
        System.out.println("(Portal at 1: 1->2 free=0, city 2 cursed, leaving pays 50+100=150. Total=150)");
        System.out.println("(No portal: 10+(50+100)=160)");
        System.out.println("(Portal at 2: 10 + curse100 applied + road 0 = 10+100=110. Wait, curse is added to leave cost.)");
        System.out.println(" -> No portal: 1->2(10), leave 2 cursed: 2->3(50+100=150). Total=160.");
        System.out.println(" -> Portal@1: 1->2(0), leave 2 cursed (portal used): 2->3(50). Total=50+0=50.");
        System.out.println(" -> Portal@2: 1->2(10), leave cursed 2 via portal: road=0, curse still=100. Total=10+0+100=110.");
        System.out.println("Best = portal@1 -> total = 0+50 = 50.");
        System.out.println("Expected corrected: Minimum cost: 50");
    }
}
