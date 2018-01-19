package com.maciaszczykm.katas;

import java.util.*;

public class ClothesOrder {

  private Map<String, Set<String>> graph = new HashMap<>();
  private Set<String> values = new HashSet<>();

  ClothesOrder(String input) {
    Scanner scanner = new Scanner(input);
    while(scanner.hasNextLine()) {
      String[] nodes = scanner.nextLine().split(" ");
      values.add(nodes[0]);
      values.add(nodes[1]);

      if (graph.containsKey(nodes[1])) {
        Set targetNodes = graph.get(nodes[1]);
        targetNodes.add(nodes[0]);
        graph.put(nodes[1], targetNodes);
      } else {
        Set targetNodes = new HashSet<String>();
        targetNodes.add(nodes[0]);
        graph.put(nodes[1], targetNodes);
      }
    }

    printGraph();
  }

  public int howManyClothesBefore(String vertex, Set<String> visited) {
    int counter = 0;

    if(!visited.isEmpty()) {
      System.out.println(vertex);
      counter++;
    }

    visited.add(vertex);
    Set<String> targetNodes = graph.get(vertex);

    if (targetNodes != null) {
      for (String targetNode : targetNodes) {
        if(!visited.contains(targetNode)) {
          counter += howManyClothesBefore(targetNode, visited);

        }
      }
    }

    return counter;
  }

  private void printGraph() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Set<String>> stringSetEntry : graph.entrySet()) {
      sb.append(stringSetEntry.getKey()).append(" => ");
      Set targetNodes = stringSetEntry.getValue();
      for (Object targetNode : targetNodes) {
        sb.append(targetNode).append(", ");
      }
      sb.delete(sb.length() - 2, sb.length() - 1);
      sb.append("\n");
    }
    System.out.println(sb.toString());
  }

  // TODO: Fix it.
  public List<String> whatIsTheCorrectOrder(){
    List<String> result = new ArrayList<>();

    while (result.size() != values.size()) {
      for (String value : values) {
        if (!graph.keySet().contains(value) || graph.get(value).isEmpty()) {
          if (!result.contains(value)) {
            result.add(value);
          }
        }
      }

      for (Map.Entry<String, Set<String>> stringSetEntry : graph.entrySet()) {
        Set<String> requiredClothes = stringSetEntry.getValue();
        requiredClothes.removeAll(result);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    ClothesOrder clothesOrder = new ClothesOrder("Pants Belt\nBelt Pants\nShirt Jacket\n" +
            "Shirt Tie\nPants Jacket\nPants Shoes\nJacket Shoes\nJacket Coat");
    System.out.println("How many things before Jacket?");
    System.out.println(clothesOrder.howManyClothesBefore("Jacket", new HashSet<>()));
    System.out.println("\nWhat is the correct order of dressing up?");
    System.out.println(clothesOrder.whatIsTheCorrectOrder());
  }
}