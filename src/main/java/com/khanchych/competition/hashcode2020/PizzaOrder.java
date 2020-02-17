package com.khanchych.competition.hashcode2020;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PizzaOrder {
    private static final String[] EXAMPLES = {"a_example.in", "b_small.in", "c_medium.in", "d_quite_big.in", "e_also_big.in"};
    private int pizzaTypes;
    private int maxSlices;
    private int[] pizzaSizes;
    private Map<Integer,Integer> memory;

    public PizzaOrder(String fileName) {
        loadDataFromFile(fileName);
    }

    public static void main(String[] args) {
        PizzaOrder pizzaOrder = new PizzaOrder("c_medium.in");
        long start = System.currentTimeMillis();
        int result = pizzaOrder.solve(0, pizzaOrder.maxSlices);
        System.out.println("Time ellapsed " + (System.currentTimeMillis() - start));
        System.out.println(result);
    }

    private int solve(int index, int maxSlices) {
        if (maxSlices < 0) {
            return 0;
        }
        if (maxSlices == 0) {
            //stop calculation, we found the best value
        }
        if (index == pizzaSizes.length - 1) {
            if (maxSlices >= pizzaSizes[pizzaSizes.length - 1]) {
                return pizzaSizes[pizzaSizes.length - 1];
            } else {
                return 0;
            }
        }

        int bestResultWithoutTaking = solve(index + 1, maxSlices);
        int bestResultWithTaking = (maxSlices - pizzaSizes[index]) < 0 ? 0 : solve(index + 1, maxSlices - pizzaSizes[index]) + pizzaSizes[index];

        return Math.max(bestResultWithoutTaking, bestResultWithTaking);
    }

    private void loadDataFromFile(String fileName) {
        ClassLoader classLoader = PizzaOrder.class.getClassLoader();

        try (Scanner scanner = new Scanner(classLoader.getResourceAsStream(fileName))) {
            maxSlices = scanner.nextInt();
            pizzaTypes = scanner.nextInt();
            pizzaSizes = new int[pizzaTypes];
            for (int i = 0; i < pizzaTypes; i++) {
                pizzaSizes[i] = scanner.nextInt();
            }
        }

        memory = new HashMap<>();
    }
}
