package ua.kpi.fift.service;

import java.util.ArrayList;

public interface MainService {

    ArrayList<String> generateMatrix(int countOfTools, int countOfBarriers);
    ArrayList<String> createMatrix();
    String solvabilityСheck();
    String greedyAlgorithm();
    String geneticAlgorithm();
    String beeAlgorithm();
}
