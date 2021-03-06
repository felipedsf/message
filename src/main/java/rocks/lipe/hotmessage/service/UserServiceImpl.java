package rocks.lipe.hotmessage.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rocks.lipe.hotmessage.domain.User;
import rocks.lipe.hotmessage.repository.RoleRepository;
import rocks.lipe.hotmessage.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User save(User user) {
		user.setRole(roleRepository.findByDescription("user"));
		user.setCreatedDate(new Date());
		user.setLogged(false);
		return userRepository.save(user);
	}

	@Override
	public User getUserByName(String username) {
		Optional<User> userSaved = userRepository.findByName(username);
		return userSaved.get();
	}

	@Override
	public void setLogged(User user) {
		user.setLogged(!user.isLogged());
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
