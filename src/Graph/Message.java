package Graph;
import java.util.Date;

public class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;
    Message(String message){
        this.asText = message;
        this.data = message.getBytes();
        double tempDouble;
        try{
            tempDouble = Double.parseDouble(message);
        }catch(NumberFormatException e){
//            System.out.println("Failed to convert the string to a number: " + e.getMessage());
            tempDouble = Double.NaN;
        }
        this.asDouble = tempDouble;
        this.date = new Date();
    }
    Message(byte[] bytes){
        this(new String(bytes));

    }
    Message(double number){
        this(String.valueOf(number));
    }
}
