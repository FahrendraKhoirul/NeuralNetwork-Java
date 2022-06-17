/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class main {

    public static void main(String[] args) throws IOException {
        NeuralNetwork nn = new NeuralNetwork(5, 4, 1);
        double input[][] = {{1, 1}, {0, 1}, {0, 0}, {1, 0}};
        double output[][] = {{0}, {1}, {0}, {1}};

//================================ read csv ====================================
        String filename = "d:Data Fahrendra\\Tugas RENDRA\\UIN Malang\\Tugas\\Semester 4\\Artificial Intelligence\\Stroke Dataset\\healthcare-dataset-stroke-data.csv";
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }

//        for(int i=0; i<records.size(); i++){
//            System.out.println(records.get(i).toString());
//        }
        int totaltraining = 500; //3577
        int totaltest = (records.size() - 1) * 30 / 100; //1533

        double[][] inputTraining_dataset = new double[totaltraining][5];
        double[][] outputTraining_dataset = new double[totaltraining][1];
        double[][] inputTest_dataset = new double[totaltest][5];
        double[][] outputTest_dataset = new double[totaltest][1];

        for (int i = 0; i < totaltraining; i++) {
            int j = i + 1;
            inputTraining_dataset[i][0] = "Male".equals(records.get(j).get(1)) ? 1.0 : 0.0;    //gender
            inputTraining_dataset[i][1] = Double.parseDouble(records.get(j).get(2));      //age
            inputTraining_dataset[i][2] = Double.parseDouble(records.get(j).get(3));      //hypertension
            inputTraining_dataset[i][3] = "Yes".equals(records.get(j).get(4)) ? 1.0 : 0.0;     //heart_disease
            inputTraining_dataset[i][4] = Double.parseDouble(records.get(j).get(8));      //avg_glucose_level

            outputTraining_dataset[i][0] = Double.parseDouble(records.get(j).get(11));
        }

        for (int i = 0; i < totaltest; i++) {
            int j = i + totaltraining;
            inputTest_dataset[i][0] = "Male".equals(records.get(j).get(1)) ? 1.0 : 0.0;    //gender
            inputTest_dataset[i][1] = Double.parseDouble(records.get(j).get(2));      //age
            inputTest_dataset[i][2] = Double.parseDouble(records.get(j).get(3));      //hypertension
            inputTest_dataset[i][3] = "Yes".equals(records.get(j).get(4)) ? 1.0 : 0.0;     //heart_disease
            inputTest_dataset[i][4] = Double.parseDouble(records.get(j).get(8));      //avg_glucose_level

            outputTest_dataset[i][0] = Double.parseDouble(records.get(j).get(11));
        }

//        for(int i=0; i<outputTraining_dataset.length; i++){
//            System.out.print(inputTraining_dataset[i][0] + " ");
//            System.out.print(inputTraining_dataset[i][1] + " ");
//            System.out.print(inputTraining_dataset[i][2] + " ");
//            System.out.print(inputTraining_dataset[i][3] + " ");
//            System.out.println(inputTraining_dataset[i][4] + " ");
//        }
//=========================== Train for Dataset ================================
        for (int i = 0; i < inputTraining_dataset.length; i++) {
            double eror = nn.train(inputTraining_dataset[i], outputTraining_dataset[i]);
            System.out.println(eror);
        }

//============================= Test Dataset ===================================
        System.out.println("======= TESTING =======");
        int skor = 0;
        int total = 0;
        for(int i=0; i<inputTraining_dataset.length ; i++){
            double[] output_forward = nn.feedforward(inputTraining_dataset[i]);
            System.out.println(output_forward[0]);
            double outSementara = 0.0;
            if(output_forward[0] >= 0.5){
                outSementara = 1.0;
            }else{
                outSementara = 0.0;
            }
            
            if(outSementara == outputTraining_dataset[i][0]){
                skor++;
                total++;
            }else{
                total++;
                
            }
        }
        double akurasi = Double.valueOf(skor) / Double.valueOf(total) * 100;
        System.out.println("========== HASIL ============");
        System.out.println("| Skor Benar       : " + skor);
        System.out.println("| Total Data test  : " + total);
        System.out.println("|");
        System.out.println("| Akurasi AI       : " + akurasi + "%");
        System.out.println("=============================");
//===XOR        
//        Random rand = new Random();
//        for (int i = 0; i < 5000; i++) {
//            int rand_num = rand.nextInt(4);
//            double eror = nn.train(input[rand_num], output[rand_num]);
//            System.out.println(eror);
//        }
//
//        for(int k=0;  k<1; k++){
//            for(int i=0;  i<4; i++){
//                nn.train(input[i], output[i]);
//            }
//        }
//        
//System.out.println("OUTPUT");
//         double[] db = nn.feedforward(input[0]);
        //System.out.println(db[0]);
//        System.out.println("OUTPUT");
//        for (int i = 0; i < 4; i++) {
//            double[] db = nn.feedforward(input[i]);
//            System.out.println(db[0]);
//        }
        //nn.train(input[0], output[0]);
//       Matrix test = new Matrix(5,2);
//       test.randomize();
//       test.printMatrix();
//       Matrix test2 = new Matrix(5,1);
//       test2.randomize();
//       test2.printMatrix();
//       
//       test.mapAdd(test2);
//       test.printMatrix();
//        
    }

}
