package view;

import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

class LowercaseDecimalFormatter extends NumberFormatter {
		private static final long serialVersionUID = 1L;

		LowercaseDecimalFormatter(){
			super(new DecimalFormat("##0.#E0"));
		}
				
		@Override
		public Object stringToValue(String text) throws ParseException {
			return super.stringToValue(text.toUpperCase());
		}
		
		@Override
		public String valueToString(Object value) throws ParseException {
			return super.valueToString(value).toLowerCase();
		}
}
