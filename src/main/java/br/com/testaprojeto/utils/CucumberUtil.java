package br.com.testaprojeto.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class CucumberUtil {

	private static final String FEATURE_FILE_PATH = "C:\\Users\\Tiago\\Desktop\\Projeto Final\\App\\cucumber\\features\\default.feature";
	private static final String RUN_CUCUMBER_PATH = "C:\\Users\\Tiago\\Desktop\\Projeto Final\\App\\cucumber\\run.bat";
	private static final String CUCUMBER_PATH = "C:\\Users\\Tiago\\Desktop\\Projeto Final\\App\\cucumber";
	
	// Escreve o conteúdo do teste no arquivo de features do cucumber.
	public static void prepareFeature(String testeCaseBody) throws IOException {
		
		FileWriter outFile = new FileWriter(FEATURE_FILE_PATH);
		PrintWriter printer = new PrintWriter(outFile);
		printer.write(testeCaseBody.toCharArray());
		
		outFile.close();
		
	}
	
	// Executa o cucumber.
	public static String executeCucumber() throws IOException, InterruptedException {

		Runtime runtime = Runtime.getRuntime();
		
		String output = "";
		Process cucumber = runtime.exec(RUN_CUCUMBER_PATH,
										null,
										new File(CUCUMBER_PATH));
		InputStream isout = cucumber.getInputStream();
		 
        int line;

        while((line=isout.read()) != -1) {
            System.out.print((char)line);
            output += (char)line;
        }

        System.out.println(output);
        
        int exitVal = cucumber.waitFor();
        System.out.println("Exited with error code "+exitVal);
        
        return output;

	}

}
