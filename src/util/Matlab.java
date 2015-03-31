package util;

import main.RegelStrecke;
import matlabcontrol.*;

public class Matlab {
	private static MatlabProxy proxy = null;

	public static MatlabProxy getProxy() {
		if (proxy == null) {
			MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
					.setHidden(true).build();
			MatlabProxyFactory factory = new MatlabProxyFactory(options);
			try {
				proxy = factory.getProxy();
				String matlabDir = System.getProperty("user.dir") + "\\matlab";
				proxy.eval("addpath \'" + matlabDir + "\'");
			} catch (MatlabConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MatlabInvocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return proxy;
	}

	public static void closeProxy() {
		if (proxy != null) {
			try {
				proxy.exit();
			} catch (MatlabInvocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			proxy = null;
		}
	}

	public static double[] calcSani(RegelStrecke rs) {
		double[] output = {};

		MatlabProxy matlabProxy = getProxy();

		String saniArgs = "" + rs.getTu() + "," + rs.getTg();

		try {
			output = (double[]) matlabProxy.returningEval("p2_sani(" + saniArgs
					+ ")", 2)[1];

		} catch (MatlabInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

	public static double[] calcStep(String poly) {
		// Create a proxy, which we will use to control MATLAB
		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
				.setHidden(true).build();
		MatlabProxyFactory factory = new MatlabProxyFactory(options);

		// Display 'hello world' just like when using the demo
		double[] output = {};
		try {
			MatlabProxy matlabProxy = factory.getProxy();
			output = (double[]) matlabProxy.returningEval("step(" + poly + ")",
					1)[0];

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
