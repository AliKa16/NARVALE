package org.example;

import java.util.*;

public class AlgorytmDobierajacy {
    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> mapaPracownikow = new HashMap<>();
        mapaPracownikow.put("JavaScript", new ArrayList<>(List.of(6, 7, 19, 30, 45, 46, 59, 64, 88, 89, 92, 126, 166, 222, 225, 250, 275, 287)));
        mapaPracownikow.put("Django", new ArrayList<>(List.of(7, 10, 17, 19, 25, 29, 44, 54, 60, 73, 86, 88, 89, 123, 137, 142, 155, 163, 166, 218, 223, 231, 233, 235, 256, 260, 270, 274, 282, 292)));
        mapaPracownikow.put("SQL", new ArrayList<>(List.of(25, 26, 29, 37, 66, 77, 100, 117, 119, 171, 177, 182, 183, 188, 203, 224, 248, 274, 286, 299)));
        mapaPracownikow.put("Python", new ArrayList<>(List.of(12, 21, 24, 34, 42, 65, 67, 87, 91, 113, 116, 123, 133, 136, 142, 143, 156, 164, 191, 199, 205, 206, 216, 223, 231, 240)));

        ArrayList<ArrayList<Integer>> typOsobowosci = new ArrayList<>();
        typOsobowosci.add(new ArrayList<>(List.of(3, 5, 2, 7, 4, 7, 6, 1, 7, 3, 5, 5, 8, 2, 7, 7, 4, 6)));
        typOsobowosci.add(new ArrayList<>(List.of(5, 2, 3, 2, 4, 6, 8, 3, 7, 1, 8, 3, 12, 5, 8, 2, 7, 1, 8, 6, 4, 5, 7, 1, 4, 6, 1, 8, 2, 5)));
        typOsobowosci.add(new ArrayList<>(List.of(4, 3, 6, 5, 1, 3, 1, 2, 8, 7, 2, 3, 5, 4, 6, 3, 8, 1, 7, 4)));
        typOsobowosci.add(new ArrayList<>(List.of(5, 7, 2, 5, 3, 4, 2, 8, 6, 9, 3, 6, 5, 7, 3, 8, 1, 4, 5, 2, 7, 1, 8, 4, 3, 6)));

        ArrayList<Map<Integer, List<String>>> soft = new ArrayList<>();
        soft.add(new HashMap<>(Map.of(1, new ArrayList<>(List.of("empatia", "cierpliwość", "zdolności mediacyjne")))));
        soft.add(new HashMap<>(Map.of(2, new ArrayList<>(List.of("krytyczne myślenie", "uważność", "szczerość")))));
        soft.add(new HashMap<>(Map.of(3, new ArrayList<>(List.of("komunikatywność", "optymizm", "elastyczność")))));
        soft.add(new HashMap<>(Map.of(4, new ArrayList<>(List.of("analiza i refleksja", "samodyscyplina", "kreatywność")))));
        soft.add(new HashMap<>(Map.of(5, new ArrayList<>(List.of("motywacja", "budowanie relacji", "otwartość na zmiany")))));
        soft.add(new HashMap<>(Map.of(6, new ArrayList<>(List.of("decyzyjność", "przywództwo", "zarządzanie stresem")))));
        soft.add(new HashMap<>(Map.of(7, new ArrayList<>(List.of("pragmatyzm", "rozwiązywanie problemów", "odpowiedzialność")))));
        soft.add(new HashMap<>(Map.of(8, new ArrayList<>(List.of("wizjonerstwo", "zarządzanie emocjami", "empatia")))));


        Map<Integer, List<Integer>> mapaWspolpracy = new HashMap<>();
        mapaWspolpracy.put(1, List.of(2, 3, 4, 5));
        mapaWspolpracy.put(2, List.of(1, 3, 4, 5, 6));
        mapaWspolpracy.put(3, List.of(1, 2, 4, 5, 7));
        mapaWspolpracy.put(4, List.of(1, 2, 3, 5, 6, 8));
        mapaWspolpracy.put(5, List.of(1, 2, 3, 4, 7, 8));
        mapaWspolpracy.put(6, List.of(2, 4, 5, 8));
        mapaWspolpracy.put(7, List.of(3, 5, 8));
        mapaWspolpracy.put(8, List.of(4, 5, 6, 7));

        List<List<Integer>> zespoly = new ArrayList<>();

        while (true) {
            Set<Integer> wybraneTypy = new HashSet<>();
            List<Integer> zespol = new ArrayList<>();

            if (mapaPracownikow.values().stream().mapToInt(List::size).sum() < 4) {
                break;
            }

            for (String jezyk : mapaPracownikow.keySet()) {
                ArrayList<Integer> pracownicy = mapaPracownikow.get(jezyk);
                for (Integer pracownik : pracownicy) {
                    int typ = typOsobowosci.get(getJezykIndex(jezyk)).get(pracownik % typOsobowosci.get(getJezykIndex(jezyk)).size());

                    if (czyDobrzeWspolpracuja(wybraneTypy, typ, mapaWspolpracy) && zespol.size() < 4) {
                        zespol.add(pracownik);
                        wybraneTypy.add(typ);
                    }

                    if (zespol.size() == 4) {
                        break;
                    }
                }
                if (zespol.size() == 4) {
                    break;
                }
            }

            if (zespol.size() == 4) {
                zespoly.add(zespol);
                for (Integer pracownik : zespol) {
                    mapaPracownikow.forEach((jezyk, pracownicy) -> pracownicy.remove(pracownik));
                }
            } else {
                break;
            }
        }

        for (List<Integer> zespol : zespoly) {
            System.out.println("Zespół: " + zespol);
        }
    }

    private static int getJezykIndex(String jezyk) {
        return switch (jezyk) {
            case "JavaScript" -> 0;
            case "Django" -> 1;
            case "SQL" -> 2;
            case "Python" -> 3;
            default -> -1;
        };
    }

    private static boolean czyDobrzeWspolpracuja(Set<Integer> wybraneTypy, Integer nowyTyp, Map<Integer, List<Integer>> mapaWspolpracy) {
        if (!mapaWspolpracy.containsKey(nowyTyp)) {
            return false;
        }

        for (Integer typ : wybraneTypy) {
            List<Integer> wspolpracujaceTypy = mapaWspolpracy.get(typ);

            if (wspolpracujaceTypy == null || !wspolpracujaceTypy.contains(nowyTyp)) {
                return false;
            }
        }
        return true;
    }
}
