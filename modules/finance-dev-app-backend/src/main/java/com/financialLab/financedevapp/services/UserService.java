package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.FinancePasswordEncoder;
import com.financialLab.financedevapp.dto.UserDTO;
import com.financialLab.financedevapp.dto.requests.NewUserRequestDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Finance;
import com.financialLab.financedevapp.models.Role;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends SimpleCrudService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(User existingObject, User updatedObject) {
        if (updatedObject.getName() != null) {
            existingObject.setName(updatedObject.getName());
        }
        if (updatedObject.getLastName() != null) {
            existingObject.setLastName(updatedObject.getLastName());
        }
        if (updatedObject.getEmail() != null) {
            existingObject.setEmail(updatedObject.getEmail());
        }
        if (updatedObject.getPassword() != null) {
            existingObject.setPassword(updatedObject.getPassword());
        }
        if (updatedObject.getPhone() != null) {
            existingObject.setPhone(updatedObject.getPhone());
        }
        if (updatedObject.getBirthday() != null) {
            existingObject.setBirthday(updatedObject.getBirthday());
        }
        if (updatedObject.getGoal() != null) {
            existingObject.setGoal(updatedObject.getGoal());
        }
        if (updatedObject.getPoints() != null) {
            existingObject.setPoints(updatedObject.getPoints());
        }
    }




    public List<UserDTO> getAllUsers() {
        List<User> users = this.findAll();

        return users.stream().map(UserDTO::of).collect(Collectors.toList());
    }

    public UserDTO getUser(String externalId) {
        return UserDTO.of(this.getByExternalId(externalId));
    }

    @Transactional
    public User create(NewUserRequestDTO userDTO, Role defaultRole) {
        //Hash user password
        userDTO.setPassword(FinancePasswordEncoder.encode(userDTO.getPassword()));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(defaultRole);
        return this.save(NewUserRequestDTO.toModel(userDTO, userRoles));
    }

    @Transactional
    public User create(NewUserRequestDTO userDTO, Set<Role> defaultRoles) {
        //Hash user password
        userDTO.setPassword(FinancePasswordEncoder.encode(userDTO.getPassword()));

        return this.save(NewUserRequestDTO.toModel(userDTO, defaultRoles));
    }

    public User getByEmail(String email){
        Optional<User> maybeUser = this.repository.findByEmail(email);
        if (maybeUser.isEmpty()) {
            throw FinancialAppException.objectNotFound("El usuario no fue encontrado");
        }
        return maybeUser.get();
    }

    public boolean exitsUserBYEmail(String email){
        Optional<User> maybeUser = this.repository.findByEmail(email);
        return maybeUser.isPresent();
    }

    public void test(String username, String password){
        this.repository.test(username, password);
    }
}
