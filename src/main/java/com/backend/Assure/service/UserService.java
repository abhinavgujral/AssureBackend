package com.backend.Assure.service;

import com.backend.Assure.AssureApplication;
import com.backend.Assure.dto.AuthenticateUserDTO;
import com.backend.Assure.dto.LoginDTO;
import com.backend.Assure.dto.UserDTO;
//import com.backend.Assure.entity.PhoneOTP;
import com.backend.Assure.entity.User;
import com.backend.Assure.exception.*;

//import com.backend.Assure.repository.PhoneOTP_Repository;
import com.backend.Assure.repository.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import org.modelmapper.TypeToken;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

//    @Autowired
//    PhoneOTP_Repository phoneOTP_repository;

    static Logger logger = LoggerFactory.getLogger(AssureApplication.class);

    // Twilio string saved as environment variables are accessed here
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    // Authenticating the user
    public void authenticateUser(MappingJacksonValue mappingJacksonValue){

        AuthenticateUserDTO authenticateUserDTO = modelMapper
                .map(mappingJacksonValue.getValue(), new TypeToken<AuthenticateUserDTO>() {}.getType());

        List<User> userList = userRepository.findAll();
System.out.println("userList-->"+userList);
        if(authenticateUserDTO.getUserName().equals("")){
            throw new EmptyInputException("Enter name");
        }
        else if (authenticateUserDTO.getUserName().length() <= 2){
            throw new EmptyInputException("Name should be more than 2 characters");
        }
        else if(authenticateUserDTO.getUserEmail().equals("")){
            throw new EmptyInputException("Enter email");
        }
        else if(!authenticateUserDTO.getUserEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
            throw new InvalidEmailException("Enter correct email");
        }
        else if(authenticateUserDTO.getUserMobile().equals("")){
            throw new EmptyInputException("Enter mobile");
        }
        else if(!authenticateUserDTO.getUserMobile().matches("\\d{10}")){
            throw new InvalidMobileNumberException("Enter correct mobile number");
        }
        else if(authenticateUserDTO.getUserPass().equals("")){
            throw new EmptyInputException("Enter pass");
        }
        else if(authenticateUserDTO.getUserPass().length() <= 4){
            throw new EmptyInputException("Password should be more that 4 characters");
        }

        for(User i : userList){
            if(i.getUserEmail().equals(authenticateUserDTO.getUserEmail())){
                throw new UserExists("Email Already Registered");
            }
            else if (i.getUserMobile().equals(authenticateUserDTO.getUserMobile())){
                throw new UserExists("Mobile Already Registered");
            }
        }

//        List<PhoneOTP> phoneOTPList = phoneOTP_repository.findAll();

        // checking if the phone number matches the otp
//        if (!phoneOTPList.isEmpty()){
//            for (PhoneOTP p : phoneOTPList){
//                if (p.getUserMobile().equals(authenticateUserDTO.getUserMobile())){
//                    throw new EmptyOTP("OTP not entered");
//                }
//            }

            //generate 4 digit number
//            String otp = otpGenerator();

//            logger.info(otp);

            // method to send sms to the user
//            sendSMS(authenticateUserDTO.getUserMobile(),otp);

            // saving mobile no. and otp for authentication
//            phoneOTP_repository.save(new PhoneOTP(authenticateUserDTO.getUserMobile(), otp));
//        }
//        else{

            //generate 4 digit number
//            String otp = otpGenerator();

//            logger.info(otp);

            // method to send sms to the user
//            sendSMS(authenticateUserDTO.getUserMobile(),otp);

            // saving mobile no. and otp for authentication
//            phoneOTP_repository.save(new PhoneOTP(authenticateUserDTO.getUserMobile(), otp));


    }


    //Registering the user
    public Long registerUser(UserDTO userDTO){

        User user = modelMapper
                        .map(userDTO, new TypeToken<User>() {}.getType());
System.out.println(user);
                userRepository.save(user);


        return user.getUserId();
    }

    // After login returning the user details
    public Long getUserDetails(LoginDTO loginDTO){
        List<User> userList = userRepository.findAll();

        Long temp = null;

        // To check if the email exists
        boolean check = false;

        if(loginDTO.getEmail().equals("")){
            throw new EmptyInputException("Enter email");
        }
        else if(!loginDTO.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
            throw new InvalidEmailException("Enter correct email");
        }
        else if(loginDTO.getPass().equals("")){
            throw new EmptyInputException("Enter pass");
        }
        else if(loginDTO.getPass().length() <= 4){
            throw new EmptyInputException("Password should be more that 4 characters");
        }

        for(User u : userList){
            // checking and adding the policies and claims to the arraylist
            if(u.getUserEmail().equals(loginDTO.getEmail())
                    && u.getUserPass().equals(loginDTO.getPass())){

                temp = u.getUserId();

                check = true;
                break;
            }
            else if(u.getUserEmail().equals(loginDTO.getEmail())
                    && (!u.getUserPass().equals(loginDTO.getPass()))){
                throw new IncorrectPasswordAndEmail("Email or password is incorrect");
            }
        }

        // If the  email is not registered
        if (check == false){
            throw new IncorrectPasswordAndEmail("Email not registered");
        }

        return temp;
    }

    // user pressing cancel button will remove phone-otp relation in db
//    public void removePhoneOTP(UserDTO userDTO) {
//        List<PhoneOTP> phoneOTPList = phoneOTP_repository.findAll();
//
//        for(PhoneOTP p : phoneOTPList){
//            if(p.getUserMobile().equals(userDTO.getUserMobile())){
//                phoneOTP_repository.delete(p);
//                break;
//            }
//        }
//    }

    //Random 4 digit number generator for otp
//    private String otpGenerator(){
//        return Math.round(Math.random()*9)
//                + "" + Math.round(Math.random()*9)
//                + "" + Math.round(Math.random()*9)
//                + "" + Math.round(Math.random()*9);
//    }

    public String getUserName(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new UserDoesNotExistException("Invalid User Id.");
        return user.get().getUserName();
    }

//     Send otp to the user
//    private void sendSMS(String mobile, String otp){
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            Message.creator(
//                            new com.twilio.type.PhoneNumber("+91" + mobile),
//                            new com.twilio.type.PhoneNumber("+13186682959"),
//                            "Your OTP is : " + otp)
//                    .create();
//    }
}
