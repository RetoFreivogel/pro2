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

	public void setKr(String ks_text) {
		try {
			double ks_value = Double.parseDouble(ks_text);
			RegelKreis alterKreis = model.getRegelkreis();
			RegelStrecke alteStrecke = alterKreis.getRegelstrecke();
			RegelStrecke neueStrecke = new RegelStrecke(ks_value,
					alteStrecke.getTu(), alteStrecke.getTg());
			RegelKreis neuerKreis = new RegelKreis(alterKreis.getDim(),
					neueStrecke);
			model.setRegelkreis(neuerKreis);
		} catch (Exception e) {
			// TODO print better Messages
			view.setStatus(e.getMessage());
		}
	}

}
