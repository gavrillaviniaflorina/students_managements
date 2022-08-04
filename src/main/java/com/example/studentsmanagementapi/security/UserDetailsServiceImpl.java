//package com.example.studentsmanagementapi.security;
//
//
//import com.example.studentsmanagementapi.model.User;
//import com.example.studentsmanagementapi.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user= userRepository.selectedEmailExists(s).get();
////        if(user!=null){
////            return user;
////        }
//
//        throw  new UsernameNotFoundException(
//                "User "+s+"not found"
//        );
//    }
//}
