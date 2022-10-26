package ru.stazaev.site.controller;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

@Component
public class ValueHandler {

    public int getMax(List<Integer> list){
        return list.stream()
                .max(Comparator.naturalOrder())
                .get();
    }

    public int getMin(List<Integer> list){
        return list.stream()
                .min(Comparator.naturalOrder())
                .get();
    }

    public Double getMedian(List<Integer> list) {
        DoubleStream sortedNumbers = list.stream()
                .mapToDouble(v -> v)
                .sorted();
        OptionalDouble median = (
                list.size() % 2 == 0 ?
                        sortedNumbers.skip((list.size() / 2) - 1)
                                .limit(2)
                                .average() :
                        sortedNumbers.skip(list.size() / 2)
                                .findFirst()
        );
        return median.orElse(Double.NaN);
    }

    public double getAverage(List<Integer> list) {
        return list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }

    public List<List<Integer>> increasingNumbers(List<Integer> list) {
        List<List<Integer>> listOfLists = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            if (i!=list.size() && list.get(i) > list.get(i - 1)) {
                if (tempList.size() ==0) {
                    tempList.add(list.get(i - 1));
                }
                tempList.add(list.get(i));
            } else {
                if (listOfLists.isEmpty() || tempList.size() == listOfLists.get(0).size()) {
                    listOfLists.add(tempList);
                } else if (tempList.size() > listOfLists.get(0).size()) {
                    listOfLists = new ArrayList<>();
                    listOfLists.add(tempList);
                }
                tempList = new ArrayList<>();
            }
        }

        return listOfLists;
    }

    public List<List<Integer>> decreasingNumbers(List<Integer> list) {
        List<List<Integer>> listOfLists = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            if (i!=list.size() && list.get(i) < list.get(i - 1)) {
                if (tempList.size() ==0) {
                    tempList.add(list.get(i - 1));
                }
                tempList.add(list.get(i));
            } else {
                if (listOfLists.isEmpty() || tempList.size() == listOfLists.get(0).size()) {
                    listOfLists.add(tempList);
                } else if (tempList.size() > listOfLists.get(0).size()) {
                    listOfLists = new ArrayList<>();
                    listOfLists.add(tempList);
                }
                tempList = new ArrayList<>();
            }
        }
        return listOfLists;
    }


}
