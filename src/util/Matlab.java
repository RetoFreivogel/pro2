package util;

import main.RegelStrecke;
import matlabcontrol.*;

public class Matlab {
	private static MatlabProxy proxy = null;
	private static boolean mocked = false;

	public static MatlabProxy getProxy() {
		if(mocked){
			return null;
		}
		if (proxy == null) {
			MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
					.setHidden(true).build();
			MatlabProxyFactory factory = new MatlabProxyFactory(options);
			try {
				proxy = factory.getProxy();
				String matlabDir = System.getProperty("user.dir") + "\\matlab";
				proxy.eval("addpath \'" + matlabDir + "\'");
			} catch (MatlabConnectionException e) {
				e.printStackTrace();
			} catch (MatlabInvocationException e) {
				e.printStackTrace();
			}
		}
		return proxy;
	}

	public static void setMocked(boolean mocked){
		Matlab.mocked = mocked;
	}
	
	public static void closeProxy() {
		if (proxy != null) {
			try {
				proxy.exit();
			} catch (MatlabInvocationException e) {
				e.printStackTrace();
			}
			proxy = null;
		}
	}

	public static double[] calcSani(RegelStrecke rs) {
		double[] output = new double[]{};

		MatlabProxy matlabProxy = getProxy();
		if(matlabProxy == null){
			return new double[]{1, 2, 3, 4};
		}
		
		double tu = rs.getTu();
		double tg = rs.getTg();

		try {
			output = (double[]) matlabProxy
					.returningFeval("p2_sani", 2, tu, tg)[1];

		} catch (MatlabInvocationException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static double[] calcStep(double[] n, double[] z) {
		// Create a proxy, which we will use to control MATLAB

		// Display 'hello world' just like when using the demo
		double[] output = {};
		MatlabProxy matlabProxy = getProxy();
		if(matlabProxy == null){
			return new double[]{};
		}
		
		try {
			output = (double[]) matlabProxy.returningFeval("step", 1, n, z)[0];
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
		}
		return output;
	}
}
