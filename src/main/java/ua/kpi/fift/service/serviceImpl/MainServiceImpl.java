package ua.kpi.fift.service.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.kpi.fift.service.MainService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class MainServiceImpl implements MainService {

    private int[][] matrix;
    private ArrayList<String> copyOfMatrix;
    private String answer;
    private int[][] geneticMatrix;

    @Value("${file_path}")
    private String filePath;


    @Override
    public ArrayList<String> generateMatrix(int countOfTools, int countOfBarriers) {
        matrix = new int[countOfTools][countOfBarriers];
        copyOfMatrix = new ArrayList<>();

        for (int i = 0; i < countOfTools; i++){
            for (int j = 0; j < countOfBarriers; j++){
                matrix[i][j] = (int) (Math.random()*2);
            }
        }

        for (int i = 0; i < countOfTools; i++){
            String row = "";
            for (int j = 0; j < countOfBarriers; j++){
                row += matrix[i][j] + " ";
            }
            copyOfMatrix.add(row);
        }

        return copyOfMatrix;
    }

    @Override
    public ArrayList<String> createMatrix() {
        copyOfMatrix = new ArrayList<>();

        int counter = 0;
        String raw = "";

        Scanner in = null;
        try {
            in = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNextLine()){
            if (counter == 0){
                raw = in.nextLine();
                String[] numbers = raw.split(" ");
                matrix = new int[Integer.parseInt(numbers[0])][Integer.parseInt(numbers[1])];
            } else {
                raw = in.nextLine();
                copyOfMatrix.add(raw);
                String[] numberOfMatrix = raw.split(" ");
                for (int i = 0; i < numberOfMatrix.length; i++){
                    matrix[counter - 1][i] = Integer.parseInt(numberOfMatrix[i]);
                }
            }

            counter++;
        }


        return copyOfMatrix;
    }

    @Override
    public String solvabilityСheck() {
        int counter = 0;
        for (int i = 0; i < matrix[0].length; i++){
            for (int j = 0; j < matrix.length; j++) {
                counter += matrix[j][i];
            }
            if (counter == 0){
                return "Task has not solutions!!!";
            } else {
                counter = 0;
            }
        }
        return null;
    }

    @Override
    public String greedyAlgorithm() {

        int[] record = new int[matrix[0].length];
        int counter = 0;
        int counter2 = 0;

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 1){
                    counter++;
                }
            }
            if (counter > counter2){
                counter2 = counter;
                record = Arrays.copyOf(matrix[i], matrix[0].length);
                answer = "Take tool " + (i + 1) + " ";
            }
            counter = 0;
        }

        findOtherTools(record);

        return answer;
    }

    @Override
    public String geneticAlgorithm() {
        generateAnotherMatrix(matrix.length);
        ArrayList<int[]> solutions = new ArrayList<>();
        do {
            int gen = (int) (Math.random() * (matrix.length + 1));

            int[] firstParent = Arrays.copyOf(geneticMatrix[(int) (Math.random() * 20)], matrix.length);
            int[] secondParent = Arrays.copyOf(geneticMatrix[(int) (Math.random() * 20)], matrix.length);
            int[] child = new int[matrix.length];

            for (int i = 0; i < matrix.length; i++){
                if (i <= gen){
                    child[i] = firstParent[i];
                } else {
                    child[i] = secondParent[i];
                }
            }

            List<Integer> tools = new ArrayList<>();

            for (int i = 0; i < child.length; i++){
                if (child[i] == 1) {
                    tools.add(i);
                }
            }

            int counter = 0;

            for (int i = 0; i < matrix[0].length; i++){
                for (Integer position : tools) {
                    counter += matrix[position][i];
                }
                if (counter == 0) {
                    break;
                } else {
                    counter = 0;
                }
                if (i == matrix[0].length - 1){
                    solutions.add(child);
                }
            }

        } while (solutions.size() < 10);

        int record = 0;
        int record2 = Integer.MAX_VALUE;
        int[] recordVector = new int[matrix.length];

        for (int[] solution : solutions) {
            for (int i = 0; i < solution.length; i++){
                if (solution[i] == 1){
                    record++;
                }
            }
            if (record < record2){
                record2 = record;
                recordVector = Arrays.copyOf(solution, solution.length);
            }
            record = 0;
        }

        answer = "";

        for (int i = 0; i < recordVector.length; i++){
            if (recordVector[i] == 1){
                answer += "take tool " + (i + 1) + ", ";
            }
        }


        return answer;
    }

    @Override
    public String beeAlgorithm() {
        ArrayList<int[]> solutions = new ArrayList<>();

        do {
            generateAnotherMatrix(matrix.length);
            List<int[]> tools = new ArrayList<>();
            int[] change;

            for (int i = 0; i < geneticMatrix.length; i++) {
                for (int j = 0; j < geneticMatrix[0].length; j++) {
                    if (geneticMatrix[i][j] == 0) {
                        change = Arrays.copyOf(geneticMatrix[i], geneticMatrix[0].length);
                        change[j] = 1;
                        tools.add(change);
                    }
                }
            }

            for (int[] tool : tools) {
                List<Integer> positions = new ArrayList<>();
                for (int i = 0; i < tool.length; i++) {
                    if (tool[i] == 1) {
                        positions.add(i);
                    }
                }

                int counter = 0;

                for (int i = 0; i < matrix[0].length; i++) {
                    for (Integer position : positions) {
                        counter += matrix[position][i];
                    }
                    if (counter == 0) {
                        break;
                    } else {
                        counter = 0;
                    }
                    if (i == matrix[0].length - 1) {
                        solutions.add(tool);
                    }
                }
            }
        } while (solutions.size() < 10);

        int record = 0;
        int record2 = Integer.MAX_VALUE;
        int[] recordVector = new int[matrix.length];

        for (int[] solution : solutions) {
            for (int i = 0; i < solution.length; i++){
                if (solution[i] == 1){
                    record++;
                }
            }
            if (record < record2){
                record2 = record;
                recordVector = Arrays.copyOf(solution, solution.length);
            }
            record = 0;
        }

        answer = "";

        for (int i = 0; i < recordVector.length; i++){
            if (recordVector[i] == 1){
                answer += "take tool " + (i + 1) + ", ";
            }
        }

        return answer;
    }

    private void findOtherTools(int[] record){
        int counter = 0;
        int counter2 = 0;

        int position = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (record[j] == 0 && matrix[i][j] != 0){
                    counter++;
                }
                if (j == matrix[0].length - 1 && counter > counter2){
                    counter2 = counter;
                    /*record[j] = matrix[i][j];*/
                    position = i;

                    /*answer += ", tool " + (position + 1) + " ";*/
                }
            }
            counter = 0;
        }

        for (int i = 0; i < record.length; i++){
            if (record[i] == 0 && matrix[position][i] != 0){
                record[i] = matrix[position][i];
            }
        }



        answer += ", tool " + (position + 1) + " ";

        int check = 0;
        for (int i = 0; i < record.length; i++){
            check += record[i];
        }
        if (check != record.length){
            findOtherTools(record);
        }
    }

    private void generateAnotherMatrix(int lenght){
        geneticMatrix = new int[20][lenght];

        for (int i = 0; i < geneticMatrix.length; i++){
            for (int j = 0; j < geneticMatrix[0].length; j++){
                geneticMatrix[i][j] = (int) (Math.random()*2);
            }
        }

    }
}
