package com.mifmodaintima.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormatDate {

	public static Date formatDate(String data) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataConvertida = formatter.parse(data);

		return dataConvertida;

	}

	public static Date formatDate(Calendar calendar) throws ParseException {	
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataCalendario = formatter.format(calendar.getTime());
		Date dataConvertida = formatter.parse(dataCalendario);
		return dataConvertida;

	}

}
