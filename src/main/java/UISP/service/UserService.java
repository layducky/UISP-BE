package UISP.service;

import UISP.domain.Role;
import UISP.domain.User;
import UISP.DTO.response.UserDTO;
import UISP.repository.RoleRepository;
import UISP.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
    public User Save(User user) {
        return this.userRepository.save(user);
    }
    @Transactional
    public UserDTO CreateUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setFullname(user.getFullname());

        this.userRepository.save(user);
        if(user.getRole()!=null){
            Optional<Role> role=this.roleRepository.findById(user.getRole().getId());
            if(role.isPresent())
            {
                Role role1=role.get();
                userDTO.setRoleName(role1.getRoleName());
            }
        }else{
            userDTO.setRoleName("USER");
        }

        return userDTO;
    }
    public UserDTO UserToDTO(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullname(user.getFullname());
        userDTO.setAddress(user.getAddress());
        if(user.getRole()!=null){
            userDTO.setRoleName(user.getRole().getRoleName());
        }

        return userDTO;
    }
    public void updateUserToken(String refresh_token, String email){
        User user=this.findByEmail(email);
        user.setRefreshToken(refresh_token);
        this.userRepository.save(user);
    }
    public User getUserByRefreshTokenAndEmail(String email,String refreshToken)
    {
        return this.userRepository.findByEmailAndRefreshToken(email,refreshToken);
    }

    public boolean deleteUserByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return userRepository.deleteByEmail(email);
        }
        return false;
    }
}
