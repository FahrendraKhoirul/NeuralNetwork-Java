
package neuralnetworkapp;

import java.text.DecimalFormat;
import java.util.function.Function;


public class Matrix {
    int rows;
    int cols;
    double[][] data;
    
    public Matrix(int rows, int cols) {
        
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                data[i][j] =  0;
            }
        }
    }
    
    void mapSigmoid(){
        //apply a function to every element of matrix
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                double val = data[i][j];
                this.data[i][j] =  1/(1 + Math.exp(-this.data[i][j]));
                
            }
        }
    }
    
    void mapdSigmoid(){
        //apply a function to every element of matrix
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                double val = data[i][j];
                double sigmoid = 1/(1 + Math.exp(-this.data[i][j]));
                this.data[i][j] =   sigmoid * (1 - sigmoid);
            }
        }
    }
    
    void mapAdd(Matrix n){
        //apply a function to every element of matrix
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                double val = data[i][j];
                this.data[i][j] += n.data[i][0];
                
            }
        }
    }
    
    
    void mapSquare(){
        //apply a function to every element of matrix
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                double val = data[i][j];
                this.data[i][j] = this.data[i][j]*this.data[i][j]   ;
            }
        }
    }
    
    static Matrix mapdSigmoid(Matrix m){
        //apply a function to every element of matrix
        for(int i=0; i<m.rows; i++){
            for(int j=0; j<m.cols; j++){
                double val = m.data[i][j];
                double sigmoid = 1/(1 + Math.exp(-m.data[i][j]));
                m.data[i][j] =   sigmoid * (1 - sigmoid);
            }
        }
        return m;
    }
    
    static Matrix fromArray(double[] arr){
        Matrix m = new Matrix(arr.length, 1);
        for(int i=0; i<arr.length; i++){
            m.data[i][0] = arr[i];
        }
        //System.out.println("== fromArray ==");
        //m.printMatrix();
        return m;
    }
    
    double[] toArray(Matrix m){
        double[] a = new double[m.rows];
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                a[i] = this.data[i][j];
            }
        }
        return a;
    }
    
    void multiply(double n){
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                data[i][j] *=  n;
            }
        }
    }
    

    
    static Matrix multiply(Matrix a, Matrix b){
        if(a.cols != b.rows){
            System.out.print("Cols A harus sama dengan Rows B.");
            return null;
        }else{
            Matrix result = new Matrix(a.rows, b.cols);
            for(int i=0; i<result.rows; i++) {
                for(int j=0; j<result.cols; j++) {
                    for (int k=0; k<a.cols; k++){
                        result.data[i][j] += a.data[i][k] * b.data[k][j];
                    }
                }
            }
            return result;
        }
    }
    
    static Matrix substract(Matrix a, Matrix b){
        Matrix result = new Matrix(a.rows, b.cols);
            for(int i=0; i<result.rows; i++) {
                for(int j=0; j<result.cols; j++) {
                        result.data[i][j] = a.data[i][j] - b.data[i][j];
                }
            }
            return result;
    }
    
    void add(double n){
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                data[i][j] +=  n;
            }
        }
    }
    
    void add(Matrix n){
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                data[i][j] +=  n.data[i][j];
            }
        }
    }
    
    Matrix transpose(){
        Matrix result = new Matrix(this.cols, this.rows);
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                result.data[j][i] =  this.data[i][j];
            }
        }
        return result;
    }
    
    static Matrix transpose(Matrix m){
        Matrix result = new Matrix(m.cols, m.rows);
        for(int i=0; i<m.rows; i++){
            for(int j=0; j<m.cols; j++){
                result.data[j][i] =  m.data[i][j];
            }
        }
        return result;
    }
    
    void randomize(){
        DecimalFormat decim = new DecimalFormat("0.00");
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                data[i][j] =  Double.parseDouble(decim.format(Math.random()));
            }
        }
    }
    
    void fill1(){
        
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.cols; j++){
                data[i][j] =  1.0;
            }
        }
    }
    
    void printMatrix(){
        for (int i = 0; i < data.length; i++) { //this equals to the row in our matrix.
         for (int j = 0; j < data[i].length; j++) { //this equals to the column in each row.
            System.out.print(data[i][j] + " ");
         }
         System.out.println(); //change line on console as row comes to end in the matrix.
      }
    }
    
}
