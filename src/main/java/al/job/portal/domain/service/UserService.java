package al.job.portal.domain.service;

import al.job.portal.domain.model.specifications.UserSpecification;
import al.job.portal.shared.exceptions.ResourceAlreadyExists;
import al.job.portal.shared.exceptions.ResourceNotFoundException;
import al.job.portal.shared.util.ErrorMessage;
import jakarta.transaction.Transactional;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.enums.UserRole;
import al.job.portal.domain.mapper.UserMapper;
import al.job.portal.domain.model.resource.UserResource;
import al.job.portal.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResource getUser(Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND.getValue()));

        return userMapper.toDTO(user);
    }

    public UserResource getUserByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND.getValue()));

        return userMapper.toDTO(user);
    }

    public Page<UserResource> getUsersByRole(UserRole userRole, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        UserSpecification specification = new UserSpecification().withUserRole(userRole);

        Page<User> users = userRepository.findAll(specification, pageable);

        return users.map(userMapper::toDTO);
    }

    @Transactional
    public UserResource createUser(UserResource userResource){
        String email = userResource.email();
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()){
            throw new ResourceAlreadyExists("Email is already in use");
        }

        User user = userMapper.toEntity(userResource);
        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Transactional
    public void deleteUser(Long userId){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found");
        }

        userRepository.deleteById(userId);
    }
}