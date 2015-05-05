package model;

import org.apache.commons.math3.complex.Complex;

public class ZellwegerDim extends AbstractDim {
	private double phasenrand;
	
	@Override
	public Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo) {
		TransferFunction tf = regelstrecke.getTranferFunction();
		tf.phaseAt(0);
		tf.amplitudeAt(0);
		//gs in array [gs,w]= get_gs(T,Ks)  ist komplex
		double wpid, wdpid,	ablwpid;	
		double low_freq = 0, high_freq = 1000000, middle_freq;
		double low2_freq= 0, high2_freq= 1000000, middle2_freq;
		double beta, beta1, beta2;
		double subst;
		double tnk, tvk, tppid;
		double krpid, tvpid, tnpid, krk;
		double gspid, grpid,gopid;
		
		do{
			middle_freq = (low_freq+high_freq)/2;
					
			if(phaseAt(middle_freq) < 135){
				
				high_freq=middle_freq;				
			}
			
			if(phaseAt(middle_freq) > 135){
				
				low_freq = middle_freq;	
			}
						
		}while(Math.abs(middle_freq+135)>=0.001 || Math.abs(middle_freq+135)<= -0.001);
		
		wpid =middle_freq;
		
		
		ablwpid=0;
		for(int k=0; k< T.length; k++){
			ablwpid= ablwpid - T[k]/(1+Math.pow(wpid, 2)*Math.pow(T[k], 2));
		}
		  
		  
		 
		
		
		subst= -0.5 - ablwpid*wpid; 
		beta1 = 1/subst + Math.sqrt(1/Math.pow(subst, 2)-1);
		beta2 = 1/subst - Math.sqrt(1/Math.pow(subst, 2)-1); 
		
		if(subst<-1|| subst> 1 || Math.abs(beta1)>1 || beta1<0){
			beta1=1;
		}
		beta=beta1;
		if(subst>=-1 && subst< 1 && Math.abs(beta2)<1 && beta2 > 0){
			beta=beta2;
		}
		
		tnk=1/(beta*wpid);
		tvk=beta/wpid;
		tppid=tvk/10;
		
		pggrpid= (Math.atan(w*tnk) + Math.atan(w*tvk) - Math.PI/2 - Math.atan(w*tppid)) /Math.PI*180;
		
		pggo=pggrpid + pggs;
		
		
		do{
			middle2_freq = (low2_freq+high2_freq)/2;
					
			if(phaseAt(middle2_freq) < 180-phasenrand){
				
				high2_freq=middle2_freq;				
			}
			
			if(phaseAt(middle2_freq) > 180-phasenrand){
				
				low2_freq = middle2_freq;	
			}
					
		}while(Math.abs(middle2_freq+(180-phasenrand))>=0.001 || Math.abs(middle2_freq+(180-phasenrand))<= -0.001);
		
		wdpid= middle2_freq;
		
		
		
		gspid=Math.abs(gs(middle_freq));  //gs komplex
		
		
		grpid=Math.sqrt(1+Math.pow(wdpid*tnk, 2))*Math.sqrt(1+Math.pow(wdpid*tvk, 2))/(wdpid*tnk);
		gopid=gspid*grpid;
		krk=1/gopid;
		
		tnpid = tnk+tvk-tppid;
		tvpid = (tnk*tvk)/(tnk+tvk-tppid)-tppid;
		krpid = krk*(1+tvk/tnk-tppid/tnk);
		
		return new Regler(tnpid, tvpid, krpid, tppid);
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
