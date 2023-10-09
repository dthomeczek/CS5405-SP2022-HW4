//HW04 Demo
package code;
/*
 * To change this license header, choose License Headers 
in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.scene.layout.*;//Pane, VBox
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//actions items
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Devin Thomeczek
 */
public class Demo extends 
Application {
    Pane root;
    @Override
    public void start(Stage stage) {
        root = new VBox();
        createTextField();
        Scene scene = new Scene(root,400,400);
        stage.setScene(scene);
        stage.show();
        //Optional
        stage.setTitle("Demo by Devin Thomeczek");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    /** declare Label and Text variables */
    Label name,prompt,point1,point2,point3,point4,quad;
    TextField tField;
    
    /** Create label */
    public void createTextField()
    {
        name = new Label();
        name.setText("By: Devin Thomeczek");
        name.setTextAlignment(TextAlignment.CENTER);
        
        prompt = new Label();
        prompt.setText("Enter 8 values separated by spaces to find the area of the quadrilateral:");
        prompt.setTextAlignment(TextAlignment.CENTER);
    
        point1 = new Label();
        point1.setText("Point 1: ( , )\n");
        point1.setTextAlignment(TextAlignment.CENTER);

        point2 = new Label();
        point2.setText("Point 2: ( , )\n");
        point2.setTextAlignment(TextAlignment.CENTER);

        point3 = new Label();
        point3.setText("Point 3: ( , )\n");
        point3.setTextAlignment(TextAlignment.CENTER);

        point4 = new Label();
        point4.setText("Point 4: ( , )\n");
        point4.setTextAlignment(TextAlignment.CENTER);

        quad = new Label();
        quad.setText("Area of the quadrilateral is: ");
        quad.setTextAlignment(TextAlignment.CENTER);
        
        //tField = new TextField("Chaman L. Sabharwal", 20);
        tField = new TextField("1 2 3 4 5 6 7 8");
        tField.setOnAction(new TextFieldHandler());
        tField.setMaxWidth(250);
        root.getChildren().addAll(name,prompt,tField,point1,point2,point3,point4,quad);
    }

    public class TextFieldHandler implements 
    EventHandler<ActionEvent>{
        public void handle(ActionEvent e)
        {
            String str = tField.getText();
            String delimiter = " ";
            String[] nums_str;
            double[] nums = new double[8];
            nums_str= str.split(delimiter);

            for (int i = 0; i < nums_str.length; i++) {
                nums[i] = Double.parseDouble(nums_str[i]);
            }
            
            // Arrays for x values and y values
            double[] x = new double[4];
            double[] y = new double[4];
            
            // j and k are counters
            int j = 0;
            int k = 0;
            for (int i = 0; i < 8; i++) {
                if ((i % 2) == 0) {
                    x[j] = nums[i];
                    j+= 1;
                }
                if ((i % 2) == 1) {
                    y[k] = nums[i];
                    k+= 1;
                }
            }
    
            // Sides of the quadrilateral
            double ab = Math.sqrt((x[1]-x[0])*(x[1]-x[0]) + (y[1]-y[0])*(y[1]-y[0]));
            double bc = Math.sqrt((x[2]-x[1])*(x[2]-x[1]) + (y[2]-y[1])*(y[2]-y[1]));
            double cd = Math.sqrt((x[3]-x[2])*(x[3]-x[2]) + (y[3]-y[2])*(y[3]-y[2]));
            double da = Math.sqrt((x[0]-x[3])*(x[0]-x[3]) + (y[0]-y[3])*(y[0]-y[3]));
    
            // Used Brahmagupta formula for calculation of variables, and cross
            // referenced my output with the same test inputs at
            // https://calculator.swiftutors.com/brahmagupta-formula-calculator.html
            double perimeter = ab + bc + cd + da;
            double semiperimeter = perimeter/2;
            double area = Math.sqrt((semiperimeter-ab)*(semiperimeter-bc)*(semiperimeter-cd)*(semiperimeter-da));


            tField.setText("");  // clear data entry field
            point1.setText("Point 1: (" + nums[0] + ", " + nums[1] + ")\n");
            point2.setText("Point 2: (" + nums[2] + ", " + nums[3] + ")\n");
            point3.setText("Point 3: (" + nums[4] + ", " + nums[5] + ")\n");
            point4.setText("Point 4: (" + nums[6] + ", " + nums[7] + ")\n");
            String quad_area=str.format("Area of the quadrilateral is: " + area + "\n");
            quad.setText(quad_area);
        }
    }
}