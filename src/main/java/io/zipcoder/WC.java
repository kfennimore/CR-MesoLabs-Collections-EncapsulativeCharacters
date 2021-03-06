package io.zipcoder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class WC {

    private Iterator<String> si;
    private Map<String, Integer> listOfWords = new LinkedHashMap<>();


    public static void main(String[] args) {
        WC wc = new WC("/Users/karoushafennimore/Dev/EncapsulativeCharacters/src/main/resources/WarPeace.txt");
        wc.printMap();
    }

    public WC(String fileName) {
        try {
            this.si = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " Does Not Exist");
            System.exit(-1);
        }
    }

    public WC(Iterator<String> si) {

        this.si = si;
    }

//
//    public int wordCount(){
//    int count = 0;
//
//    while(si.hasNext()) {
//        String[] words = si.next().toLowerCase().split(" ");
//        count += words.length;
//    }
//    return count;
//
//    }


    public Map<String, Integer> uniqueWordCount() {

        while (si.hasNext()) {

            String word = si.next().toLowerCase().replaceAll("[^a-z]", "" );

                if (listOfWords.containsKey(word)) {
                    listOfWords.put(word, listOfWords.get(word) +1);
                } else {
                    listOfWords.put(word, 1);
                }
        }
        return listOfWords;
    }


    public Map<String, Integer> returnValueDesc() {
        Map<String, Integer> sorted =
                uniqueWordCount().entrySet().stream()
                        .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
                        .collect(toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sorted;
    }

    public Map<String, Integer> getMap(){
        return this.listOfWords;
    }

    public String printMap(){
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> words: returnValueDesc().entrySet()) {
            sb.append("Word: " + words.getKey() + "\n" + "Appears: " + words.getValue() + " times \n" + "_____________\n");
            count += getMap().get(words.getKey());
        }
        StringBuilder sb1 = new StringBuilder();

        sb1.append("\nTotal Word Count : " + count + "\n\n" + "~~~~~~~~~~~~~\n\n");
        sb1.append(sb);
        System.out.println(sb1.toString());
        return sb1.toString();
    }
}
