package main;


public class Controller {
	private Model model;
	private View view;

	public Controller(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public void setKs(double ks){
		try{
			model.getRegelkreis().getRegelstrecke().setKs(ks);
		}catch (Exception e){
			view.setStatus(e.getMessage());
		}
	}
	
	public void setTu(double tu){
		try{
			model.getRegelkreis().getRegelstrecke().setTu(tu);
		}catch (Exception e){
			view.setStatus(e.getMessage());
		}
	}
	
	public void setTg(double tg){
		try{
			model.getRegelkreis().getRegelstrecke().setTg(tg);
		}catch (Exception e){
			view.setStatus(e.getMessage());
		}
	}
}
