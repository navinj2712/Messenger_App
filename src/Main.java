import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class NotValidNumber extends Exception {
    public NotValidNumber(String enterAValidNumber) {
        super(enterAValidNumber);
    }
}

class NotValidEmail extends Exception {
    public NotValidEmail(String enterNotValidEmail) {
        super(enterNotValidEmail);
    }
}

class IsEmpty extends Exception {
    public IsEmpty(String enterIsEmpty) {
        super(enterIsEmpty);
    }
}

class NotWhatsappNumber extends Exception {
    public NotWhatsappNumber(String enterNotWhatsappNumber) {
        super(enterNotWhatsappNumber);
    }
}

interface MessagingService{
    void sendMessage();
}

class SmsMessageService implements MessagingService{
    long number;
    private String text;
    public void sendMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the mobile number");
        number = sc.nextLong();

        boolean matcher = String.valueOf(number).matches("[6-9][0-9]{9}");
        try{
            if(!matcher) {
                throw new NotValidNumber("Not a valid number");
            }
            System.out.println("Enter the text message");
            text = sc.next();
            System.out.println("Message will be delivered soon.");
        }
        catch (NotValidNumber notValidNumber){
            System.out.println(notValidNumber.getMessage());
        }
    }
}

class EmailMessagingService implements MessagingService{
    @Override
    public void sendMessage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email id : ");
        String email = sc.next();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        try{
            if(!(matcher.matches()) || !(email.contains("gmail.com") || email.contains("yahoo.com"))){
                throw new NotValidEmail("Not a valid email.");
            }
            System.out.println("Enter subject : ");
            String subject = sc.next();
            try{
                if(subject.isEmpty()){
                    throw new IsEmpty("Required field is empty.");
                }
                System.out.println("Enter email body : ");
                String body = sc.next();
                if (body.isEmpty()){
                    throw new IsEmpty("Required field id empty.");
                }
                System.out.println("Email will be delivered soon.");
            }
            catch (IsEmpty isEmpty){
                System.out.println(isEmpty.getMessage());
            }

        }
        catch (NotValidEmail notValidEmail){
            System.out.println(notValidEmail.getMessage());
        }

    }
}

class WhatsAppMessagingService implements MessagingService{

    long number;
    boolean isWhatsappNumber;
    String text;
    @Override
    public void sendMessage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter mobile number : ");
        number = sc.nextLong();
        boolean matcher = String.valueOf(number).matches("[6-9][0-9]{9}");
        try{
            if(!matcher) {
                throw new NotValidNumber("Not a valid number");
            }
            System.out.println("Is above number is whatsapp number ? ");
            isWhatsappNumber = sc.nextBoolean();
            try {
                if(!isWhatsappNumber){
                    throw new NotWhatsappNumber("Only whatsapp number are allowed to send message.");
                }
                System.out.println("Enter the message : ");
                text = sc.next();
                try{
                    if(text.isEmpty()){
                        throw new IsEmpty("Required field is empty.");
                    }
                    System.out.println("Message will be delivered soon.");
                }
                catch (IsEmpty isEmpty){
                    System.out.println(isEmpty.getMessage());
                }

            }
            catch (NotWhatsappNumber notWhatsappNumber){
                System.out.println(notWhatsappNumber.getMessage());
            }
        }
        catch (NotValidNumber notValidNumber){
            System.out.println(notValidNumber.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SmsMessageService smsMessageService = new SmsMessageService();
        //smsMessageService.sendMessage();
        EmailMessagingService emailMessagingService = new EmailMessagingService();
        //emailMessagingService.sendMessage();
        WhatsAppMessagingService whatsAppMessagingService = new WhatsAppMessagingService();
        //whatsAppMessagingService.sendMessage();
        Scanner scanner = new Scanner(System.in);
        int input;
        do{
            System.out.println("Enter 1 to send SMS, 2 to send email, 3 to send whatsapp message and 4 to exit : ");
            input = scanner.nextInt();
            switch (input){
                case 1 : {
                    smsMessageService.sendMessage();
                    break;
                }
                case 2 : {
                    emailMessagingService.sendMessage();
                    break;
                }
                case 3 : {
                    whatsAppMessagingService.sendMessage();
                    break;
                }
                case 4 : {
                    System.out.println("Thank you for using our service.");
                    break;
                }
                default : {
                    System.out.println("Enter a valid input.");
                    break;
                }
            }
        }
        while (input != 4);
    }
}