package Squares;

import java.io.*;
import java.util.Arrays;

public class Squares_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//double x[] = {0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2};
		//double y[] = {1.0, 1.9, 3.2, 4.3, 4.8, 6.1, 7.2};
		//double y[] = {-2.7 , -1.29, -1.2 ,  0.72,  0.81,  1.55,  1.88};
		
		Squares_main smain = new Squares_main();
		double x[] = smain.getCSV1("csv/x.csv", 7);
		double y[] = smain.getCSV1("csv/y.csv", 7);
		
		Squares_lib slib = new Squares_lib(x, y);
		double theta[] = new double[2];
		double value[] = new double[100];
		double values[][] = new double[100][4];
		
		for (int i = 0; i < 100; i++)
		{
			theta = slib.getDx();
			System.out.println(i + ": theta[0] = " + theta[0]);
			System.out.println(i + ": theta[1] = " + theta[1]);
			System.out.println(
				i + ": Objective function = " + slib.getObject());
			value[i] = slib.getObject();
			values[i][0] = i;
			values[i][1] = value[i];
			values[i][2] = theta[0];
			values[i][3] = theta[1];
		}
		
		Graph graph = new Graph(value);
		graph.setBounds(5, 5, 655, 455);
		graph.setVisible(true);
		smain.writeCSV("csv/result.csv", values);
	}
	
	public double[] getCSV1(String path, int n) {
		double csvdata[] = new double[n];
		
		try {
			File f = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String[] data = new String[n];
			String line = br.readLine();

			data = line.split(",", 0);
			line = br.readLine();
			br.close();
			
			for (int i = 0; i < data.length; i++) {
				csvdata[i] = Double.parseDouble(data[i]);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("CSV = " + Arrays.toString(csvdata));
		return csvdata;
	}
	
	public void writeCSV(String path, double[][] data) {
		try {
			FileWriter fw = new FileWriter(path, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.print("id,objective,theta0,theta1");
			pw.println();
			for (int i = 0; i < data.length; i++) {
				for (int j  = 0; j < data[0].length; j++) {
					pw.print(data[i][j]);
					pw.print(",");
				}
				pw.println();
			}
			pw.close();
			System.out.println("Output succeeded:" + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}