package br.com.dcm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {

	/**
	 * @constructor DateUtils
	 * @date 23/09/2015
	 */
	private DateUtils() {
		super();
	}

	/**
	 * @method getCurrentDate
	 * @date 23/09/2015
	 * @returnType String
	 * @return
	 * @throws Exception
	 * @description Metodo Util responsavel por retornar a data e hora corrente no
	 *              formato dd-MM-yyyy-HH-mm-ss
	 */
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	/**
	 * @method intervaloTempo
	 * @date 23/09/2015
	 * @returnType String
	 * @param strDataInicio
	 * @param strDataTermino
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 * @description Retorna a quantidade de dias entre as datas.
	 */
	public static String intervaloTempo(String strDataInicio, String strDataTermino) throws ParseException {
		String intervalo = "";
		String datePattern = "dd/MM/yyyy";
		Date dataInicio = strToDate(strDataInicio, datePattern);
		GregorianCalendar gregInicio = new GregorianCalendar();
		gregInicio.setTime(dataInicio);

		Date dataTermino = strToDate(strDataTermino, datePattern);
		GregorianCalendar gregTermino = new GregorianCalendar();
		gregTermino.setTime(dataTermino);

		gregTermino.setTime(formatPatternDate(gregTermino.getTime(), datePattern));
		gregInicio.setTime(formatPatternDate(gregInicio.getTime(), datePattern));

		if (dataInicio != null && dataTermino != null && dataInicio.compareTo(dataTermino) < 0) {
			long dt1 = gregInicio.getTimeInMillis();
			long dt2 = gregTermino.getTimeInMillis();
			intervalo = String.valueOf(((dt2 - dt1) / 86400000) + 1);
		} else if (gregInicio.compareTo(gregTermino) >= 0) {
			intervalo = "0";
		}
		return intervalo;
	}

	/**
	 * @method intervaloTempo
	 * @date 23/09/2015
	 * @returnType String
	 * @param Date dataInicio
	 * @param Date dataTermino
	 * @return a quantidade de dias entre as datas
	 * @throws ParseException
	 * @throws Exception
	 * @description Retorna a quantidade de dias entre as datas.
	 */
	public static String intervaloTempo(Date dataInicio, Date dataTermino) throws ParseException {
		String intervalo = "";
		String datePattern = "dd/MM/yyyy";

		GregorianCalendar gregInicio = new GregorianCalendar();
		gregInicio.setTime(dataInicio);

		GregorianCalendar gregTermino = new GregorianCalendar();
		gregTermino.setTime(dataTermino);

		gregTermino.setTime(formatPatternDate(gregTermino.getTime(), datePattern));
		gregInicio.setTime(formatPatternDate(gregInicio.getTime(), datePattern));

		if (dataInicio != null && dataTermino != null && dataInicio.compareTo(dataTermino) < 0) {
			long dt1 = gregInicio.getTimeInMillis();
			long dt2 = gregTermino.getTimeInMillis();
			intervalo = String.valueOf(((dt2 - dt1) / 86400000) + 1);
		} else if (gregInicio.compareTo(gregTermino) >= 0) {
			intervalo = "0";
		}
		return intervalo;
	}

	/**
	 * @method dateToStr
	 * @date 23/09/2015
	 * @returnType String
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 * @description Converte Date para String.
	 */
	public static String dateToStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * @description Recebe uma data em string e seu formato e retorna ela convertida
	 *              para objeto date
	 * @method strToDate
	 * @date 23/09/2015
	 * @returnType Date
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 * 
	 */
	public static Date strToDate(String strDate, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(strDate);
	}

	/**
	 * @method calcIdade
	 * @date 23/09/2015
	 * @returnType Integer
	 * @param dataNasc
	 * @return
	 * @description Devera passar uma Data com um dos seguintes patterns -->
	 *              (dd/MM/yyyy, dd-MM-yyyy ou ddMMyyyy). Retorna idade.
	 */
	public static Integer calcIdade(Date dataNasc) {
		Date hoje = new Date();
		Calendar cal = Calendar.getInstance();

		cal.setTime(hoje);
		Integer diaAtual = cal.get(Calendar.DAY_OF_YEAR);
		Integer anoAtual = cal.get(Calendar.YEAR);

		cal.setTime(dataNasc);
		Integer diaNasc = cal.get(Calendar.DAY_OF_YEAR);
		Integer anoNasc = cal.get(Calendar.YEAR);

		Integer nAno = anoAtual - anoNasc;

		if (diaAtual < diaNasc) {
			nAno--; // Ainda nao completou aniversario esse ano.
		}
		return nAno;
	}
	
	/**
	 * @method getDate
	 * @date 25/11/2019
	 * @returnType String
	 * @param pattern
	 * @param date
	 * @return
	 * @description Retorna data passada como parâmetro no formato do pattern informado.
	 */
	public static String getDate(String pattern, Long dateInMiliseconds) {
		SimpleDateFormat sd = new SimpleDateFormat(pattern);
		final GregorianCalendar tempDate = new GregorianCalendar();
		tempDate.setTimeInMillis(dateInMiliseconds);
		return sd.format(tempDate.getTime());
	}

	/**
	 * @method getDate
	 * @date 23/09/2015
	 * @returnType String
	 * @param pattern
	 * @return
	 * @description Retorna data atual no formato do pattern informado.
	 */
	public static String getDate(String pattern) {
		SimpleDateFormat sd = new SimpleDateFormat(pattern);
		final GregorianCalendar tempDate = new GregorianCalendar();
		tempDate.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
		return sd.format(tempDate.getTime());
	}

	/**
	 * @method getDate
	 * @date 23/09/2015
	 * @returnType Date
	 * @return
	 * @description Retorna data atual.
	 */
	public static Date getDate() {
		final GregorianCalendar tempDate = new GregorianCalendar();
		tempDate.setTime(Calendar.getInstance().getTime());
		return tempDate.getTime();
	}

	/**
	 * @method adicionarDia
	 * @date 23/09/2015
	 * @returnType String
	 * @param strDate
	 * @param patternIN
	 * @param patternOUT
	 * @param nDia
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 * @description Adiciona nDias na data informada.
	 */
	public static String adicionarDia(String strDate, String patternIN, String patternOUT, Integer nDia)
			throws ParseException {
		SimpleDateFormat sdfIN = new SimpleDateFormat(patternIN);
		Date date = null;
		SimpleDateFormat sdfOUT = null;
		GregorianCalendar tempDate = null;

		date = sdfIN.parse(strDate);
		sdfOUT = new SimpleDateFormat(patternOUT);
		tempDate = new GregorianCalendar();

		tempDate.setTimeInMillis(date.getTime());
		tempDate.add(Calendar.DAY_OF_MONTH, nDia);
		return sdfOUT.format(tempDate.getTimeInMillis());
	}

	/**
	 * @method adicionarDia
	 * @date 23/09/2015
	 * @returnType String
	 * @param date
	 * @param patternIN
	 * @param patternOUT
	 * @param nDia
	 * @return
	 * @description Adiciona nDias na data informada.
	 */
	public static String adicionarDia(Date date, String patternOUT, Integer nDia) {
		SimpleDateFormat sdfOUT = null;
		GregorianCalendar tempDate = null;

		sdfOUT = new SimpleDateFormat(patternOUT);
		tempDate = new GregorianCalendar();

		tempDate.setTimeInMillis(date.getTime());
		tempDate.add(Calendar.DAY_OF_MONTH, nDia);
		return sdfOUT.format(tempDate.getTimeInMillis());
	}

	/**
	 * @method formatPatternDate
	 * @date 23/09/2015
	 * @returnType Date
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * @description Metodo formata a data para o padrao passado
	 */
	public static Date formatPatternDate(Date date, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(sdf.format(date.getTime()));
	}

	/**
	 * Metodo responsavel por alterar as horas de uma data
	 *
	 * @param data
	 * @param horas
	 * @param minutos
	 * @param segundos
	 * @return
	 */
	public static Date alteraHorasDaData(Date data, int horas, int minutos, int segundos) {
		if (data != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			cal.set(Calendar.HOUR_OF_DAY, horas);
			cal.set(Calendar.MINUTE, minutos);
			cal.set(Calendar.SECOND, segundos);
			cal.set(Calendar.MILLISECOND, 0);

			return cal.getTime();
		}
		return data;
	}

	/**
	 * Metodo responsavel por zerar os segundos de uma Data
	 *
	 * @param data
	 * @return
	 */
	public static Date zerarSegundos(Date data) {
		if (data != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
		return data;
	}

	/**
	 * @method alteraParaUltimaHoraDoDia
	 * @date 17/05/2016
	 * @returnType Date
	 * @param data
	 * @return
	 * @description Metodo altera a data passada para o ultimo segundo do dia
	 */
	public static Date alteraParaUltimaHoraDoDia(Date data) {
		return alteraHorasDaData(data, 23, 59, 59);
	}

	/**
	 * @method alteraParaPrimeiraHoraDoDia
	 * @date 17/05/2016
	 * @returnType Date
	 * @param data
	 * @return
	 * @description Metodo altera a data passa para a primeira hora do dia
	 */
	public static Date alteraParaPrimeiraHoraDoDia(Date data) {
		return alteraHorasDaData(data, 0, 0, 0);
	}

	/**
	 * @method getPrimeiroDiaMes
	 * @date 02/02/2017
	 * @returnType Date
	 * @param data
	 * @return
	 * @description Metodo retorna o primeiro dia do mês
	 */
	@SuppressWarnings("deprecation")
	public static Date getPrimeiroDiaMes(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, date.getYear());
		cal.set(Calendar.MONTH, date.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

		return cal.getTime();
	}

	/**
	 * @method getUltimoDiaMes
	 * @date 02/02/2017
	 * @returnType Date
	 * @param data
	 * @return
	 * @description Metodo retorna o ultimo dia do mês
	 */
	@SuppressWarnings("deprecation")
	public static Date getUltimoDiaMes(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		return cal.getTime();
	}

	public static boolean isDateValid(String dataTexto) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			format.setLenient(false);
			format.parse(dataTexto);
			return true;
		} catch (ParseException e) {
			System.out.println("Data inválida");
			return false;
		}
	}
	
	public static String convertToBrDate(String data) {
		if(data == null) {
			return data;
		}
		if(data.contains(" ")) {
			data = data.split(" ")[0];
		}
		String[] dtSplit = data.split("\\-");
		
		if(dtSplit != null && dtSplit.length == 3) {
			data = dtSplit[2] + "/"+ dtSplit[1] + "/"+ dtSplit[0];
		}
		
		return data;
	}

}
