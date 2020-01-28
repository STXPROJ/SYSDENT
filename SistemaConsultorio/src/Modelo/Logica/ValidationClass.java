package Modelo.Logica;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import Vista.ComponentCustomization;

public class ValidationClass {
	ComponentCustomization custom = new ComponentCustomization();
	
public boolean validDate(String date) {
	boolean bool = false;
	SimpleDateFormat fecha = new SimpleDateFormat("dd/mm/yy");
	try {
		Date validDate = fecha.parse(date);
		bool = true;	
		} catch (ParseException e) {
		bool = false;
	}
	return bool;
}
public boolean validTime(String time) {
	boolean bool = false;
	SimpleDateFormat fecha = new SimpleDateFormat("h:m a");
	try {
		Date validDate = fecha.parse(time);
		bool = true;	
		} catch (ParseException e) {
		bool = false;
	}
	return bool;
}
public boolean validDigit(String digit) {
	String numberText = digit.replaceAll("[-() ]", "");
	boolean bool = false;
	double num = 0;
	try {
	num = Double.parseDouble(numberText);
	bool = true;}
	catch (Exception e) {
	bool = false;
	}
	return bool;
}
public String getDigit(String digit) {
	String numberText = digit.replaceAll("[-() ]", "");
	return numberText;
}
public boolean validFormat(String number,int lenght) {
	boolean bool = false;
	int size = 0;
	int digit;
	try {
		digit = Integer.parseInt(number);
		if (size == lenght) 
		{bool = true;}
		}catch (Exception e) 
		{e.printStackTrace();
			bool = false;}
	return bool;
}	
public boolean validStringFormat(String number,int lenght,int type) {
	boolean bool = false;
	int size = number.length();
	switch(type) {
	case 1:
	try {
		if (size == lenght) 
		{bool = true;}
		}catch (Exception e) 
		{bool = false;}
	break;
	case 2:
		try {
			if (size > lenght) 
			{bool = true;}
			}catch (Exception e) 
			{bool = false;}
		break;}
	return bool;
}
public boolean validDouble(String number) {
	boolean bool = false;
	try {
		Double digit = Double.parseDouble(number);
		bool = true;
		}catch (Exception e) 
		{bool = false;}
	return bool;
}
public boolean validEmail(String email) {
	boolean bool = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
			   bool = false;
		   }
		   return bool;
		}
}
