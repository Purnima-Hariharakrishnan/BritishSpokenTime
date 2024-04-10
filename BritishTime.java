package com.british;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BritishTime {


	public static void main(String[] args) {
		BritishTimeUnit obj = new BritishTimeUnit();
		obj.setMinuteTime();
		obj.setTens();
		obj.setDigits();
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Please enter your input : ");
		String input = scanner.nextLine();
		String [] partsOfTime = input.split(":");
		System.out.println(displayTime(partsOfTime, obj));
		scanner.close();
	}

	private static String displayTime(String[] partsOfTime, BritishTimeUnit obj) {		
		try {
			int hour = Integer.parseInt(partsOfTime[0]);
			int minutes = Integer.parseInt(partsOfTime[1]);
	
			//case = 0 < hour > 12, 0 < minutes > 59
			if (hour < 0 || hour > 12 || minutes < 0 || minutes > 59 ) {
				System.out.println("Entered input is in invalid time format. Please enter in the correct format.");
				return "";
			}
	
			// case = 00:00
			if (hour==0 && minutes== 0) {
				return "midnight";
			}
	
			// case = 12:00
			if (hour==12 && minutes==0) {
				return "noon";
			}
	
			//case = x:00
			if(minutes == 0){
				return obj.convertNumberToWords(hour) + obj.getMinuteTime().get(0);
			}
	
			//case = 12:45
			if (obj.getMinuteTime().containsKey(minutes) && minutes == 45 && hour == 12) {
		         return obj.getMinuteTime().get(minutes) + obj.convertNumberToWords(1);
			}
			
			//case = x<12:45
			if (obj.getMinuteTime().containsKey(minutes) && minutes == 45 && hour < 12) {
		         return obj.getMinuteTime().get(minutes) + obj.convertNumberToWords(hour + 1);
			}

			//case = x:{15, 30}
			if (obj.getMinuteTime().containsKey(minutes) && minutes != 45) {
	         return obj.getMinuteTime().get(minutes) + obj.convertNumberToWords(hour);
			}
			
			//case = x !=0:non multiples of 5
			if(hour != 0 && minutes % 5 != 0) {
				return obj.convertNumberToWords(hour) +  obj.convertNumberToWords(minutes);
			}
			
			//case = x!=0:multiples of 5 <= 30
			if(minutes <= 30){
			    
				return obj.convertNumberToWords(minutes) + "past " + obj.convertNumberToWords(hour);
			}
			
			int remainingMinutes = 60 - minutes;
			
			//others
			return obj.convertNumberToWords(remainingMinutes) + "to " + obj.convertNumberToWords(hour + 1);
		} catch (NumberFormatException e) {
			System.out.println("Entered input is in invalid format. Please enter the time in valid format.");
		}
		return "";
	}
		
}

class BritishTimeUnit {

	private HashMap<Integer, String> minuteTime = new HashMap<>();
	private HashMap<Integer, String> digits = new HashMap<>();
	private HashMap<Integer, String> tens = new HashMap<>();
	
	public Map<Integer, String> getMinuteTime() {
		return minuteTime;
	}

	public Map<Integer, String> getDigits() {
		return digits;
	}

	public Map<Integer, String> getTens() {
		return tens;
	}

	public void setMinuteTime() {
		minuteTime.put(00, "o'clock");
		minuteTime.put(15, "quarter past ");
		minuteTime.put(30, "half past ");
		minuteTime.put(45, "quarter to ");


	}

	public String convertNumberToWords(int number) {
        int positiveNumber = Integer.valueOf(number);

		if(positiveNumber == 0) {
			return "midnight";
		
		}
        
        if (positiveNumber < 20) {
            return digits.get(positiveNumber);
        }
        
        
		int tensPlace = number / 10;
		int onesPlace = number % 10;

            String result = tens.get(tensPlace); 

            if (onesPlace > 0) {
                result += digits.get(onesPlace); 
            }
            return result;
        
    }

	public void setTens() {
		tens.put(1, "ten ");
		tens.put(2, "twenty ");
		tens.put(3, "thirty ");
		tens.put(4, "forty ");
		tens.put(5, "fifty ");
	}

	public void setDigits() {
		digits.put(1, "one ");
		digits.put(2, "two ");
		digits.put(3, "three ");
		digits.put(4, "four ");
		digits.put(5, "five ");
		digits.put(6, "six ");
		digits.put(7, "seven ");
		digits.put(8, "eight ");
		digits.put(9, "nine ");
		digits.put(10, "ten ");
		digits.put(11, "eleven ");
		digits.put(12, "twelve ");
		digits.put(13, "thirteen ");
		digits.put(14, "fourteen ");
		digits.put(15, "fifteen ");
		digits.put(16, "sixteen ");
		digits.put(17, "seventeen ");
		digits.put(18, "eighteen ");
		digits.put(19, "nineteen ");
	}

}
