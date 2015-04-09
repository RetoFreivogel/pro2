package main;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model model) {
		this.model = model;
	}

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setKs(double ks) {
		try{
			model.getRegelkreis().getRegelstrecke().setKs(ks);
			model.update();
		}catch(Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setTu(double tu) {
		try{
			model.getRegelkreis().getRegelstrecke().setTu(tu);
			model.update();
		}catch(Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setTg(double tg) {
		try{
			model.getRegelkreis().getRegelstrecke().setTg(tg);
			model.update();
		}catch(Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}

