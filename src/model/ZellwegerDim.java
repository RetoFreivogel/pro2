package model;

import org.apache.commons.math3.complex.Complex;

public class ZellwegerDim extends AbstractDim {
	private double phasenrand;
	
	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		TransferFunction tf = regelstrecke.getTranferFunction();
		tf.phaseAt(0);
		tf.amplitudeAt(0);
		double wpid;
		double wdpid;
		double low_freq = 0;
		double high_freq = 1000000;
		double middle_freq;
		double high2_freq=1000000;
		double low2_freq= 0;
		double middle2_freq;
		double beta;
		double beta1;
		double beta2;
		double subst;
		double ablwpid;
		double tnk;
		double tvk;
		double tppid;
		double pggrpid;
		
		do{
			middle_freq = (low_freq+high_freq)/2;
					
			if(phaseAt(middle_freq) < 135){
				
				high_freq=middle_freq;				
			}
			
			if(phaseAt(middle_freq) > 135){
				
				low_freq = middle_freq;	
			}
						
		}while(Math.abs(middle_freq+135)<=0.001 || Math.abs(middle_freq+135)>= -0.001);
		
		wpid =middle_freq;
		
		
		/*Ableitung berechnen
		 * 
		 * ablwpid=0;
		 * 
		 */
		
		
		subst= -0.5 - ablwpid*wpid; 
		beta1 = 1/subst + Math.sqrt(1/Math.pow(subst, 2)-1);
		beta2 = 1/subst - Math.sqrt(1/Math.pow(subst, 2)-1); 
		
		if(subst<-1|| subst> 1 || Math.abs(beta1)>1 || beta1<0){
			beta1=1;
		}
		beta=beta1;
		if(subst>=-1 && subst<  1&& Math.abs(beta2)<1 && beta2 > 0){
			beta=beta2;
		}
		
		tnk=1/(beta*wpid);
		tvk=beta/wpid;
		tppid=tvk/10;
		
		pggrpid= (Math.atan(middle_freq*tnk) + Math.atan(middle_freq*tvk) - Math.PI/2 - Math.atan(middle_freq*tppid)) /Math.PI*180;
		
		pggo=pggrpid + pggs;
		
		
		do{
			middle2_freq = (low2_freq+high2_freq)/2;
					
			if(phaseAt(middle2_freq) < 135){
				
				high2_freq=middle2_freq;				
			}
			
			if(phaseAt(middle2_freq) > 135){
				
				low2_freq = middle2_freq;	
			}
					
		}while(Math.abs(middle2_freq+(180+prand))<=0.001 || Math.abs(middle2_freq+(180+prand))>= -0.001);
		
		wdpid= middle2_freq;
		
		
		return new Regler(1, 1, 1);//tppid, tnpid,tvpid,krpid zurückgeben
	}
	
	public ZellwegerDim(double phasenrand) {
		super();
		this.phasenrand = phasenrand;
	}

	public double getPhasenrand() {
		return phasenrand;
	}

	public void setPhasenrand(double phasenrand) {
		this.phasenrand = phasenrand;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZellwegerDim\nphasenrand: ");
		builder.append(phasenrand);
		builder.append("\n");
		return builder.toString();
	}

}
