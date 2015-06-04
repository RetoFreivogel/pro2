package view;

import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

class DegreeFormatter extends NumberFormatter {
		private static final long serialVersionUID = 1L;

		DegreeFormatter(){
			super(new DecimalFormat("#0°"));
		}
				
		@Override
		public Object stringToValue(String text) throws ParseException {
			if(!text.endsWith("°")){
				text = text + "°";
			}
			return super.stringToValue(text);
		}
		
		@Override
		public String valueToString(Object value) throws ParseException {
			return super.valueToString(value);
		}
}
