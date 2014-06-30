/*******************************************************************************
 * Copyright (c) 2013 University of Bergamo - Italy
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Paolo Vavassori - initial API and implementation
 *   Angelo Gargantini - utils and architecture
 ******************************************************************************/
package citlab.cvsexporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import citlab.testsuite.TestSuite;
import citlab.cvsexporter.generator.ToCSV;
import citlab.core.ext.ICitLabTestSuiteExporter;

public class CVSExporter extends ICitLabTestSuiteExporter {

	@Override
	public void generateOutput(TestSuite input, String fileName) { 
		
		ToCSV exporter = new ToCSV();
		File file = new File(fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			System.out.println(exporter.toCSVcode(input).toString());
			fw.append(exporter.toCSVcode(input));
				fw.close();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
}
