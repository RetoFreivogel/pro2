package model;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class SaniApprox {
	private static final SplineInterpolator interpolator = new SplineInterpolator();
	private static final double[][] T_Tg = {
			{ 9.9925000e-01, 9.2210509e-01, 8.7273785e-01, 8.3346151e-01,
					8.0034196e-01, 7.7154203e-01, 7.4599593e-01, 7.2301817e-01,
					7.0213071e-01, 6.8298327e-01, 6.6531353e-01, 6.4891546e-01,
					6.3362536e-01, 6.1931044e-01, 6.0586021e-01, 5.9318316e-01,
					5.8120120e-01, 5.6984783e-01, 5.5906651e-01, 5.4880640e-01,
					5.3902559e-01, 5.2968442e-01, 5.2074899e-01, 5.1219075e-01,
					5.0398124e-01, 4.9609667e-01, 4.8851531e-01, 4.8121796e-01,
					4.7418637e-01, 4.6740416e-01, 4.6085644e-01, 4.5453009e-01,
					4.4841246e-01, 4.4249195e-01, 4.3675779e-01, 4.3120056e-01,
					4.2581133e-01, 4.2058169e-01, 4.1550368e-01, 4.1056980e-01,
					4.0577341e-01, 4.0110879e-01, 3.9656909e-01, 3.9214954e-01,
					3.8784472e-01, 3.8364945e-01, 3.7955930e-01, 3.7557031e-01,
					3.7167824e-01, 3.6787912e-01 },
			{ 9.9925000e-01, 9.2210723e-01, 8.7270628e-01, 8.3336161e-01,
					8.0011155e-01, 7.7109772e-01, 7.4524269e-01, 7.2183753e-01,
					7.0038382e-01, 6.8051500e-01, 6.6194819e-01, 6.4446127e-01,
					6.2787760e-01, 6.1205913e-01, 5.9689772e-01, 5.8230609e-01,
					5.6822056e-01, 5.5459028e-01, 5.4137651e-01, 5.2855067e-01,
					5.1608883e-01, 5.0397300e-01, 4.9218856e-01, 4.8072300e-01,
					4.6956675e-01, 4.5870974e-01, 4.4814465e-01, 4.3786300e-01,
					4.2785898e-01, 4.1812518e-01, 4.0865469e-01, 3.9944143e-01,
					3.9047900e-01, 3.8176096e-01, 3.7328127e-01, 3.6503331e-01,
					3.5701202e-01, 3.4921013e-01, 3.4162242e-01, 3.3424304e-01,
					3.2706570e-01, 3.2008526e-01, 3.1329564e-01, 3.0669165e-01,
					3.0026787e-01, 2.9401871e-01, 2.8793961e-01, 2.8202475e-01,
					2.7627010e-01, 2.7067035e-01 },
			{ 9.9925000e-01, 9.2210738e-01, 8.7270699e-01, 8.3336140e-01,
					8.0010825e-01, 7.7109569e-01, 7.4523228e-01, 7.2181537e-01,
					7.0034189e-01, 6.8044161e-01, 6.6182705e-01, 6.4427011e-01,
					6.2758833e-01, 6.1163658e-01, 5.9629725e-01, 5.8147765e-01,
					5.6710293e-01, 5.5311669e-01, 5.3946971e-01, 5.2612462e-01,
					5.1305223e-01, 5.0022684e-01, 4.8763057e-01, 4.7524627e-01,
					4.6306317e-01, 4.5107347e-01, 4.3927176e-01, 4.2765515e-01,
					4.1622401e-01, 4.0497846e-01, 3.9392237e-01, 3.8305806e-01,
					3.7238945e-01, 3.6192239e-01, 3.5165990e-01, 3.4160725e-01,
					3.3176753e-01, 3.2214421e-01, 3.1274120e-01, 3.0355991e-01,
					2.9460268e-01, 2.8586990e-01, 2.7736288e-01, 2.6908124e-01,
					2.6102359e-01, 2.5318927e-01, 2.4557663e-01, 2.3818294e-01,
					2.3100564e-01, 2.2404161e-01 },
			{ 9.9925000e-01, 9.2210739e-01, 8.7270702e-01, 8.3336139e-01,
					8.0010801e-01, 7.7109567e-01, 7.4523136e-01, 7.2181417e-01,
					7.0034127e-01, 6.8043873e-01, 6.6182083e-01, 6.4426037e-01,
					6.2757131e-01, 6.1160785e-01, 5.9625001e-01, 5.8140432e-01,
					5.6699146e-01, 5.5295086e-01, 5.3923051e-01, 5.2578809e-01,
					5.1258731e-01, 4.9959738e-01, 4.8679243e-01, 4.7414957e-01,
					4.6165052e-01, 4.4927863e-01, 4.3702393e-01, 4.2487588e-01,
					4.1283025e-01, 4.0088315e-01, 3.8903546e-01, 3.7729087e-01,
					3.6565494e-01, 3.5413559e-01, 3.4274374e-01, 3.3148989e-01,
					3.2038830e-01, 3.0945082e-01, 2.9869231e-01, 2.8812540e-01,
					2.7776303e-01, 2.6761855e-01, 2.5770248e-01, 2.4802489e-01,
					2.3859468e-01, 2.2941989e-01, 2.2050551e-01, 2.1185665e-01,
					2.0347617e-01, 1.9536644e-01 },
			{ 9.9925000e-01, 9.2210739e-01, 8.7270702e-01, 8.3336139e-01,
					8.0010799e-01, 7.7109567e-01, 7.4523135e-01, 7.2181402e-01,
					7.0034123e-01, 6.8043895e-01, 6.6182110e-01, 6.4426031e-01,
					6.2757055e-01, 6.1160639e-01, 5.9624715e-01, 5.8139732e-01,
					5.6698003e-01, 5.5293116e-01, 5.3919911e-01, 5.2573870e-01,
					5.1251221e-01, 4.9948570e-01, 4.8663003e-01, 4.7391789e-01,
					4.6132670e-01, 4.4883451e-01, 4.3642338e-01, 4.2407683e-01,
					4.1178370e-01, 3.9953229e-01, 3.8731642e-01, 3.7513193e-01,
					3.6297930e-01, 3.5086291e-01, 3.3878864e-01, 3.2676869e-01,
					3.1481717e-01, 3.0295255e-01, 2.9119433e-01, 2.7956623e-01,
					2.6809172e-01, 2.5679461e-01, 2.4569986e-01, 2.3483094e-01,
					2.2420978e-01, 2.1385786e-01, 2.0379335e-01, 1.9403150e-01,
					1.8458568e-01, 1.7546718e-01 },
			{ 9.9925000e-01, 9.2210739e-01, 8.7270702e-01, 8.3336139e-01,
					8.0010799e-01, 7.7109567e-01, 7.4523136e-01, 7.2181400e-01,
					7.0034122e-01, 6.8043899e-01, 6.6182120e-01, 6.4426032e-01,
					6.2757069e-01, 6.1160617e-01, 5.9624669e-01, 5.8139694e-01,
					5.6697807e-01, 5.5292877e-01, 5.3919458e-01, 5.2573089e-01,
					5.1249992e-01, 4.9946546e-01, 4.8659744e-01, 4.7386800e-01,
					4.6125063e-01, 4.4872117e-01, 4.3625787e-01, 4.2384100e-01,
					4.1145256e-01, 3.9907483e-01, 3.8669495e-01, 3.7430090e-01,
					3.6188459e-01, 3.4944233e-01, 3.3697308e-01, 3.2448237e-01,
					3.1197879e-01, 2.9947830e-01, 2.8700131e-01, 2.7457367e-01,
					2.6222606e-01, 2.4999204e-01, 2.3790918e-01, 2.2601509e-01,
					2.1434810e-01, 2.0294500e-01, 1.9184142e-01, 1.8106869e-01,
					1.7065459e-01, 1.6062283e-01 },
			{ 9.9925000e-01, 9.2210739e-01, 8.7270702e-01, 8.3336139e-01,
					8.0010799e-01, 7.7109567e-01, 7.4523136e-01, 7.2181399e-01,
					7.0034122e-01, 6.8043900e-01, 6.6182122e-01, 6.4426033e-01,
					6.2757073e-01, 6.1160613e-01, 5.9624658e-01, 5.8139689e-01,
					5.6697787e-01, 5.5292876e-01, 5.3919436e-01, 5.2573005e-01,
					5.1249760e-01, 4.9946167e-01, 4.8659139e-01, 4.7385722e-01,
					4.6123243e-01, 4.4869191e-01, 4.3621274e-01, 4.2377128e-01,
					4.1134637e-01, 3.9891786e-01, 3.8646602e-01, 3.7397568e-01,
					3.6142957e-01, 3.4881541e-01, 3.3612519e-01, 3.2335332e-01,
					3.1050140e-01, 2.9757634e-01, 2.8459362e-01, 2.7157506e-01,
					2.5855307e-01, 2.4556556e-01, 2.3265806e-01, 2.1988169e-01,
					2.0729157e-01, 1.9494228e-01, 1.8288957e-01, 1.7118475e-01,
					1.5987559e-01, 1.4900233e-01 } };
	private static final double[][] Tu_Tg = {
			{ 0.0000000e+00, 1.5702980e-02, 2.7157153e-02, 3.6319683e-02,
					4.3925294e-02, 5.0381333e-02, 5.5945698e-02, 6.0796667e-02,
					6.5062870e-02, 6.8840503e-02, 7.2205078e-02, 7.5215814e-02,
					7.7920700e-02, 8.0359160e-02, 8.2563450e-02, 8.4561064e-02,
					8.6374933e-02, 8.8024727e-02, 8.9527818e-02, 9.0898247e-02,
					9.2149774e-02, 9.3292753e-02, 9.4337027e-02, 9.5292333e-02,
					9.6165425e-02, 9.6963443e-02, 9.7692575e-02, 9.8358722e-02,
					9.8966596e-02, 9.9520596e-02, 1.0002476e-01, 1.0048314e-01,
					1.0089896e-01, 1.0127517e-01, 1.0161441e-01, 1.0191951e-01,
					1.0219294e-01, 1.0243681e-01, 1.0265300e-01, 1.0284310e-01,
					1.0300890e-01, 1.0315250e-01, 1.0327450e-01, 1.0337692e-01,
					1.0346080e-01, 1.0352698e-01, 1.0357669e-01, 1.0361126e-01,
					1.0363146e-01, 1.0363800e-01 },
			{ 0.0000000e+00, 1.6087207e-02, 2.8606725e-02, 3.9425030e-02,
					4.9204760e-02, 5.8291772e-02, 6.6890907e-02, 7.5127737e-02,
					8.3080168e-02, 9.0795417e-02, 9.8297888e-02, 1.0559622e-01,
					1.1268812e-01, 1.1956583e-01, 1.2621873e-01, 1.3263359e-01,
					1.3880068e-01, 1.4471071e-01, 1.5035695e-01, 1.5573602e-01,
					1.6084545e-01, 1.6568559e-01, 1.7025853e-01, 1.7456751e-01,
					1.7861806e-01, 1.8241510e-01, 1.8596616e-01, 1.8927758e-01,
					1.9235860e-01, 1.9521677e-01, 1.9786011e-01, 2.0029761e-01,
					2.0253780e-01, 2.0458904e-01, 2.0645998e-01, 2.0815833e-01,
					2.0969371e-01, 2.1107229e-01, 2.1230286e-01, 2.1339301e-01,
					2.1434919e-01, 2.1517930e-01, 2.1588928e-01, 2.1648612e-01,
					2.1697596e-01, 2.1736420e-01, 2.1765759e-01, 2.1786013e-01,
					2.1797865e-01, 2.1801711e-01 },
			{ 0.0000000e+00, 1.6095058e-02, 2.8666166e-02, 3.9616244e-02,
					4.9639255e-02, 5.9110495e-02, 6.8255900e-02, 7.7224432e-02,
					8.6111816e-02, 9.4980630e-02, 1.0386749e-01, 1.1278972e-01,
					1.2175061e-01, 1.3074413e-01, 1.3975647e-01, 1.4876998e-01,
					1.5776324e-01, 1.6671525e-01, 1.7560024e-01, 1.8439313e-01,
					1.9306873e-01, 2.0159914e-01, 2.0995879e-01, 2.1811887e-01,
					2.2605374e-01, 2.3373869e-01, 2.4115014e-01, 2.4826661e-01,
					2.5507055e-01, 2.6154465e-01, 2.6767775e-01, 2.7345812e-01,
					2.7887823e-01, 2.8393598e-01, 2.8862725e-01, 2.9295433e-01,
					2.9691845e-01, 3.0052425e-01, 3.0378003e-01, 3.0669185e-01,
					3.0927021e-01, 3.1152388e-01, 3.1346594e-01, 3.1510780e-01,
					3.1646026e-01, 3.1753766e-01, 3.1835347e-01, 3.1891967e-01,
					3.1924976e-01, 3.1935672e-01 },
			{ 0.0000000e+00, 1.6095218e-02, 2.8668593e-02, 3.9627952e-02,
					4.9674730e-02, 5.9194089e-02, 6.8423153e-02, 7.7524642e-02,
					8.6609183e-02, 9.5753823e-02, 1.0501264e-01, 1.1442105e-01,
					1.2399872e-01, 1.3375743e-01, 1.4370010e-01, 1.5382634e-01,
					1.6412878e-01, 1.7459924e-01, 1.8522417e-01, 1.9598706e-01,
					2.0686681e-01, 2.1783816e-01, 2.2887194e-01, 2.3993371e-01,
					2.5098598e-01, 2.6198542e-01, 2.7288967e-01, 2.8364907e-01,
					2.9421642e-01, 3.0453990e-01, 3.1457037e-01, 3.2426014e-01,
					3.3356263e-01, 3.4243456e-01, 3.5083882e-01, 3.5873923e-01,
					3.6611033e-01, 3.7292580e-01, 3.7917102e-01, 3.8483232e-01,
					3.8990318e-01, 3.9438570e-01, 3.9828194e-01, 4.0160029e-01,
					4.0435350e-01, 4.0655966e-01, 4.0823442e-01, 4.0940095e-01,
					4.1008141e-01, 4.1030270e-01 },
			{ 0.0000000e+00, 1.6095221e-02, 2.8668692e-02, 3.9628669e-02,
					4.9677626e-02, 5.9202619e-02, 6.8443663e-02, 7.7567535e-02,
					8.6690407e-02, 9.5896155e-02, 1.0524706e-01, 1.1478834e-01,
					1.2455113e-01, 1.3456048e-01, 1.4483353e-01, 1.5538456e-01,
					1.6622516e-01, 1.7736325e-01, 1.8880506e-01, 2.0055197e-01,
					2.1260223e-01, 2.2494827e-01, 2.3757776e-01, 2.5047067e-01,
					2.6360208e-01, 2.7693737e-01, 2.9043587e-01, 3.0404737e-01,
					3.1771752e-01, 3.3137933e-01, 3.4496230e-01, 3.5838778e-01,
					3.7157410e-01, 3.8443715e-01, 3.9688702e-01, 4.0884003e-01,
					4.2021233e-01, 4.3092835e-01, 4.4091579e-01, 4.5011718e-01,
					4.5848126e-01, 4.6596689e-01, 4.7254828e-01, 4.7820910e-01,
					4.8294327e-01, 4.8676077e-01, 4.8967728e-01, 4.9171520e-01,
					4.9290744e-01, 4.9329656e-01 },
			{ 0.0000000e+00, 1.6095221e-02, 2.8668696e-02, 3.9628713e-02,
					4.9677863e-02, 5.9203490e-02, 6.8446179e-02, 7.7573663e-02,
					8.6703668e-02, 9.5922300e-02, 1.0529492e-01, 1.1487081e-01,
					1.2468659e-01, 1.3477363e-01, 1.4515759e-01, 1.5586276e-01,
					1.6691098e-01, 1.7832564e-01, 1.9012626e-01, 2.0233198e-01,
					2.1496033e-01, 2.2802274e-01, 2.4152869e-01, 2.5548174e-01,
					2.6987782e-01, 2.8470529e-01, 2.9994416e-01, 3.1556456e-01,
					3.3152504e-01, 3.4776872e-01, 3.6422909e-01, 3.8082253e-01,
					3.9745244e-01, 4.1401016e-01, 4.3037141e-01, 4.4640583e-01,
					4.6197083e-01, 4.7692522e-01, 4.9112632e-01, 5.0443787e-01,
					5.1673472e-01, 5.2790347e-01, 5.3785367e-01, 5.4651077e-01,
					5.5382459e-01, 5.5976609e-01, 5.6433357e-01, 5.6754161e-01,
					5.6942560e-01, 5.7003976e-01 },
			{ 0.0000000e+00, 1.6095221e-02, 2.8668696e-02, 3.9628715e-02,
					4.9677882e-02, 5.9203579e-02, 6.8446487e-02, 7.7574538e-02,
					8.6705834e-02, 9.5927102e-02, 1.0530469e-01, 1.1488932e-01,
					1.2471977e-01, 1.3483019e-01, 1.4525019e-01, 1.5600919e-01,
					1.6713524e-01, 1.7866017e-01, 1.9061280e-01, 2.0302412e-01,
					2.1592535e-01, 2.2934527e-01, 2.4331173e-01, 2.5784820e-01,
					2.7297456e-01, 2.8870491e-01, 3.0504702e-01, 3.2199670e-01,
					3.3953997e-01, 3.5764871e-01, 3.7627672e-01, 3.9536421e-01,
					4.1482241e-01, 4.3454336e-01, 4.5439661e-01, 4.7422270e-01,
					4.9384295e-01, 5.1305572e-01, 5.3164760e-01, 5.4939181e-01,
					5.6606945e-01, 5.8146301e-01, 5.9537551e-01, 6.0763752e-01,
					6.1811593e-01, 6.2670750e-01, 6.3336050e-01, 6.3805735e-01,
					6.4082782e-01, 6.4173015e-01 } };

	public static int getOrdnung(double tu, double tg) {
		int n = 0;
		double v = tu / tg;
		
		if (v < 0.001) {
			throw new IllegalArgumentException("tu/tg zu klein");
		}else if (v <= 0.103638) {
			n = 2;
		} else if (v <= 0.218017) {
			n = 3;
		} else if (v <= 0.319357) {
			n = 4;
		} else if (v <= 0.410303) {
			n = 5;
		} else if (v <= 0.410303) {
			n = 6;
		} else if (v <= 0.5700) {
			n = 7;
		} else if (v <= 0.64173) {
			n = 8;
		} else {
			throw new IllegalArgumentException("tu/tg zu gross");
		}
		
		return n;
	}
	
	public static double[] calcSani(double tu, double tg) {
		int n = getOrdnung(tu, tg);

		double[] ri = new double[50];
		for (int i = 0; i < 50; i++) {
			ri[i] = ((double)i)/49;
		}
		
		double v = tu/tg;
		double r = spline(Tu_Tg[n-2],ri,v);
		double w = spline(ri,T_Tg[n-2],r);

		double[] T = new double[n];
		T[n-1] = w * tg;

		for (int i = n-1; i > 0; i--) {
			T[i-1] = T[n-1] * Math.pow(r, n - i);
		}
		
		//double[] Tcoeffs = new double[] { 0.408, 0.838, 1.72, 3.532 };
		return T;
	}

	private static final double spline(double[] x, double[] y, double v) {
		PolynomialSplineFunction f = interpolator.interpolate(x, y);
		return f.value(v);
	}
}
