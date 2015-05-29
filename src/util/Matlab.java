package util;

import matlabcontrol.*;
import model.RegelGlied;
import model.RegelStrecke;
import model.TransferFunction;

public class Matlab {
	private static MatlabProxy proxy = null;
	private static boolean mocked = false;

	public synchronized static MatlabProxy getProxy() {
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

	public synchronized static void setMocked(boolean mocked){
		Matlab.mocked = mocked;
	}
	
	public synchronized static void closeProxy() {
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

	public static double[][] calcStep(RegelGlied rg) {
		TransferFunction tf = rg.getTranferFunction();
		double[] z = tf.getZaehler().getCoeff();
		double[] n = tf.getNenner().getCoeff();
		double[][] output = new double[2][];
		MatlabProxy matlabProxy = getProxy();
		if(matlabProxy == null){
			return output;
		}
		
		try {
			matlabProxy.setVariable("n", n);
			matlabProxy.setVariable("z", z);
			matlabProxy.eval("n = fliplr(n);");
			matlabProxy.eval("z = fliplr(z);");
			matlabProxy.eval("sys = tf(z, n);");
			matlabProxy.eval("[y, t] = step(sys);");
			output[0] =  (double[]) matlabProxy.getVariable("y");
			output[1] =  (double[]) matlabProxy.getVariable("t");
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
		}
		return output;
	}
}
