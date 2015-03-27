package prototype;

import java.io.File;

import main.RegelStrecke;
import matlabcontrol.*;

public class MatlabSani {
	
	public static void main(String[] args) {
		RegelStrecke rs = new RegelStrecke(1.0, 1.0, 5.0);
		double[] output = calcSani(rs);
		for (int i = 0; i < output.length; i++) {
			System.out.println(output[i]);
		}
	}
	
	public static double[] calcSani(RegelStrecke rs) {
		double[] output = {};
		try {
			MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions
					.Builder()
					.setHidden(true)
					.build();
			MatlabProxyFactory factory = new MatlabProxyFactory(options);
			MatlabProxy proxy = factory.getProxy();
			
			String matlabDir = System.getProperty("user.dir") + "\\matlab";
			proxy.eval("addpath \'"+ matlabDir+"\'");	
			
			String saniArgs = "" 
					+ rs.getTu() + "," 
					+ rs.getTg();
			output = (double[]) proxy.returningEval(
					"p2_sani(" + saniArgs + ")", 2)[1];
			
			proxy.disconnect();

		} catch (MatlabInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatlabConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
}
