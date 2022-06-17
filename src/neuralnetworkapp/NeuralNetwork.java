package neuralnetworkapp;

public class NeuralNetwork {

    int input_nodes;
    int hidden_nodes;
    int output_nodes;
    Matrix weights_ih;
    Matrix weights_ho;
    Matrix bias_h;
    Matrix bias_o;
    double learning_rate;
    double error;

    public NeuralNetwork(int numI, int numH, int numO) {
        this.input_nodes = numI;
        this.hidden_nodes = numH;
        this.output_nodes = numO;

        this.weights_ih = new Matrix(this.hidden_nodes, this.input_nodes);
        this.weights_ho = new Matrix(this.output_nodes, this.hidden_nodes);
        this.weights_ih.randomize();
        this.weights_ho.randomize();
        this.bias_h = new Matrix(this.hidden_nodes, 1);
        this.bias_o = new Matrix(this.output_nodes, 1);
        this.bias_h.randomize();
        this.bias_o.randomize();
        this.learning_rate = 0.1;

    }

    double[] feedforward(double[] input_array) {

        //membuat hidden output
        Matrix inputs = Matrix.fromArray(input_array);
        Matrix hidden = Matrix.multiply(this.weights_ih, inputs);
        hidden.add(this.bias_h);
        //Activation function
        hidden.mapSigmoid();

        //membuat output
        Matrix output = Matrix.multiply(this.weights_ho, hidden);
        output.add(this.bias_o);
        output.mapSigmoid();
        
//        double target = target_array[0];
//        double outputfix;
//        if(output.toArray(output)[0] >= 0.5){
//            outputfix = 1.0;
//        }else{
//            outputfix = 0.0;
//        }
//        
//        if(outputfix == target){
//            
//        }
//        System.out.println("input");
//        inputs.printMatrix();
//        System.out.println("weight_ih");
//        weights_ih.printMatrix();
//        System.out.println("bias_h");
//        bias_h.printMatrix();
//        System.out.println("hidden");
//        hidden.printMatrix();
//        System.out.println("bias_o");
//        bias_o.printMatrix();
//        System.out.println("weight_ho");
//        weights_ho.printMatrix();
//        System.out.println("output");
//        output.printMatrix();
        //mengembalikan nilai
        return output.toArray(output);
        
    }

    double train(double[] inputs_array, double[] targets_array) {

        //============== FEEDFORWARD ==============//
        //membuat hidden output
        Matrix inputs = Matrix.fromArray(inputs_array);
        Matrix hidden = Matrix.multiply(this.weights_ih, inputs);
        hidden.add(this.bias_h);
        //Activation function
        hidden.mapSigmoid();

        //membuat output
        Matrix outputs = Matrix.multiply(this.weights_ho, hidden);
        outputs.add(this.bias_o);
        outputs.mapSigmoid();
        

        //convert target to singgle var
        double target = targets_array[0];

        //============== BACKPROPAGATION ==============//
//         //menentukan eror total -> 0.5(target-output)^2
//         error =  1 / (double) hidden_nodes * Math.pow(target - output.data[0][0], 2); //SE
//         
         //update bias o dan h
         
//         this.bias_o.multiply(this.learning_rate);
//         this.bias_o.multiply(error);
//         this.bias_o.mapdSigmoid();
//         
//         this.bias_h.multiply(this.learning_rate);
//         this.bias_h.multiply(error);
//         this.bias_h.mapdSigmoid();
         
//         
//        
//         
//         //update weight ho dan ih
//Rumus pertama
//          error = target - outputs.data[0][0];
//         this.weights_ho.multiply(error);
//         this.weights_ho.mapAdd(this.bias_o);
//         this.weights_ih.multiply(error);
//         this.weights_ih.mapAdd(this.bias_h);

////Rumus kedua
//        this.weights_ho.mapdSigmoid();
//        this.weights_ho.mapAdd(this.bias_o);
//        this.weights_ho.mapdSigmoid();
//        this.weights_ih.mapAdd(this.bias_h);
//         //return output;
//                         ===== Hidden - Output =====
        //eror = 0,5 target - output luadrat
        double error =  Math.sqrt((target - outputs.data[0][0]) * (target - outputs.data[0][0])) ;
        
        for(int i=0; i<weights_ho.rows; i++){
            for(int j=0; j<weights_ho.cols; j++){
                this.weights_ho.data[i][j] =  this.weights_ho.data[i][j] - this.learning_rate * hidden.data[j][0] 
                        * outputs.data[i][0] * (1-outputs.data[i][0]);
            }
        }
        
        for(int i=0; i<weights_ih.rows; i++){
            for(int j=0; j<weights_ih.cols; j++){
                this.weights_ih.data[i][j] =  this.weights_ih.data[i][j] - this.learning_rate * inputs.data[j][0] 
                        * hidden.data[i][0] * (1-hidden.data[i][0]);
            }
        }
        
//        inputs.printMatrix();
//        hidden.printMatrix();
//        weights_ho.printMatrix();
//        weights_ih.printMatrix();
        
//========================================================================
//        double scalar = this.learning_rate * error;
//        Matrix satuO = new Matrix(output.rows, output.cols);
//        satuO.fill1();
//        Matrix satuH = new Matrix(hidden.rows, hidden.cols);
//        satuH.fill1();
//      
//        Matrix sub1O = Matrix.substract(satuO, output);
//        Matrix weights_hot = Matrix.multiply(hidden, output.transpose());
//        Matrix weights_ho_delta = Matrix.multiply(weights_hot, sub1O);
//        weights_ho_delta.multiply(scalar);
////         weights_ho_delta.printMatrix();
////         weights_ho.printMatrix();
//        weights_ho.add(weights_ho_delta.transpose());
//        bias_o.add(this.learning_rate * error);
//        
//        Matrix sub1H = Matrix.substract(satuH, hidden);
//        Matrix weights_iht = Matrix.multiply(inputs, hidden.transpose());
//        Matrix weights_ih_delta = Matrix.multiply(weights_iht, sub1H);
//        Matrix dahlah = Matrix.multiply(weights_ih_delta, sub1H.transpose());
//        weights_ih_delta.multiply(scalar);
//        weights_ih.add(dahlah);
//         bias_h.add(this.learning_rate * error);
//========================================================================   
//         System.out.println("Hidden");
//         hidden.printMatrix();
//         output.printMatrix();
//         System.out.println("AkhirHidden");
//         //menghitung delat m  | (y=mx+b)
//         Matrix gradient_dsigmoid = Matrix.mapdSigmoid(output);
//         gradient_dsigmoid.printMatrix(); System.out.println("habis mapdsigmoid");
//         gradient_dsigmoid.multiply(output_error);
//         gradient_dsigmoid.multiply(this.learning_rate);
//         
//         //Matrix hidden_T = Matrix.transpose(hidden);
//         Matrix weights_ho_deltas = Matrix.multiply(gradient_dsigmoid, hidden.transpose());
//         
//         //update weight dan bias
//         this.weights_ho.add(weights_ho_deltas);
//         this.bias_o.add(gradient_dsigmoid);
//         
//         //         ===== Input - Hidden =====
//         //menhitung hidden erro
//         Matrix weights_ho_T = Matrix.transpose(this.weights_ho);
//         Matrix hidden_error = weights_ho_T;
//         hidden_error.multiply(output_error);
//                 hidden_error.printMatrix(); System.out.println("habis HE");
//         
//         //menghitung delat m  | (y=mx+b)
//         Matrix hidden_gradient_dsigmoid = Matrix.mapdSigmoid(hidden);
//                  hidden_gradient_dsigmoid.printMatrix(); System.out.println("habis sigmoid");
//         
//         hidden_gradient_dsigmoid.m         
//                  
//                  
//         Matrix hidden_gradient = Matrix.multiply(hidden_gradient_dsigmoid, hidden_error.transpose());
//                hidden_gradient.printMatrix(); System.out.println("habis multiply HE");
//         hidden_gradient.multiply(this.learning_rate);
//                  hidden_gradient.printMatrix(); System.out.println("habis lr");
//         
//         
//         //Matrix inputs_T = Matrix.transpose(inputs);
//         Matrix weights_ih_delta = Matrix.multiply(hidden_gradient, inputs.transpose());
//                  weights_ih_delta.printMatrix();
//         
//         
//         //update weight dan bias
//         this.weights_ih.add(weights_ih_delta);
//         this.bias_h.add(hidden_gradient); 
        return error;
    }
    
}
